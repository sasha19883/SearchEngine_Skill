package src.main.java.searchengine.services;


import searchengine.dto.indexing.IndexingResponse;

public interface IndexingService {   //
    IndexingResponse getIndexing();
    IndexingResponse stopIndexing();
    IndexingResponse getOnePageIndexing(String url);
}