package es.urjccode.mastercloudapps.adcs.draughts.models;

abstract class PieceBase {
    protected Color color;

    PieceBase(Color color) {
        this.color = color;
    }

    public abstract Error canMove(Coordinate origin, Coordinate target);

}