package es.urjccode.mastercloudapps.adcs.draughts.models;

class Square {

    private Piece piece;

    Square() {
    }

    void put(Piece piece) {
        this.piece = piece;
    }

    Piece remove() {
        Piece piece = this.piece;
        this.piece = null;
        return piece;
    }

    Piece getPiece() {
        return this.piece == null ? new PieceNull(null) : piece;
    }

    boolean isEmpty() {
        return this.piece == null;
    }

    Color getColor() {
        return piece == null ? null : piece.getColor();
    }

}