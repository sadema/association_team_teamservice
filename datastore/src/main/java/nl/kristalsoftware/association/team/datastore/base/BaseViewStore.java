package nl.kristalsoftware.association.team.datastore.base;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class BaseViewStore<T> {

    private final RestTemplate restTemplate;

    protected BaseViewStore(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    protected T getDocument(String url, Class<T> clazz) {
        ResponseEntity<T> responseEntity = restTemplate.getForEntity(url, clazz);
        return responseEntity.getBody();
    }

    protected void createOrUpdateDocument(String url, T document) {
        HttpEntity<T> httpEntity = new HttpEntity<>(document);
        restTemplate.put(url, httpEntity);
    }

}
