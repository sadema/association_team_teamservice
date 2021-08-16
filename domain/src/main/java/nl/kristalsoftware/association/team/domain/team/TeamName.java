package nl.kristalsoftware.association.team.domain.team;

import nl.kristalsoftware.domain.base.TinyStringType;
import nl.kristalsoftware.domain.base.annotations.ValueObject;

@ValueObject
public class TeamName extends TinyStringType {

    private TeamName(String value) {
        super(value);
    }

    public static TeamName of(String value) {
        return new TeamName(value);
    }
}
