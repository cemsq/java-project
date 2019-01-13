package cem.ttt;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.StringJoiner;

public class TicTacToeBoard {

    public static final int N = 3;
    private final int [][] matrix = new int[N][N];
    private Player currentPlayer;
    private Player opponent;
    private final Player pX;
    private final Player pO;

    public TicTacToeBoard(Player pX, Player pO) {
        this.pX = pX;
        this.pO = pO;
        this.currentPlayer = pX;
        this.opponent = pO;
    }

    public void reset() {
        this.currentPlayer = this.pX;
        this.opponent = this.pO;

        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {
                put(row, column, 0);
            }
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    private void switchPLayer() {
        Player temp = this.currentPlayer;
        this.currentPlayer = this.opponent;
        this.opponent = temp;
    }

    public void applyMove(Move move) {
        if (isValid(move.getRow(), move.getColumn()) && isEmpty(move.getRow(), move.getColumn())) {
            put(move.getRow(), move.getColumn(), getCurrentPlayer().getPlayerValue());
            switchPLayer();
        }
    }

    public void undoMove(Move move) {
        validate(move.getRow(), move.getColumn());

        if (isEmpty(move.getRow(), move.getColumn())) {
            throw new IllegalArgumentException("Position is not empty: " + move);
        }

        put(move.getRow(), move.getColumn(), 0);
        switchPLayer();
    }

    private void validate(int row, int column) {
        if (!isValid(row, column)) {
            throw new IllegalArgumentException(String.format("Wrong position: [%s, %s]", row, column));
        }
    }

    public boolean isEmpty(int row, int column) {
        return matrix[row][column] == 0;
    }

    public boolean isValid(int row, int column) {
        return row >= 0 && row < N && column >= 0 && column < N;
    }

    public int value(int row, int column) {
        return this.matrix[row][column];
    }

    private void put(int row, int column, int value) {
        matrix[row][column] = value;
    }

    public List<Move> getPossibleMoves() {

        List<Move> moves = Lists.newArrayList();
        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {
                if (isEmpty(row, column)) {
                    Move move = new Move(row, column);
                    moves.add(move);
                }
            }
        }

        return moves;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");
        for (int row = 0; row < N; row++) {
            StringBuilder sb = new StringBuilder();
            for (int column = 0; column < N; column++) {
                if (isEmpty(row, column)) {
                    sb.append("[ ]");

                } else if (value(row, column) == 1) {
                    sb.append("[X]");
                } else {
                    sb.append("[O]");
                }
            }

            joiner.add(sb);
        }

        return joiner.toString();
    }
}
