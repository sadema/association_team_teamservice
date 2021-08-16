package nl.kristalsoftware.association.team.datastore.player.viewstore;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class BaseViewStore {

    private final RestTemplate restTemplate;

    protected BaseViewStore(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    protected PlayerDocument getPlayerDocument(String url) {
        ResponseEntity<PlayerDocument> responseEntity = restTemplate.getForEntity(url, PlayerDocument.class);
        return responseEntity.getBody();
    }

    protected void createOrUpdateDocument(String url, PlayerDocument playerDocument) {
        HttpEntity<PlayerDocument> httpEntity = new HttpEntity<>(playerDocument);
        restTemplate.put(url, httpEntity);
    }

}
