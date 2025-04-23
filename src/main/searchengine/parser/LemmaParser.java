package main.searchengine.parser;

import main.searchengine.dto.StatisticsLemma;
import main.searchengine.model.SitePage;

import java.util.List;

public interface LemmaParser {
    void run(SitePage site);
    List<StatisticsLemma> getLemmaDtoList();
}
