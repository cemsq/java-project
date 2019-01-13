package cem.ttt;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class TicTacToeBoardTest {

    @Test
    public void shouldMakeMoveAndSwitchPlayer() {
        Player pX = Player.pX();
        Player pO = Player.pO();

        TicTacToeBoard board = new TicTacToeBoard(pX, pO);
        board.applyMove(new Move(0, 0));

        String expected = new StringJoiner("\n")
                .add("[X][ ][ ]")
                .add("[ ][ ][ ]")
                .add("[ ][ ][ ]")
                .toString();

        String value = board.toString();
        Assert.assertEquals(value, expected, "board");
        Assert.assertEquals(board.getCurrentPlayer().getPlayerValue(), pO.getPlayerValue(), "player value");
    }

    @Test
    public void shouldNotMakeMoveAndDoNotSwitchPlayerBecauseItIsIllegal() {
        Player pX = Player.pX();
        Player pO = Player.pO();

        TicTacToeBoard board = new TicTacToeBoard(pX, pO);
        board.applyMove(new Move(-1, 0));

        String expected = new StringJoiner("\n")
                .add("[ ][ ][ ]")
                .add("[ ][ ][ ]")
                .add("[ ][ ][ ]")
                .toString();

        String value = board.toString();
        Assert.assertEquals(value, expected, "board");
        Assert.assertEquals(board.getCurrentPlayer().getPlayerValue(), pX.getPlayerValue(), "player value");
    }

    @Test
    public void shouldUndoMoveAndSwitchPlayer() {
        Player pX = Player.pX();
        Player pO = Player.pO();

        TicTacToeBoard board = new TicTacToeBoard(pX, pO);
        board.applyMove(new Move(0, 0));

        Move oMove = new Move(1, 1);
        board.applyMove(oMove);

        board.undoMove(oMove);
        String expected = new StringJoiner("\n")
                .add("[X][ ][ ]")
                .add("[ ][ ][ ]")
                .add("[ ][ ][ ]")
                .toString();

        String value = board.toString();
        Assert.assertEquals(value, expected, "board");
        Assert.assertEquals(board.getCurrentPlayer().getPlayerValue(), pO.getPlayerValue(), "player value");
    }

    @Test
    public void shouldGetPossiblePositions() {
        Player pX = Player.pX();
        Player pO = Player.pO();

        TicTacToeBoard board = new TicTacToeBoard(pX, pO);
        board.applyMove(new Move(0, 0));
        board.applyMove(new Move(1, 1));
        board.applyMove(new Move(2, 2));

        String expected = "1, 2, 3, 5, 6, 7";
        List<Move> possibleMoves = board.getPossibleMoves();
        String value = possibleMoves.stream()
                .map(Move::getId)
                .map(Object::toString)
                .collect(Collectors.joining(", "));

        Assert.assertEquals(value, expected, "moves");
    }
}
