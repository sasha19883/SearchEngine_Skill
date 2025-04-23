package main.searchengine.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import main.searchengine.config.Site;
import main.searchengine.config.SitesList;
import main.searchengine.model.SitePage;
import main.searchengine.model.Status;
import main.searchengine.parser.IndexParser;
import main.searchengine.parser.LemmaParser;
import main.searchengine.parser.SiteIndexed;
import main.searchengine.repozitories.IndexSearchRepository;
import main.searchengine.repozitories.LemmaRepository;
import main.searchengine.repozitories.PageRepository;
import main.searchengine.repozitories.SiteRepository;
import main.searchengine.services.IndexingService;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
@Slf4j
public class IndexingServiceImpl implements IndexingService {
    private static final int processorCoreCount = Runtime.getRuntime().availableProcessors();
    private ExecutorService executorService;
    private final PageRepository pageRepository;
    private final SiteRepository siteRepository;
    private final LemmaRepository lemmaRepository;
    private final IndexSearchRepository indexSearchRepository;
    private final LemmaParser lemmaParser;
    private final IndexParser indexParser;
    private final SitesList sitesList;

    public IndexingServiceImpl(PageRepository pageRepository, SiteRepository siteRepository, LemmaRepository lemmaRepository, IndexSearchRepository indexSearchRepository, LemmaParser lemmaParser, IndexParser indexParser, SitesList sitesList) {
        this.pageRepository = pageRepository;
        this.siteRepository = siteRepository;
        this.lemmaRepository = lemmaRepository;
        this.indexSearchRepository = indexSearchRepository;
        this.lemmaParser = lemmaParser;
        this.indexParser = indexParser;
        this.sitesList = sitesList;
    }

    @Override
    public boolean urlIndexing(String url) {
        if (urlCheck(url)) {
            log.info("Start reindexing site - " + url);
            executorService = Executors.newFixedThreadPool(processorCoreCount);
            executorService.submit(new SiteIndexed(pageRepository, siteRepository, lemmaRepository, indexSearchRepository, lemmaParser, indexParser, url, sitesList));
            executorService.shutdown();

            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean indexingAll() {
        if (isIndexingActive()) {
            log.debug("Indexing already started");
            return false;
        } else {
            List<Site> siteList = sitesList.getSites();
            executorService = Executors.newFixedThreadPool(processorCoreCount);
            for (Site site : siteList) {
                String url = site.getUrl();
                SitePage sitePage = new SitePage();
                sitePage.setName(site.getName());
                log.info("Parsing site: " + site.getName());
                executorService.submit(new SiteIndexed(pageRepository, siteRepository, lemmaRepository, indexSearchRepository, lemmaParser, indexParser, url, sitesList));
            }
            executorService.shutdown();
        }
        return true;
    }

    @Override
    public boolean stopIndexing() {
        if (isIndexingActive()) {
            log.info("Indexing was stopped");
            executorService.shutdownNow();
            return true;
        } else {
            log.info("Indexing was not stopped because it was not started");
            return false;
        }
    }

    private boolean isIndexingActive() {
        siteRepository.flush();
        Iterable<SitePage> siteList = siteRepository.findAll();
        for (SitePage site : siteList) {
            if (site.getStatus() == Status.INDEXING) {
                return true;
            }
        }
        return false;
    }

    private boolean urlCheck(String url) {
        List<Site> urlList = sitesList.getSites();
        for (Site site : urlList) {
            if (site.getUrl().equals(url)) {
                return true;
            }
        }
        return false;
    }
}
