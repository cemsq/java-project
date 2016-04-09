package chess;

import chess.moves.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

/**
 *
 */
@Slf4j
public class IMovementTest {
    private static final String CROSS = "Cross";
    private static final String DIAGONAL = "Diagonal";
    private static final String HORSE = "Horse";
    private static final String KING = "King";

    @Test(dataProvider = "movement-provider")
    public void testingMovements(MovementGenerator movement, String name, Position position, int size) {
        log.info(" Position: {}, size: {}, Testing: {}", position, size, name);
        List<Position> list = movement.generate(Board.emptyChessBoard(), position);

        Assert.assertEquals(list.size(), size, name + " list.size not match");
    }

    @DataProvider(name = "movement-provider")
    public Object[][] movementProvider() {
        Position position_0_0 = new Position(0, 0);
        Position position_3_3 = new Position(3, 3);
        Position position_5_7 = new Position(5, 7);

        return new Object[][]{
                {Movements.cross(), CROSS, position_0_0, 14},
                {Movements.diagonal(), DIAGONAL, position_0_0, 7},
                {Movements.horse(), HORSE, position_0_0, 2},
                {Movements.king(), KING, position_0_0, 3},

                {Movements.cross(), CROSS, position_3_3, 14},
                {Movements.diagonal(), DIAGONAL, position_3_3, 13},
                {Movements.horse(), HORSE, position_3_3, 8},
                {Movements.king(), KING, position_3_3, 8},

                {Movements.cross(), CROSS, position_5_7, 14},
                {Movements.diagonal(), DIAGONAL, position_5_7, 7},
                {Movements.horse(), HORSE, position_5_7, 4},
                {Movements.king(), KING, position_5_7, 5}
        };
    }
}
