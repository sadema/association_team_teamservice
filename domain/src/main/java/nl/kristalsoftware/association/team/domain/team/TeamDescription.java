package nl.kristalsoftware.association.team.domain.team;

import nl.kristalsoftware.domain.base.TinyStringType;
import nl.kristalsoftware.domain.base.annotations.ValueObject;

@ValueObject
public class TeamDescription extends TinyStringType {

    private TeamDescription(String value) {
        super(value);
    }

    public static TeamDescription of(String value) {
        return new TeamDescription(value);
    }
    
}
