package src.main.searchengine.parser;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import src.main.searchengine.dto.StatisticsLemma;
import src.main.searchengine.model.Page;
import src.main.searchengine.model.SitePage;
import src.main.searchengine.morphology.Morphology;
import src.main.searchengine.repozitories.PageRepository;
import src.main.searchengine.utils.CleanHtmlCode;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
@RequiredArgsConstructor
@Slf4j
public class LemmaCreation implements LemmaParser {
    private final PageRepository pageRepository;
    private final Morphology morphology;
    private List<StatisticsLemma> statisticsLemmaList;

    public LemmaCreation(PageRepository pageRepository, Morphology morphology) {
        this.pageRepository = pageRepository;
        this.morphology = morphology;
    }

    public List<StatisticsLemma> getLemmaDtoList() {
        return statisticsLemmaList;
    }

    @Override
    public void run(SitePage site) {
        statisticsLemmaList = new CopyOnWriteArrayList<>();
        Iterable<Page> pageList = pageRepository.findAll();
        TreeMap<String, Integer> lemmaList = new TreeMap<>();
        for (Page page : pageList) {
            String content = page.getContent();
            String title = CleanHtmlCode.clear(content, "title");
            String body = CleanHtmlCode.clear(content, "body");
            HashMap<String, Integer> titleList = morphology.getLemmaList(title);
            HashMap<String, Integer> bodyList = morphology.getLemmaList(body);
            Set<String> allTheWords = new HashSet<>();
            allTheWords.addAll(titleList.keySet());
            allTheWords.addAll(bodyList.keySet());
            for (String word : allTheWords) {
                int frequency = lemmaList.getOrDefault(word, 0) + 1;
                lemmaList.put(word, frequency);
            }
        }
        for (String lemma : lemmaList.keySet()) {
            Integer frequency = lemmaList.get(lemma);
            statisticsLemmaList.add(new StatisticsLemma(lemma, frequency));
        }
    }


}
