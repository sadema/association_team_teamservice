package nl.kristalsoftware.association.team.rest.team;

import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.association.team.domain.team.TeamCategory;
import nl.kristalsoftware.association.team.domain.team.TeamDescription;
import nl.kristalsoftware.association.team.domain.team.TeamName;
import nl.kristalsoftware.association.team.domain.team.TeamReference;
import nl.kristalsoftware.association.team.domain.team.TeamService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.UUID;

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

    @PutMapping(value = "/teams/{teamReference}", consumes = "application/json")
    public ResponseEntity<Void> editTeam(@RequestBody TeamData teamData, @PathVariable String teamReference) {
        teamService.editTeam(
                TeamReference.of(UUID.fromString(teamReference)),
                TeamName.of(teamData.getName()),
                TeamCategory.of(teamData.getCategory()),
                TeamDescription.of(teamData.getDescription())
        );
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
