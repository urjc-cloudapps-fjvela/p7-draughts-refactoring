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
    boolean isAdvanced(Coordinate origin, Coordinate target) {
        return false;
    }
}