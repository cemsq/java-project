package chess.piece;

import chess.Board;
import chess.Position;
import chess.moves.MovementGenerator;
import chess.moves.Movements;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Piece {
    private String name;
    private MovementGenerator movementGenerator;

    public static Piece king() {
        return new Piece("Kink", Movements.king());
    }

    public static Piece queen() {
        return new Piece("Queen", Movements.queen());
    }

    public static Piece bishop() {
        return new Piece("Bishop", Movements.diagonal());
    }

    public static Piece tower() {
        return new Piece("Tower", Movements.cross());
    }

    public static Piece horse() {
        return new Piece("Horse", Movements.horse());
    }

    public Piece(String name, MovementGenerator movementGenerator) {
        this.name = name;
        this.movementGenerator = movementGenerator;
    }

    public String getName() {
        return name;
    }

    public boolean move(Board board, Position initial, Position destiny) {
        List<Position> positions = generate(board, initial);

        return positions.contains(destiny);
    }

    public List<Position> generate(Board board, Position initial) {
        return movementGenerator != null? movementGenerator.generate(board, initial) : Lists.newArrayList();
    }

}
