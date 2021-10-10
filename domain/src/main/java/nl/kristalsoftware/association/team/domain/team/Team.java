package nl.kristalsoftware.association.team.domain.team;

import nl.kristalsoftware.association.team.TeamEventData;
import nl.kristalsoftware.association.team.domain.team.command.EditTeam;
import nl.kristalsoftware.association.team.domain.team.command.RegisterTeam;
import nl.kristalsoftware.association.team.domain.team.event.team_edited.TeamEdited;
import nl.kristalsoftware.association.team.domain.team.event.team_edited.TeamEditedEventData;
import nl.kristalsoftware.association.team.domain.team.event.team_registered.TeamRegistered;
import nl.kristalsoftware.association.team.domain.team.event.team_registered.TeamRegisteredEventData;
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

    public void loadData(TeamRegisteredEventData teamRegisteredEventData) {
        teamName = teamRegisteredEventData.getTeamName();
        teamCategory = teamRegisteredEventData.getTeamCategory();
        teamDescription = teamRegisteredEventData.getTeamDescription();
    }

    public void loadData(TeamEditedEventData teamEditedEventData) {
        teamName = teamEditedEventData.getTeamName();
        teamCategory = teamEditedEventData.getTeamCategory();
        teamDescription = teamEditedEventData.getTeamDescription();
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

    public void handleCommand(EditTeam command) {
        if (!(command.getTeamName().equals(teamName) &&
                command.getTeamCategory().equals(teamCategory) &&
                command.getTeamDescription().equals(teamDescription))
        ) {
            TeamEventData teamEventData = TeamEventData.newBuilder()
                    .setReference(getReference().getStringValue())
                    .setName(command.getTeamName().getValue())
                    .setCategory(command.getTeamCategory().getValue())
                    .setDescription(command.getTeamDescription().getValue())
                    .build();
            sendEvent(new TeamEdited(teamEventData));
        }
    }

}
