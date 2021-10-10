## TeamService events

### Consumerende events

| Event | Fields |
|-------|-------------|
| MemberSignedUp | 
| MemberPropertiesUpdated |

### Producerende events
| Command | Event | PlayerEventData | API |
|---------|-------|--------|-----|
| | PlayerSignedUp |
| | PlayerPropertiesUpdated | firstName, lastName |
| | PlayerRoleAssigned |  |
| MakePlayerMemberOfTeam |PlayerAddedToTeam | teamReference
| MakePlayerMemberOfTeam | PlayerMovedToAnotherTeam | teamReference
| MakePlayerMemberOfTeam | PlayerDetachedFromTeam | teamReference

| Command | Event | TeamEventData |
| ------- | ----- | ------------- |
| RegisterTeam | TeamRegistered |
| EditTeam | TeamEdited |

## Event field mappings

| PlayerEventData | Player<br>Aggregate | Rest<br>PlayerData | CQRS |
|--------------| -------- | ----- | ----- |
| reference | reference | | id |
| firstName<br>lastName| playerName | | firstName |
| birthDate | playerBirthDate | | lastName |
| role | playerRole | playerRole | playerRole | 
| teamReference | teamReference | teamReference | team_reference

