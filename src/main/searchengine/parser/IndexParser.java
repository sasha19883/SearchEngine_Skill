package src.main.searchengine.parser;

import src.main.searchengine.dto.StatisticsIndex;
import src.main.searchengine.model.SitePage;

import java.util.List;

public interface IndexParser {
    void run(SitePage site);
    List<StatisticsIndex> getIndexList();
}
