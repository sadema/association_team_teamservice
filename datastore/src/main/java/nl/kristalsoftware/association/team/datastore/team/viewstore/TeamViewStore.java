package nl.kristalsoftware.association.team.datastore.team.viewstore;

import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.association.team.PlayerEventData;
import nl.kristalsoftware.association.team.TeamEventData;
import nl.kristalsoftware.association.team.datastore.player.viewstore.PlayerDocument;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
public class TeamViewStore {

    private final RestTemplate restTemplate;

    @Value("${team.datastore.viewstore.url}")
    private String teamDatabaseUrl;

    public void saveRegisteredTeam(TeamEventData eventData) {
        String url = teamDatabaseUrl + eventData.getReference();
        TeamDocument teamDocument = TeamDocument.of(eventData);
        createOrUpdateDocument(url, teamDocument);
    }

    private void createOrUpdateDocument(String url, TeamDocument teamDocument) {
        HttpEntity<TeamDocument> httpEntity = new HttpEntity<>(teamDocument);
        restTemplate.put(url, httpEntity);
    }


}
