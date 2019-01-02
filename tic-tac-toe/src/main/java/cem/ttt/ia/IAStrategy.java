package cem.ttt.ia;

import cem.ttt.Move;
import cem.ttt.TicTacToeBoard;

public interface IAStrategy {

    Move bestMove(TicTacToeBoard board);
}
