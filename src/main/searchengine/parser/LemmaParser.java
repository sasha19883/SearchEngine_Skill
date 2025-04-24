package src.main.searchengine.parser;

import src.main.searchengine.dto.StatisticsLemma;
import src.main.searchengine.model.SitePage;

import java.util.List;

public interface LemmaParser {
    void run(SitePage site);
    List<StatisticsLemma> getLemmaDtoList();
}
