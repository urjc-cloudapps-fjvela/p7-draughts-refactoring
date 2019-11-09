package es.urjccode.mastercloudapps.adcs.draughts.models;


public class PieceNull extends Piece {

    PieceNull(Color color) {
        super(color);
    }

    @Override
    public boolean isNull() {
        return true;
    }

    @Override
    public boolean isAdvanced(Coordinate origin, Coordinate target) {
        return false;
    }
}