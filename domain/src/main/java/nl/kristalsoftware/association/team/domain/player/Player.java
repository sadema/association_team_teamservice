package nl.kristalsoftware.association.team.domain.player;

import lombok.Getter;
import nl.kristalsoftware.association.team.PlayerEventData;
import nl.kristalsoftware.association.team.domain.player.command.AssignPlayerRole;
import nl.kristalsoftware.association.team.domain.player.command.MakePlayerMemberOfTeam;
import nl.kristalsoftware.association.team.domain.player.command.SignUpPlayer;
import nl.kristalsoftware.association.team.domain.player.command.StopPlaying;
import nl.kristalsoftware.association.team.domain.player.command.UpdatePlayerProperties;
import nl.kristalsoftware.association.team.domain.player.event.player_added_to_team.PlayerAddedToTeam;
import nl.kristalsoftware.association.team.domain.player.event.player_added_to_team.PlayerAddedToTeamEventData;
import nl.kristalsoftware.association.team.domain.player.event.player_detached_from_team.PlayerDetachedFromTeam;
import nl.kristalsoftware.association.team.domain.player.event.player_detached_from_team.PlayerDetachedFromTeamEventData;
import nl.kristalsoftware.association.team.domain.player.event.player_moved_to_another_team.PlayerMovedToAnotherTeam;
import nl.kristalsoftware.association.team.domain.player.event.player_moved_to_another_team.PlayerMovedToAnotherTeamEventData;
import nl.kristalsoftware.association.team.domain.player.event.player_properties_updated.PlayerPropertiesUpdated;
import nl.kristalsoftware.association.team.domain.player.event.player_properties_updated.PlayerPropertiesUpdatedEventData;
import nl.kristalsoftware.association.team.domain.player.event.player_role_assigned.PlayerRoleAssigned;
import nl.kristalsoftware.association.team.domain.player.event.player_role_assigned.PlayerRoleAssignedEventData;
import nl.kristalsoftware.association.team.domain.player.event.player_signed_up.PlayerSignedUp;
import nl.kristalsoftware.association.team.domain.player.event.player_signed_up.PlayerSignedUpEventData;
import nl.kristalsoftware.association.team.domain.player.event.player_stopped.PlayerStopped;
import nl.kristalsoftware.association.team.domain.player.event.player_stopped.PlayerStoppedEventData;
import nl.kristalsoftware.association.team.domain.team.TeamReference;
import nl.kristalsoftware.domain.base.Aggregate;
import nl.kristalsoftware.domain.base.BaseAggregateRoot;
import nl.kristalsoftware.domain.base.BaseEvent;
import nl.kristalsoftware.domain.base.annotations.AggregateRoot;
import org.springframework.context.ApplicationEventPublisher;

import java.util.UUID;

@Getter
@AggregateRoot
public class Player extends BaseAggregateRoot<PlayerReference, BaseEvent<PlayerEventData>> implements Aggregate {

    private PlayerName playerName;

    private PlayerBirthDate playerBirthDate;

    private TeamReference teamReference;

    private PlayerRole playerRole;

    private Player(PlayerReference reference, ApplicationEventPublisher eventPublisher) {
        super(reference, eventPublisher);
    }

    public static Player of(PlayerReference reference, ApplicationEventPublisher eventPublisher) {
        return new Player(reference, eventPublisher);
    }

    public void loadData(PlayerSignedUpEventData playerSignedUpEventData) {
        playerName = playerSignedUpEventData.getPlayerName();
        playerBirthDate = playerSignedUpEventData.getPlayerBirthDate();
        teamReference = TeamReference.of((UUID) null);
        playerRole = PlayerRole.of(PlayerRole.Role.UNKNOWN);
    }

    public void loadData(PlayerAddedToTeamEventData playerAddedToTeamEventData) {
        teamReference = playerAddedToTeamEventData.getTeamReference();
    }

    public void loadData(PlayerMovedToAnotherTeamEventData playerMovedToAnotherTeamEventData) {
        teamReference = playerMovedToAnotherTeamEventData.getTeamReference();
    }

    public void loadData(PlayerDetachedFromTeamEventData playerDetachedFromTeamEventData) {
        teamReference = TeamReference.of((UUID) null);
    }

    public void loadData(PlayerRoleAssignedEventData playerRoleAssignedEventData) {
        playerRole = playerRoleAssignedEventData.getPlayerRole();
    }

    public void loadData(PlayerPropertiesUpdatedEventData playerSignedUpEventData) {
        playerName = playerSignedUpEventData.getPlayerName();
        playerBirthDate = playerSignedUpEventData.getPlayerBirthDate();
    }

    public void loadData(PlayerStoppedEventData playerStoppedEventData) {
        // TODO: set deleted ???
    }

    public void handleCommand(SignUpPlayer command) {
        if (command.getKind().equals("PLAYER")) {
            PlayerEventData playerEventData = PlayerEventData.newBuilder()
                    .setReference(getReference().getStringValue())
                    .setFirstName(command.getFirstName())
                    .setLastName(command.getLastName())
                    .setBirthDate(command.getBirthDate())
                    .build();
            sendEvent(new PlayerSignedUp(playerEventData));
        }
    }

    public void handleCommand(MakePlayerMemberOfTeam command) {
        PlayerEventData playerEventData = PlayerEventData.newBuilder()
                .setReference(getReference().getStringValue())
                .setTeamReference(command.getTeamReference().getStringValue())
                .build();
        if (isPlayerAlreadyMemberOfTeam() && !hasNewTeam(command.getTeamReference())) {
            sendEvent(new PlayerDetachedFromTeam(playerEventData));
        }
        else {
            if (isPlayerNotMemberOfAnyTeam() && hasNewTeam(command.getTeamReference())) {
                sendEvent(new PlayerAddedToTeam(playerEventData));
            } else {
                if (isPlayerAlreadyMemberOfTeam() && hasNewTeam(command.getTeamReference())) {
                    sendEvent(new PlayerMovedToAnotherTeam(playerEventData));
                }
            }
        }
    }

    public void handleCommand(AssignPlayerRole command) {
        if (!playerRole.equals(command.getPlayerRole())) {
            PlayerEventData playerEventData = PlayerEventData.newBuilder()
                    .setReference(getReference().getStringValue())
                    .setRole(command.getPlayerRole().getValue().toString())
                    .build();
            sendEvent(new PlayerRoleAssigned(playerEventData));
        }
    }

    public void handleCommand(UpdatePlayerProperties command) {
        PlayerEventData playerEventData = PlayerEventData.newBuilder()
                .setReference(getReference().getStringValue())
                .build();
        if (command.getFirstName() != null) playerEventData.setFirstName(command.getFirstName());
        if (command.getLastName() != null) playerEventData.setLastName(command.getLastName());
        if (command.getBirthDate() != null) playerEventData.setBirthDate(command.getBirthDate());
        sendEvent(new PlayerPropertiesUpdated(playerEventData));
    }

    public void handleCommand(StopPlaying command) {
        PlayerEventData playerEventData = PlayerEventData.newBuilder()
                .setReference(getReference().getStringValue())
                .build();
        sendEvent(new PlayerStopped(playerEventData));
    }

    private boolean isPlayerAlreadyMemberOfTeam() {
        return !teamReference.isEmpty();
    }

    private boolean hasNewTeam(TeamReference teamReference) {
        return !teamReference.isEmpty() && !this.teamReference.equals(teamReference);
    }

    private boolean isPlayerNotMemberOfAnyTeam() {
        return teamReference.isEmpty();
    }

}
