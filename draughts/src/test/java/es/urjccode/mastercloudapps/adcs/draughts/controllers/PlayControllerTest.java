package es.urjccode.mastercloudapps.adcs.draughts.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.instanceOf;

import es.urjccode.mastercloudapps.adcs.draughts.models.Coordinate;
import es.urjccode.mastercloudapps.adcs.draughts.models.Error;
import es.urjccode.mastercloudapps.adcs.draughts.models.Game;
import es.urjccode.mastercloudapps.adcs.draughts.models.Piece;
import es.urjccode.mastercloudapps.adcs.draughts.models.PieceNull;
import es.urjccode.mastercloudapps.adcs.draughts.models.State;
import es.urjccode.mastercloudapps.adcs.draughts.models.Color;

public class PlayControllerTest {

    private PlayController playController;
    @Before
    public void beforeTest() {
        playController = new PlayController(new Game(), new State());
    }
    @Test
    public void givenPlayControllerWhenMovementRequiereCorrectThenNotError() {
        Coordinate origin = new Coordinate(5, 0);
        Coordinate target = new Coordinate(4, 1);
        assertNull(playController.move(origin, target));
        assertThat(playController.getPiece(origin), instanceOf(PieceNull.class));
        Piece pieceTarget = playController.getPiece(target);
        assertNotNull(pieceTarget);
        assertEquals(pieceTarget.getColor(), Color.WHITE);
    }

    @Test
    public void givenPlayControlllerWhenNewGameThenBoardIsNotBlocked() {
        //TODO: may be play a complete game?
        assertFalse(playController.isBlocked());
    }


    @Test
    public void testGivenPlayControllerWhenMoveWithNotAdvancedThenError() {
        assertEquals(Error.NOT_ADVANCED,
                this.advance(new Coordinate[][] { { new Coordinate(5, 6), new Coordinate(4, 7) },
                        { new Coordinate(2, 7), new Coordinate(3, 6) }, { new Coordinate(5, 4), new Coordinate(4, 3) },
                        { new Coordinate(1, 6), new Coordinate(2, 7) }, { new Coordinate(4, 3), new Coordinate(3, 4) },
                        { new Coordinate(0, 7), new Coordinate(1, 6) },
                        { new Coordinate(3, 4), new Coordinate(4, 5) }, }));
    }


    private Error advance(Coordinate[][] coordinates) {
        Error error = null;
        for (int i = 0; i < coordinates.length; i++) {
            assertNull(error);

            error = playController.move(coordinates[i][0], coordinates[i][1]);
        }
        return error;
    }


    // public void data(){
    // Coordinate[][] coordinates = new Coordinate[][] {
    // { new Coordinate(0,0), new Coordinate(0,0) },
    // { new Coordinate(0,0), new Coordinate(0,0), new Coordinate(0,0), new
    // Coordinate(0,0) },
    // { new Coordinate(0,0), new Coordinate(0,0) },
    // };

    // peon mueve una
    // peon come una
    // peon come varias
    // peon se convierte en dama
    // dama mueve una
    // dama mueve varias
    // dama come una
    // dama come varias, atras

    // }

}