package nl.kristalsoftware.association.team.rest.team;

import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.association.team.domain.team.TeamReference;
import nl.kristalsoftware.association.team.domain.team.TeamService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RestController
public class TeamController {

    @Value("${team.datastore.viewstore.url}")
    private String url;

    private final TeamService teamService;

    @PostMapping(value = "/teams", consumes = "application/json")
    public ResponseEntity<Void> createTeam(@RequestBody TeamData teamData) {
        TeamReference teamReference = teamService.registerTeam(
                teamData.getName(),
                teamData.getCategory(),
                teamData.getDescription()
        );
        return ResponseEntity.created(URI.create(url + teamReference.getValue().toString())).build();
    }
}
