package src.main.java.searchengine.dto.statistics;

import lombok.Data;

@Data
public class TotalStatistics {
    private int sites;
    private int pages;
    private int lemmas;
    private boolean indexing;
}
