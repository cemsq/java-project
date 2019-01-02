package cem.ttt.ia;

import cem.ttt.Move;
import cem.ttt.TicTacToeBoard;

import java.util.List;

public class DummyIA implements IAStrategy {

    @Override
    public Move bestMove(TicTacToeBoard board) {
        List<Move> moves = board.getPossibleMoves();
        if (moves.isEmpty()) {
            throw new IllegalArgumentException("no moves");
        }

        return moves.get(0);
    }
}
