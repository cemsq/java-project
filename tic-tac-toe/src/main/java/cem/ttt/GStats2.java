package cem.ttt;

import javax.swing.*;
import java.awt.*;

public class GStats2 extends JPanel {

    private final StatLabel jWins = new StatLabel("Wins");
    private final StatLabel jDraws = new StatLabel("Draws");
    private final StatLabel jDefeats = new StatLabel("Defeats");

    public GStats2(Player player, int width) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(width, this.getHeight()));

        JLabel playerInfo = new JLabel();
        playerInfo.setText("Player: " + player.getPlayerValue());

        this.add(playerInfo);
        this.add(this.jWins);
        this.add(this.jDraws);
        this.add(this.jDefeats);
        this.setVisible(true);
        this.update();
    }

    public void update() {
        this.jWins.update();
        this.jDraws.update();
        this.jDefeats.update();
    }

    public void win() {
        this.jWins.increment();
    }

    public void loose() {
        this.jDefeats.increment();
    }

    public void draw() {
        this.jDraws.increment();
    }

    private class StatLabel extends JLabel {
        private int value;
        private final String text;

        public StatLabel(String text) {
            this.text = text;
        }

        private void increment() {
            this.value++;
            update();
        }

        private void update() {
            this.setText(text + ": " + value);
        }
    }
}
