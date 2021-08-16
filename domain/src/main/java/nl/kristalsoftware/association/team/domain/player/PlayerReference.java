package nl.kristalsoftware.association.team.domain.player;

import nl.kristalsoftware.domain.base.TinyUUIDType;
import nl.kristalsoftware.domain.base.annotations.ValueObject;

import java.util.UUID;

@ValueObject
public class PlayerReference extends TinyUUIDType {

    private PlayerReference(UUID value) {
        super(value);
    }

    public static PlayerReference of(UUID value) {
        return new PlayerReference(value);
    }

    public static PlayerReference of(String value) {
        UUID uuid = null;
        if (value != null && !value.isEmpty() && !value.equals("0")) {
            uuid = UUID.fromString(value);
        }
        return new PlayerReference(uuid);
    }

}
