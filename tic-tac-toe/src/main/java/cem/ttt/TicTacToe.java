package cem.ttt;

import cem.ttt.gui.Header;
import cem.ttt.gui.JStats;
import cem.ttt.gui.listener.MouseReleased;
import cem.ttt.gui.listener.ResizeComponentListener;
import cem.ttt.ia.BoardEvaluator;
import cem.ttt.ia.IAStrategy;
import cem.ttt.ia.MiniMax2;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

@Slf4j
public class TicTacToe extends JPanel {

    private static final Color BOARD_COLOR = new Color(0, 15, 12);
    private static final double SQUARE_TEXT_PERCENT = .8;
    private static final int EMPTY_PANEL_SIZE = 100;

    private int labelSize;
    private static final BoardEvaluator evaluator = new BoardEvaluator();
    private final TicTacToeBoard ticTacToeBoard;
    private final JLabel [][] matrix = new JLabel[TicTacToeBoard.N][TicTacToeBoard.N];
    private IAStrategy iaStrategy;

    public static void main(String ...args) {
        Player pX = Player.pX();
        Player pO = Player.pO();

        TicTacToeBoard board = new TicTacToeBoard(pX, pO);
        TicTacToe ticTacToe = new TicTacToe(board, 300, 300, new MiniMax2(evaluator));

        JStats xStats = new JStats(pX, 150);
        JStats oStats = new JStats(pO, 150);

        JFrame jFrame = new JFrame();
        jFrame.setLayout(new BorderLayout());
        jFrame.setLocation(500, 100);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jFrame.add(new Header(100), BorderLayout.NORTH);
        jFrame.add(ticTacToe, BorderLayout.CENTER);
        jFrame.add(emptyPanel(), BorderLayout.WEST);
        jFrame.add(emptyPanel(), BorderLayout.EAST);
        jFrame.add(emptyPanel(), BorderLayout.SOUTH);

        jFrame.pack();
        jFrame.setVisible(true);
    }

    private static JPanel emptyPanel() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(EMPTY_PANEL_SIZE, EMPTY_PANEL_SIZE));
        panel.setVisible(true);
        return panel;
    }

    private TicTacToe(TicTacToeBoard board, int with, int height, IAStrategy iaStrategy) {
        this.ticTacToeBoard = board;
        this.iaStrategy = iaStrategy;
        this.setBackground(BOARD_COLOR);
        this.setPreferredSize(new Dimension(with, height));
        this.setLayout(new GridLayout(TicTacToeBoard.N, TicTacToeBoard.N, 1, 1));
        this.setVisible(true);

        this.addComponentListener(new ResizeComponentListener(e -> this.resizeLabelText()));
        this.addMouseListener(new MouseReleased(e -> {
            int squareWith = this.getWidth() / TicTacToeBoard.N;
            int squareHeight = this.getHeight() / TicTacToeBoard.N;

            int row = e.getY() / squareHeight;
            int column = e.getX() / squareWith;

            if (board.isEmpty(row, column)) {
                Move move = new Move(row, column);
                boolean gameOver = makeMove(board, move);

                if (!gameOver) {
                    move = iaStrategy.bestMove(board);
                    makeMove(board, move);
                }
            }
        }));

        Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);
        for (int row = 0; row < TicTacToeBoard.N; row++) {
            for (int column = 0; column < TicTacToeBoard.N; column++) {
                JLabel jLabel = new JLabel();
                jLabel.setBorder(border);
                jLabel.setHorizontalAlignment(JLabel.CENTER);
                jLabel.setVerticalAlignment(JLabel.CENTER);

                matrix[row][column] = jLabel;
                this.add(jLabel, BorderLayout.CENTER);
            }
        }
    }

    private boolean makeMove(TicTacToeBoard board, Move move) {
        log.info("Player {} moves: {}", board.getCurrentPlayer().getPlayerValue(), move);
        board.applyMove(move);
        repaintBoard();

        return checkGameOver();
    }

    private boolean checkGameOver() {
        int value = evaluator.evaluate(this.ticTacToeBoard);

        if (value == 1) {
            showDialogAndReset("Player 1 wins!!!");
            return true;

        } else if (value == -1) {
            showDialogAndReset("Player 2 wins!!!");
            return true;

        } else if (this.ticTacToeBoard.getPossibleMoves().isEmpty()) {
            showDialogAndReset("Draw :(");
            return true;
        }

        return false;
    }

    private void showDialogAndReset(String text) {
        log.info(text);
        JOptionPane.showMessageDialog(this, text);
        this.ticTacToeBoard.reset();
        this.repaintBoard();
    }

    private void repaintBoard() {
        log.debug("Repainting Board...");

        int newLabelSize = getNewLabelSize();
        boolean resizeLabel = newLabelSize != this.labelSize;
        if (resizeLabel) {
            this.labelSize = newLabelSize;
        }

        for (int row = 0; row < TicTacToeBoard.N; row++) {
            for (int column = 0; column < TicTacToeBoard.N; column++) {

                JLabel jLabel = matrix[row][column];
                switch (ticTacToeBoard.value(row, column)) {
                    case 0: setLabelText(jLabel, "", null);
                        break;

                    case 1: setLabelText(jLabel, "X", Color.RED);
                        break;

                    case 2: setLabelText(jLabel, "O", Color.BLUE);
                        break;
                }
            }
        }
    }

    private void resizeLabelText() {
        log.info("Resizing text...");

        int newLabelSize = getNewLabelSize();
        if (newLabelSize == this.labelSize) {
            return;
        }

        this.labelSize = newLabelSize;
        for (int row = 0; row < TicTacToeBoard.N; row++) {
            for (int column = 0; column < TicTacToeBoard.N; column++) {

                JLabel jLabel = matrix[row][column];
                jLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, newLabelSize));
            }
        }
    }

    private int getNewLabelSize() {
        JLabel jLabel = matrix[0][0];
        return (int)Math.floor(Math.min(jLabel.getWidth(), jLabel.getHeight()) * SQUARE_TEXT_PERCENT);
    }

    private void setLabelText(JLabel jLabel, String text, Color color) {
        jLabel.setText(text);
        jLabel.setForeground(color);
    }

}
