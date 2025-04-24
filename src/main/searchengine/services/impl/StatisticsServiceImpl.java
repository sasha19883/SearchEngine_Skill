package src.main.searchengine.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.main.searchengine.dto.DetailedStatisticsItem;
import src.main.searchengine.dto.StatisticsData;
import src.main.searchengine.dto.StatisticsResponse;
import src.main.searchengine.dto.TotalStatistics;
import src.main.searchengine.model.SitePage;
import src.main.searchengine.model.Status;
import src.main.searchengine.repozitories.LemmaRepository;
import src.main.searchengine.repozitories.PageRepository;
import src.main.searchengine.repozitories.SiteRepository;
import src.main.searchengine.services.StatisticsService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {
    private final PageRepository pageRepository;
    private final LemmaRepository lemmaRepository;
    private final SiteRepository siteRepository;

    public StatisticsServiceImpl(PageRepository pageRepository, LemmaRepository lemmaRepository, SiteRepository siteRepository) {
        this.pageRepository = pageRepository;
        this.lemmaRepository = lemmaRepository;
        this.siteRepository = siteRepository;
    }

    private TotalStatistics getTotal() {
        Long sites = siteRepository.count();
        Long pages = pageRepository.count();
        Long lemmas = lemmaRepository.count();
        return new TotalStatistics(sites, pages, lemmas, true);
    }

    private DetailedStatisticsItem getDetailed(SitePage site) {
        String url = site.getUrl();
        String name = site.getName();
        Status status = site.getStatus();
        Date statusTime = site.getStatusTime();
        String error = site.getLastError();
        long pages = pageRepository.countBySiteId(site);
        long lemmas = lemmaRepository.countBySitePageId(site);
        return new DetailedStatisticsItem(url, name, status, statusTime, error, pages, lemmas);
    }

    private List<DetailedStatisticsItem> getDetailedList() {
        List<SitePage> siteList = siteRepository.findAll();
        List<DetailedStatisticsItem> result = new ArrayList<>();
        for (SitePage site : siteList) {
            DetailedStatisticsItem item = getDetailed(site);
            result.add(item);
        }
        return result;
    }


    @Override
    public StatisticsResponse getStatistics() {
        TotalStatistics total = getTotal();
        List<DetailedStatisticsItem> list = getDetailedList();
        return new StatisticsResponse(true, new StatisticsData(total, list));
    }
}