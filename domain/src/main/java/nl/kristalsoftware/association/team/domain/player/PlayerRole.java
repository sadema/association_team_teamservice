package nl.kristalsoftware.association.team.domain.player;

import lombok.Getter;
import nl.kristalsoftware.domain.base.TinyType;
import nl.kristalsoftware.domain.base.annotations.ValueObject;

@ValueObject
public class PlayerRole extends TinyType<PlayerRole.Role> {

    public enum Role
    {
        UNKNOWN, KEEPER, DEFENDER, MIDFIELD, STRIKER;
    }

    protected PlayerRole(Role value) {
        super(value);
    }

    public static PlayerRole of(String value) {
        if (value != null) {
            return new PlayerRole(getRole(value));
        }
        return new PlayerRole(Role.UNKNOWN);
    }

    private static Role getRole(String value) {
        PlayerRole.Role role;
        try {
            role = Role.valueOf(value);
        }
        catch (IllegalArgumentException iae) {
            role = Role.UNKNOWN;
        }
        return role;
    }

    public static PlayerRole of(Role role) {
        return new PlayerRole(role);
    }

    @Override
    public Boolean isEmpty() {
        return getValue() == null || getValue().equals(Role.UNKNOWN);
    }

}
