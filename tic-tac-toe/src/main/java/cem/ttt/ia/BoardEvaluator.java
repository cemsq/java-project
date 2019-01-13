package cem.ttt.ia;

import cem.ttt.TicTacToeBoard;
import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.function.Function;

public class BoardEvaluator {

    private static final List<Function<TicTacToeBoard, Integer>> evaluators;
    static {
        evaluators = ImmutableList.<Function<TicTacToeBoard, Integer>>builder()
                .add(b -> checkRow(b, 0))
                .add(b -> checkRow(b, 1))
                .add(b -> checkRow(b, 2))
                .add(b -> checkColumn(b, 0))
                .add(b -> checkColumn(b, 1))
                .add(b -> checkColumn(b, 2))
                .add(BoardEvaluator::checkPrimaryDiagonal)
                .add(BoardEvaluator::checkSecondaryDiagonal)
                .build();
    }

    public BoardEvaluator() {

    }

    public int evaluate(TicTacToeBoard board) {
        for (Function<TicTacToeBoard, Integer> evaluator : evaluators) {
            Integer value = evaluator.apply(board);
            if (value != 0) {
                return value;
            }
        }

        return 0;
    }

    private static int checkRow(TicTacToeBoard board, int initRow) { return check(board, initRow, 0, 0, 1); }

    private static int checkColumn(TicTacToeBoard board, int initColumn) { return check(board, 0, initColumn, 1, 0); }

    private static int checkPrimaryDiagonal(TicTacToeBoard board) { return check(board, 0, 0, 1, 1); }

    private static int checkSecondaryDiagonal(TicTacToeBoard board) { return check(board, 2, 0, -1, 1); }

    private static int check(TicTacToeBoard board, int initRow, int initColumn, int deltaRow, int deltaColumn) {

        if (board.isEmpty(initRow, initColumn)) {
            return 0;
        }

        int row = initRow;
        int column = initColumn;
        int playerValue = board.value(initRow, initColumn);
        for (int iteration = 0; iteration < 2; iteration++) {
            row += deltaRow;
            column += deltaColumn;
            if (playerValue != board.value(row, column)) {
                return 0;
            }
        }

        return playerValue == 1? 1 : -1;
    }
}
