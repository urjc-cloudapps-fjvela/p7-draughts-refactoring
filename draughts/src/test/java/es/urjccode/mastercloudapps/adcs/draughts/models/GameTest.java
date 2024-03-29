package es.urjccode.mastercloudapps.adcs.draughts.models;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class GameTest {

    private Game game;

    public GameTest() {
        game = new Game();
    }

    private Error advance(Coordinate[][] coordinates) {
        Error error = null;
        for (int i = 0; i < coordinates.length; i++) {
            assertNull(error);
            error = game.move(coordinates[i][0], coordinates[i][1]);
        }
        return error;
    }

    @Test
    public void testGivenNewGameThenInitialPositionsAreOk() {

        checkInitialPositions(startRowForCheckInititalPosition(0), endRowForCheckInititalPosition(3),
                colorForCheckInitialPosition(Color.BLACK));
        checkInitialPositions(startRowForCheckInititalPosition(5), endRowForCheckInititalPosition(game.getDimension()),
                colorForCheckInitialPosition(Color.WHITE));
    }

    private int startRowForCheckInititalPosition(int value) {
        return value;
    }

    private int endRowForCheckInititalPosition(int value) {
        return value;
    }

    private Color colorForCheckInitialPosition(Color color) {
        return color;
    }

    private void checkInitialPositions(int startRow, int endRow, Color colorToCheck) {
        Coordinate coordinate;
        Color color;
        for (int i = startRow; i < endRow; i++) {
            for (int j = 0; j < game.getDimension(); j++) {
                coordinate = new Coordinate(i, j);
                color = game.getColor(coordinate);
                if (coordinate.isBackgroundColorBlack()) {
                    assertEquals(colorToCheck, color);
                } else {
                    assertNull(color);
                }
            }
        }
    }

    @Test
    public void testGivenGameWhenMoveEmptySquaerThenEmptySquareError() {
        assertEquals(Error.EMPTY_ORIGIN,
                this.advance(new Coordinate[][] { { new Coordinate(4, 3), new Coordinate(3, 4), }, }));
    }

    @Test
    public void testGivenGameWhenNotEmptyTargeThenError() {
        assertEquals(Error.NOT_EMPTY_TARGET,
                this.advance(new Coordinate[][] { { new Coordinate(5, 6), new Coordinate(4, 7) },
                        { new Coordinate(2, 7), new Coordinate(3, 6) },
                        { new Coordinate(4, 7), new Coordinate(3, 6) }, }));
    }

    @Test
    public void testGivenGameWhenCorrectMovementThenOk() {
        Coordinate origin = new Coordinate(5, 0);
        Coordinate target = new Coordinate(4, 1);
        this.game.move(origin, target);
        assertNull(this.game.getColor(origin));
        assertEquals(Color.WHITE, this.game.getColor(target));
        origin = new Coordinate(2, 3);
        target = new Coordinate(3, 4);
        this.game.move(origin, target);
        assertNull(this.game.getColor(origin));
        assertEquals(Color.BLACK, this.game.getColor(target));
    }

    @Test
    public void testGivenGameWhenMovementThenEatPiece() {
        assertNull(this.advance(new Coordinate[][] { { new Coordinate(5, 0), new Coordinate(4, 1) },
                { new Coordinate(2, 1), new Coordinate(3, 0) }, { new Coordinate(5, 2), new Coordinate(4, 3) },
                { new Coordinate(3, 0), new Coordinate(5, 2) }, }));
        assertNull(game.getColor(new Coordinate(3, 0)));
        assertNull(game.getColor(new Coordinate(4, 1)));
        assertEquals(Color.BLACK, game.getColor(new Coordinate(5, 2)));
    }

    @Test
    public void testGivenGameWhenEatEmptyPieceThenError() {
        assertEquals(Error.EATING_EMPTY,
                this.advance(new Coordinate[][] { { new Coordinate(5, 4), new Coordinate(3, 2) }, }));
    }

}