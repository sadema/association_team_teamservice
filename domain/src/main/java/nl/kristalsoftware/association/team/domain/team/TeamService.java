package nl.kristalsoftware.association.team.domain.team;

import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.association.team.domain.team.command.EditTeam;
import nl.kristalsoftware.association.team.domain.team.command.RegisterTeam;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TeamService {

    private final TeamDataStore teamDataStore;

    private final ApplicationEventPublisher eventPublisher;

    public TeamReference registerTeam(
            String teamName,
            String teamCategory,
            String teamDescription) {
        Team team = teamDataStore.loadAggregate(eventPublisher);
        team.handleCommand(RegisterTeam.of(teamName, teamCategory, teamDescription));
        return team.getReference();
    }

    public void editTeam(
            TeamReference teamReference,
            TeamName teamName,
            TeamCategory teamCategory,
            TeamDescription teamDescription) {
        Team team = teamDataStore.loadAggregate(eventPublisher);
        team.handleCommand(EditTeam.of(teamName, teamCategory, teamDescription));
    }
}
