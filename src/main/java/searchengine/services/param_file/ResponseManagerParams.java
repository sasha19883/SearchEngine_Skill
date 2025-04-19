package src.main.java.searchengine.services.param_file;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class ResponseManagerParams {
    private Map<Integer, Float> pageAndRank;
    private List<String> lemmas;
    private int limit;
    private int offset;
    private String query;
}