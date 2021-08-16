package nl.kristalsoftware.association.team.domain.team;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TeamReferenceTest {

    @Test
    void isNullWithNullValue() {
        TeamReference teamReference = TeamReference.of((String) null);
        assertEquals(null, teamReference.getValue());
    }

    @Test
    void isNullWithEmptyStringValue() {
        TeamReference teamReference = TeamReference.of("");
        assertEquals(null, teamReference.getValue());
    }

    @Test
    void isNullWithStringOfZero() {
        TeamReference teamReference = TeamReference.of("0");
        assertEquals(null, teamReference.getValue());
    }

    @Test
    void throwsRuntimeExceptionWithInvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            TeamReference.of("123");
        });
    }

}
