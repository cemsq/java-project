package cem.ttt;

public class Player {

    private final PlayerType playerType;
    private final Stats stats = new Stats();

    public static Player pX() {
        return new Player(PlayerType.X);
    }

    public static Player pO() {
        return new Player(PlayerType.O);
    }

    private Player(PlayerType playerType) {
        this.playerType = playerType;
    }

    public int getPlayerValue() {
        return this.playerType.value();
    }

    public Stats getStats() {
        return stats;
    }
}
