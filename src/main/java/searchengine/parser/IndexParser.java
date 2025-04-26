package searchengine.parser;

import searchengine.dto.StatisticsIndex;
import searchengine.model.SitePage;

import java.util.List;

public interface IndexParser {
    void run(SitePage site);
    List<StatisticsIndex> getIndexList();
}
