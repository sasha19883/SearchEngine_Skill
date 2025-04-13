package searchengine.dto.statistics.indeksin;

import lombok.Getter;

@Getter
public class IndexingResponseTrue implements IndexingResponse{
    private boolean result = true;
}