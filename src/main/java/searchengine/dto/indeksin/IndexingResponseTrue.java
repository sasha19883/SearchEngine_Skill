package src.main.java.searchengine.dto.indeksin;

import lombok.Getter;

@Getter
public class IndexingResponseTrue implements IndexingResponse{
    private boolean result = true;
}