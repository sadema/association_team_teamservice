package nl.kristalsoftware.association.team.domain.team;

import nl.kristalsoftware.domain.base.TinyUUIDType;
import nl.kristalsoftware.domain.base.annotations.ValueObject;

import java.util.UUID;

@ValueObject
public class TeamReference extends TinyUUIDType {

    private TeamReference(UUID value) {
        super(value);
    }

    public static TeamReference of(UUID value) {
        return new TeamReference(value);
    }

    public static TeamReference of(String value) {
        UUID uuid = null;
        if (value != null && !value.isEmpty() && !value.equals("0")) {
            uuid = UUID.fromString(value);
        }
        return new TeamReference(uuid);
    }

}

