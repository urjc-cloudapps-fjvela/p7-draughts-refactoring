package es.urjccode.mastercloudapps.adcs.draughts.models;

public class PieceNull extends Piece {

    PieceNull(Color color) {
        super(color);
    }

    @Override
    boolean isNull() {
        return true;
    }

    @Override
    public Error canMove(Coordinate origin, Coordinate target) {
        return Error.PIECE_NOT_VALID;
    }
}