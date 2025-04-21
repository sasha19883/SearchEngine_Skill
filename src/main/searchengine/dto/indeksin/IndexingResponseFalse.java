package src.main.searchengine.dto.indeksin;

import lombok.Getter;

@Getter
public class IndexingResponseFalse implements IndexingResponse{
    private boolean result = false;
    private String error;

    public IndexingResponseFalse(String error) {
        this.error = error;
    }
}