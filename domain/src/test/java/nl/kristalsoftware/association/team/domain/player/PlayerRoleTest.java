package nl.kristalsoftware.association.team.domain.player;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@SpringBootTest
class PlayerRoleTest {

    private final static String EMPTY_STRING = "";
    private final static String NON_EXISTING_ROLE = "NONEXISTING";
    private final static String DEFENDER = "DEFENDER";

    @Test
    void isEmptyWithEmptyRole() {
        PlayerRole playerRole = PlayerRole.of(EMPTY_STRING);
        assertEquals(PlayerRole.Role.UNKNOWN, playerRole.getValue());
    }

    @Test
    void isEmptyWithNotExistingRole() {
        PlayerRole playerRole = PlayerRole.of(NON_EXISTING_ROLE);
        assertEquals(PlayerRole.Role.UNKNOWN, playerRole.getValue());
    }

    @Test
    void isEmptyWithNullValue() {
        PlayerRole playerRole = PlayerRole.of((String) null);
        assertEquals(PlayerRole.Role.UNKNOWN, playerRole.getValue());
    }

    @Test
    void testDefenderRole() {
        PlayerRole playerRole = PlayerRole.of(DEFENDER);
        assertEquals(PlayerRole.Role.DEFENDER, playerRole.getValue());
    }

    @Test
    void testEquals() {
        PlayerRole playerRole = PlayerRole.of(DEFENDER);
        PlayerRole newPlayerRole = PlayerRole.of(DEFENDER);
        assertTrue(newPlayerRole.equals(playerRole));
    }

}
