package cem.ttt;

public class Move {

    private int row;
    private int column;
    private int id;

    public Move(int row, int column) {
        this.row = row;
        this.column = column;
        this.id = this.row * TicTacToeBoard.N + column;
    }

    public int getId() {
        return id;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s]", row, column);
    }
}
