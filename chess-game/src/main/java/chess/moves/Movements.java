package chess.moves;

import chess.Board;
import chess.InstanceHolder;
import chess.Position;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Movements {

    public static MovementGenerator cross() {
        return InstanceHolder.instance(Cross.class);
    }

    public static MovementGenerator diagonal() {
        return InstanceHolder.instance(Diagonal.class);
    }

    public static MovementGenerator horse() {
        return InstanceHolder.instance(LMovement.class);
    }

    public static MovementGenerator king() {
        return InstanceHolder.instance(KingMove.class);
    }

    private static class Cross implements MovementGenerator {
        public Cross() {
        }

        @Override
        public List<Position> generate(Board board, Position initial) {
            List<Position> list = new ArrayList<>();

            // up
            list.addAll(board.searchByIncrement(initial, -1, 0));
            // down
            list.addAll(board.searchByIncrement(initial, 1, 0));
            // right
            list.addAll(board.searchByIncrement(initial, 0, 1));
            // left
            list.addAll(board.searchByIncrement(initial, 0, -1));

            return list;
        }

        @Override
        public String getName() {
            return "Cross";
        }
    }

    private static class Diagonal implements MovementGenerator {
        public Diagonal() {
        }

        @Override
        public List<Position> generate(Board board, Position initial) {
            List<Position> list = new ArrayList<>();

            // up - left
            list.addAll(board.searchByIncrement(initial, -1, -1));
            // up - right
            list.addAll(board.searchByIncrement(initial, -1, 1));
            // down - right
            list.addAll(board.searchByIncrement(initial, 1, 1));
            // down - left
            list.addAll(board.searchByIncrement(initial, 1, -1));

            return list;
        }

        @Override
        public String getName() {
            return "Diagonal";
        }
    }

    private static class LMovement implements MovementGenerator {
        public LMovement() {
        }

        @Override
        public List<Position> generate(Board board, Position initial) {
            List<Position> list = new ArrayList<>();

            for (int i=-2; i<=2; i++) {
                for (int j=-2; j<=2; j++) {
                    Position pos = initial.increment(i, j);
                    if (board.isValid(pos) && (Math.abs(i) != Math.abs(j)) && (i != 0 && j != 0)) {
                        list.add(pos);
                    }
                }
            }

            return list;
        }

        @Override
        public String getName() {
            return "LMovement";
        }
    }

    private static class KingMove implements MovementGenerator {
        public KingMove() {
        }

        @Override
        public List<Position> generate(Board board, Position initial) {
            List<Position> list = new ArrayList<>();

            for (int i =- 1; i <= 1; i++) {
                for (int j =- 1; j <= 1; j++) {
                    Position pos = initial.increment(i, j);
                    if (board.isValid(pos) && !(i == 0 && j == 0)) {
                        list.add(pos);
                    }
                }
            }

            return list;
        }

        @Override
        public String getName() {
            return "KingMove";
        }
    }
}
