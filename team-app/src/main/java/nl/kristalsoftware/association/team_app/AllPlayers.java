package nl.kristalsoftware.association.team_app;

import lombok.Data;

import java.util.List;

@Data
public class AllPlayers {

    private List<Player> rows;

    @Data
    public static class Player {
        private PlayerValue value;
    }

    @Data
    public static class PlayerValue {
        private String _id;
        private String _rev;
    }
}
