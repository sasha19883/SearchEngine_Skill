package searchengine.dto;

import lombok.Value;

@Value
public class StatisticsPage {
    String url;
    String content;
    int code;
}