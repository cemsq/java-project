package cem.ttt;

public class Stats {

    private int wins;
    private int draws;
    private int defeats;

    public void reset() {
        this.wins = 0;
        this.draws = 0;
        this.defeats = 0;
    }

    public void wins() {
        this.wins++;
    }

    public void draws() {
        this.draws++;
    }

    public void looses() {
        this.defeats++;
    }

    public int getWins() {
        return wins;
    }

    public int getDraws() {
        return draws;
    }

    public int getDefeats() {
        return defeats;
    }
}
