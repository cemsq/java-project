package chess.moves;

import chess.Board;
import chess.Position;

import java.util.List;

/**
 *
 */
public interface MovementGenerator {

    List<Position> generate(Board board, Position initial);

    String getName();
}
