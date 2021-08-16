package nl.kristalsoftware.association.team.domain.player;

import nl.kristalsoftware.domain.base.TinyStringType;
import nl.kristalsoftware.domain.base.annotations.ValueObject;

@ValueObject
public class PlayerName extends TinyStringType {

    private PlayerName(String value) {
        super(value);
    }

    public static PlayerName of(String firstName, String lastName) {
        return new PlayerName(firstName + " " + lastName);
    }

}
