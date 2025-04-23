package main.searchengine.parser;

import main.searchengine.dto.StatisticsIndex;
import main.searchengine.model.SitePage;

import java.util.List;

public interface IndexParser {
    void run(SitePage site);
    List<StatisticsIndex> getIndexList();
}
