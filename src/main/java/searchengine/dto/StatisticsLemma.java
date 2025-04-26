package searchengine.dto;

import lombok.Value;

@Value
public class StatisticsLemma {
    String lemma;
    int frequency;
}