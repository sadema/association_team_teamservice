package nl.kristalsoftware.association.team.datastore.types.team.viewstore;

import nl.kristalsoftware.association.team.TeamEventData;
import nl.kristalsoftware.association.team.datastore.base.BaseViewStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TeamViewStore extends BaseViewStore<TeamDocument> {

    public TeamViewStore(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Value("${team.datastore.viewstore.url}")
    private String teamDatabaseUrl;

    public void saveRegisteredTeam(TeamEventData eventData) {
        String url = teamDatabaseUrl + eventData.getReference();
        TeamDocument teamDocument = TeamDocument.of(eventData);
        createOrUpdateDocument(url, teamDocument);
    }

    public void saveEditedTeam(TeamEventData eventData) {
        String url = teamDatabaseUrl + eventData.getReference();
        TeamDocument teamDocument = getDocument(url, TeamDocument.class);
        teamDocument.setTeamName(eventData.getName());
        teamDocument.setTeamCategory(eventData.getCategory());
        teamDocument.setTeamDescription(eventData.getDescription());
        createOrUpdateDocument(url, teamDocument);
    }

}
