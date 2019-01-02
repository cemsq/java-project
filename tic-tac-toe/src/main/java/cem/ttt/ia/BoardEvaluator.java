package cem.ttt.ia;

import cem.ttt.TicTacToeBoard;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.function.Function;

public class BoardEvaluator {

    private final List<Function<TicTacToeBoard, Integer>> evaluators = Lists.newArrayList();

    public BoardEvaluator() {
        evaluators.add(b -> checkRow(b, 0));
        evaluators.add(b -> checkRow(b, 1));
        evaluators.add(b -> checkRow(b, 2));
        evaluators.add(b -> checkColumn(b, 0));
        evaluators.add(b -> checkColumn(b, 1));
        evaluators.add(b -> checkColumn(b, 2));
        evaluators.add(this::checkPrimaryDiagonal);
        evaluators.add(this::checkSecondaryDiagonal);
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

    private int checkSecondaryDiagonal(TicTacToeBoard board) {
        return check(board, 2, 0, -1, 1);
    }

    private int checkPrimaryDiagonal(TicTacToeBoard board) {
        return check(board, 0, 0, 1, 1);
    }

    private int checkRow(TicTacToeBoard board, int iRow) {
        return check(board, iRow, 0, 0, 1);
    }

    private int checkColumn(TicTacToeBoard board, int iColumn) {
        return check(board, 0, iColumn, 1, 0);
    }

    private int check(TicTacToeBoard board, int iRow, int iColumn, int dRow, int dColumn) {

        board.value(iRow, iColumn);
        if (board.isEmpty(iRow, iColumn)) {
            return 0;
        }

        int row = iRow;
        int column = iColumn;
        int value = board.value(iRow, iColumn);
        for (int iteration = 1; iteration <= 2; iteration++) {
            row += dRow;
            column += dColumn;
            if (value != board.value(row, column)) {
                return 0;
            }
        }

        return value == 1? 1 : -1;
    }
}
