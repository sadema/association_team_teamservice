package nl.kristalsoftware.association.team.datastore.types.player.viewstore;

import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.team.PlayerEventData;
import nl.kristalsoftware.association.team.datastore.base.BaseViewStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class PlayerViewStore extends BaseViewStore<PlayerDocument> {

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
        PlayerDocument playerDocument = getDocument(url, PlayerDocument.class);
        playerDocument.setTeam_reference(eventData.getTeamReference());
        createOrUpdateDocument(url, playerDocument);
    }

    public void playerMovedToAnotherTeam(PlayerEventData eventData) {
        String url = teamDatabaseUrl + eventData.getReference();
        PlayerDocument playerDocument = getDocument(url, PlayerDocument.class);
        playerDocument.setTeam_reference(eventData.getTeamReference());
        createOrUpdateDocument(url, playerDocument);
    }

    public void playerDetachedFromTeam(PlayerEventData eventData) {
        String url = teamDatabaseUrl + eventData.getReference();
        PlayerDocument playerDocument = getDocument(url, PlayerDocument.class);
        playerDocument.setTeam_reference(eventData.getTeamReference());
        createOrUpdateDocument(url, playerDocument);
    }

    public void playerRoleAssigned(PlayerEventData eventData) {
        String url = teamDatabaseUrl + eventData.getReference();
        PlayerDocument playerDocument = getDocument(url, PlayerDocument.class);
        playerDocument.setRole(eventData.getRole());
        createOrUpdateDocument(url, playerDocument);
    }

}
