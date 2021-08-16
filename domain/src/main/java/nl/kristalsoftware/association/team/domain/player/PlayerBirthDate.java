package nl.kristalsoftware.association.team.domain.player;

import nl.kristalsoftware.domain.base.TinyDateType;

import java.time.LocalDate;

public class PlayerBirthDate extends TinyDateType {

    private PlayerBirthDate(LocalDate localDate) {
        super(localDate);
    }

    public static PlayerBirthDate of(LocalDate localDate) {
        return new PlayerBirthDate(localDate);
    }

}
