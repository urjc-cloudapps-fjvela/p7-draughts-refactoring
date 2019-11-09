package es.urjccode.mastercloudapps.adcs.draughts.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PieceTest {

    @Test
    public void testGivenPieceWhenIsAdvancedThenTrue() {
        assertTrue(new Piece(Color.WHITE).canMove(new Coordinate(5, 0), new Coordinate(4, 1)) == null);
        assertTrue(new Piece(Color.BLACK).canMove(new Coordinate(2, 1), new Coordinate(3, 2)) == null);
    }

    @Test
    public void testGivenPieceWhenNotIsAdvancedThenFalse() {
        assertTrue(new Piece(Color.WHITE).canMove(new Coordinate(5, 0), new Coordinate(6, 1)) != null);
        assertTrue(new Piece(Color.WHITE).canMove(new Coordinate(5, 0), new Coordinate(5, 2)) != null);
        assertTrue(new Piece(Color.BLACK).canMove(new Coordinate(2, 1), new Coordinate(2, 3)) != null);
        assertTrue(new Piece(Color.BLACK).canMove(new Coordinate(2, 1), new Coordinate(1, 2)) != null);
    }
}