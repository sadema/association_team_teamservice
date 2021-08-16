package nl.kristalsoftware.association.team.datastore.player.viewstore;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.team.PlayerEventData;
import nl.kristalsoftware.association.team.datastore.team.viewstore.TeamDocument;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class PlayerViewStore extends BaseViewStore {

    public PlayerViewStore(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Value("${team.datastore.viewstore.url}")
    private String teamDatabaseUrl;

    public void playerSignedUp(PlayerEventData eventData) {
        String url = teamDatabaseUrl + eventData.getReference();
        PlayerDocument playerDocument = PlayerDocument.of(eventData);
        createOrUpdateDocument(url, playerDocument);
    }

    public void playerAddedToTeam(PlayerEventData eventData) {
        String url = teamDatabaseUrl + eventData.getReference();
        PlayerDocument playerDocument = setTeamReference(url, eventData);
        createOrUpdateDocument(url, playerDocument);
    }

    public void playerMovedToAnotherTeam(PlayerEventData eventData) {
        String url = teamDatabaseUrl + eventData.getReference();
        PlayerDocument playerDocument = setTeamReference(url, eventData);
        createOrUpdateDocument(url, playerDocument);
    }

    public void playerRoleAssigned(PlayerEventData eventData) {
        String url = teamDatabaseUrl + eventData.getReference();
        PlayerDocument playerDocument = getPlayerDocument(url);
        playerDocument.setRole(eventData.getRole());
        createOrUpdateDocument(url, playerDocument);
    }

    private PlayerDocument setTeamReference(String url, PlayerEventData eventData) {
        PlayerDocument playerDocument = getPlayerDocument(url);
        playerDocument.setTeam_reference(eventData.getTeamReference());
        return playerDocument;
    }

}
