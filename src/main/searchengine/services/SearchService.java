package src.main.searchengine.services;


import src.main.searchengine.dto.StatisticsSearch;

import java.util.List;

public interface SearchService {
    List<StatisticsSearch> allSiteSearch(String text, int offset, int limit);
    List<StatisticsSearch> siteSearch(String searchText, String url, int offset, int limit);
}
