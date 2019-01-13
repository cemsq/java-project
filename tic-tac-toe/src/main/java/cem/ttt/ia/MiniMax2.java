package cem.ttt.ia;

import cem.ttt.Move;
import cem.ttt.TicTacToeBoard;

import java.util.List;

public class MiniMax2 implements IAStrategy {

    private final BoardEvaluator evaluator;

    public MiniMax2(BoardEvaluator evaluator) {
        this.evaluator = evaluator;
    }

    @Override
    public Move bestMove(TicTacToeBoard board) {
        Result result = minMax(board);
        return result.move;
    }

    private Result minMax(TicTacToeBoard board) {

        int value = evaluator.evaluate(board);
        List<Move> possibleMoves = board.getPossibleMoves();
        if (possibleMoves.isEmpty() || value != 0) {
            return new Result(value, null);
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        boolean maxNode = isMaxNode(board);
        Result best = new Result(0, null);
        for (Move move : possibleMoves) {

            board.applyMove(move);
            Result result = minMax(board);
            board.undoMove(move);

            if (maxNode) {
                if (result.value > max) {
                    max = result.value;
                    best.set(max, move);
                }
            } else if (result.value < min) {
                min = result.value;
                best.set(min, move);
            }
        }

        return best;
    }

    private static class Result {
        private int value;
        private Move move;

        private Result(int value, Move bestMove) {
            this.value = value;
            this.move = bestMove;
        }

        private void set(int value, Move move) {
            this.value = value;
            this.move = move;
        }
    }

    private boolean isMaxNode(TicTacToeBoard board) {
        return board.getCurrentPlayer().getPlayerValue() == 1;
    }
}
