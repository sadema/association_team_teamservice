package nl.kristalsoftware.association.team.domain.team;

import nl.kristalsoftware.association.team.TeamEventData;
import nl.kristalsoftware.association.team.domain.team.command.RegisterTeam;
import nl.kristalsoftware.association.team.domain.team.event.TeamRegistered;
import nl.kristalsoftware.domain.base.Aggregate;
import nl.kristalsoftware.domain.base.BaseAggregateRoot;
import nl.kristalsoftware.domain.base.BaseEvent;
import nl.kristalsoftware.domain.base.annotations.AggregateRoot;
import org.springframework.context.ApplicationEventPublisher;

@AggregateRoot
public class Team extends BaseAggregateRoot<TeamReference, BaseEvent<TeamEventData>> implements Aggregate {

    private TeamName teamName;

    private TeamCategory teamCategory;

    private TeamDescription teamDescription;

    private Team(TeamReference teamReference, ApplicationEventPublisher eventPublisher) {
        super(teamReference, eventPublisher);
    }

    public static Team of(TeamReference teamReference, ApplicationEventPublisher eventPublisher) {
        return new Team(teamReference, eventPublisher);
    }

    public void handleCommand(RegisterTeam command) {
        TeamEventData teamEventData = TeamEventData.newBuilder()
                .setReference(getReference().getValue().toString())
                .setName(command.getTeamName())
                .setCategory(command.getTeamCategory())
                .setDescription(command.getTeamDescription())
                .build();
        sendEvent(new TeamRegistered(teamEventData));
    }

}
