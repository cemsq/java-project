package cem.ttt.ia;

import cem.ttt.Move;
import cem.ttt.TicTacToeBoard;

import java.util.List;

public class MiniMax implements IAStrategy {

    private final BoardEvaluator evaluator;

    public MiniMax(BoardEvaluator evaluator) {
        this.evaluator = evaluator;
    }

    @Override
    public Move bestMove(TicTacToeBoard board) {

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        boolean maxNode = isMaxNode(board);

        Move bestMove = null;
        for (Move move : board.getPossibleMoves()) {
            board.makeMove(move);

            int value = minMax(board);
            if (maxNode) {
                if (value > max) {
                    max = value;
                    bestMove = move;
                }
            } else {
                if (value < min) {
                    min = value;
                    bestMove = move;
                } else if (value == min && random()) {
                    bestMove = move;
                }
            }

            board.undoMove(move);
        }

        return bestMove;
    }

    private boolean random() {
        return Math.random() >= 0.5;
    }

    private int minMax(TicTacToeBoard board) {
        int value = evaluator.evaluate(board);
        if (value != 0) {
            return value;
        }

        List<Move> possibleMoves = board.getPossibleMoves();
        if (possibleMoves.isEmpty()) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        boolean maxNode = isMaxNode(board);
        for (Move move : board.getPossibleMoves()) {

            board.makeMove(move);
            int miniMax = minMax(board);
            board.undoMove(move);

            if (maxNode) {
                max = Math.max(max, miniMax);
            } else {
                min = Math.min(min, miniMax);
            }
        }

        return maxNode? max : min;
    }

    private boolean isMaxNode(TicTacToeBoard board) {
        return board.getCurrentPlayer().getPlayerValue() == 1;
    }
}
