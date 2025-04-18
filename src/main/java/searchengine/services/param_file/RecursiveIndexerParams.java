package src.main.java.searchengine.services.param_file;


import lombok.AllArgsConstructor;
import lombok.Getter;
import searchengine.config.ConnectionConfig;
import searchengine.services.Stopper;

import java.util.Set;

@Getter
@AllArgsConstructor
public class RecursiveIndexerParams {
    private String address;
    private String baseUrl;
    private Set<String> total;
    private Stopper stopper;
    private ConnectionConfig connectionConfig;
}
