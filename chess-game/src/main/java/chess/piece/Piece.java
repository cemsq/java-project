package chess.piece;

import chess.Board;
import chess.Position;
import chess.moves.MovementGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public abstract class Piece implements MovementGenerator {
    List<MovementGenerator> movements = new ArrayList<>();

    public Piece(MovementGenerator movement, MovementGenerator...movements) {
        addMovement(movement);

        for (MovementGenerator move : movements) {
            addMovement(move);
        }
    }

    public boolean move(Board board, Position initial, Position destiny) {
        List<Position> positions = generate(board, initial);

        return positions.contains(destiny);
    }

    @Override
    public List<Position> generate(Board board, Position initial) {
        List<Position> positions = new ArrayList<>();
        for (MovementGenerator movement : movements) {
            positions.addAll(movement.generate(board, initial));
        }
        return positions;
    }

    public void addMovement(MovementGenerator move) {
        if (move != null) {
            this.movements.add(move);
        }
    }
}
