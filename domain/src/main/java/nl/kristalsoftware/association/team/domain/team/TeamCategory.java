package nl.kristalsoftware.association.team.domain.team;

import nl.kristalsoftware.domain.base.TinyStringType;
import nl.kristalsoftware.domain.base.annotations.ValueObject;

@ValueObject
public class TeamCategory extends TinyStringType {

    private TeamCategory(String value) {
        super(value);
    }

    public static TeamCategory of(String value) {
        return new TeamCategory(value);
    }

}
