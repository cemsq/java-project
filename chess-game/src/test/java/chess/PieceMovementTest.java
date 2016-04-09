package chess;

import chess.piece.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 */
public class PieceMovementTest extends IMovementTest{
    private static final Logger log = LoggerFactory.getLogger(IMovementTest.class);

    private Position position_0_0 = new Position(0, 0);
    private Position position_3_3 = new Position(3, 3);
    private Position position_5_6 = new Position(5, 6);

    private Board board = Board.emptyChessBoard();

    @Test(dataProvider = "pieceProvider", dependsOnMethods = "testingMovements")
    public void movePiece(Board board, Piece piece, Position initial, Position destiny, boolean expected) {
        boolean moved = piece.move(board, initial, destiny);
        Assert.assertEquals(moved, expected, "invalid movement from: " + initial + " to: " + destiny + ". Piece: " + piece.getName());
    }

    @DataProvider
    public Object[][] movementProvider() {

        return new Object[][]{
                {Piece.tower(), position_0_0, 14},
                {Piece.bishop(), position_0_0, 7},
                {Piece.horse(), position_0_0, 2},
                {Piece.queen(), position_0_0, 21}
        };
    }

    @DataProvider
    public Object[][] pieceProvider() {

        return new Object[][] {
                {board, Piece.tower(), position_0_0, new Position(0, 7), true},
                {board, Piece.tower(), position_0_0, new Position(7, 0), true},
                {board, Piece.tower(), position_0_0, new Position(1, 1), false},

                {board, Piece.bishop(), position_0_0, new Position(1, 1), true},
                {board, Piece.bishop(), position_0_0, new Position(7, 7), true},
                {board, Piece.bishop(), position_0_0, new Position(0, 0), false},

                {board, Piece.horse(), position_0_0, new Position(2, 1), true},
                {board, Piece.horse(), position_0_0, new Position(1, 2), true},
                {board, Piece.horse(), position_0_0, new Position(1, 1), false},

                {board, Piece.queen(), position_0_0, new Position(0, 7), true},
                {board, Piece.queen(), position_0_0, new Position(7, 0), true},
                {board, Piece.queen(), position_0_0, new Position(7, 7), true},
                {board, Piece.queen(), position_0_0, new Position(2, 1), false}
        };
    }
}
