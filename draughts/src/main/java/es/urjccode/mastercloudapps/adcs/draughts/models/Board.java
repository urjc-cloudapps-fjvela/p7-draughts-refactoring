package es.urjccode.mastercloudapps.adcs.draughts.models;

import java.util.ArrayList;
import java.util.List;

class Board {

    private static final int DIMENSION = 8;

    private Square[][] squares;

    Board() {
        this.squares = new Square[Board.DIMENSION][Board.DIMENSION];
        for (int i = 0; i < Board.DIMENSION; i++) {
            for (int j = 0; j < Board.DIMENSION; j++) {
                this.squares[i][j] = new Square();
            }
        }

    }

    void move(Coordinate origin, Coordinate target) {
        put(target, remove(origin));
    }

    Piece remove(Coordinate coordinate) {
        assert this.getPiece(coordinate) != null;
        return getSquare(coordinate).remove();
    }

    Color getColor(Coordinate coordinate) {
        return getSquare(coordinate).getColor();
    }

    Piece getPiece(Coordinate coordinate) {
        return getSquare(coordinate).getPiece();
    }

    List<Piece> getPieces(Color color) {
        List<Piece> pieces = new ArrayList<Piece>();
        Piece piece;

        for (int i = 0; i < Board.DIMENSION; i++) {
            for (int j = 0; j < Board.DIMENSION; j++) {
                piece = getPiece(new Coordinate(i, j));
                if (!piece.isNull() && piece.getColor() == color) {
                    pieces.add(piece);
                }
            }
        }

        return pieces;
    }

    void put(Coordinate target, Piece piece) {
        assert checkCoordinate(target);
        this.squares[target.getRow()][target.getColumn()].put(piece);
    }

    boolean isEmpty(Coordinate coordinate) {
        return getSquare(coordinate).isEmpty();
    }

    int getDimension() {
        return Board.DIMENSION;
    }

    Error canMove(Coordinate origin, Coordinate target) {
        if (isEmpty(origin)) {
            return Error.EMPTY_ORIGIN;
        }
        if (!isEmpty(target)) {
            return Error.NOT_EMPTY_TARGET;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        toStringHorizontalDimension(result);
        toStringVerticalDimension(result);
        toStringHorizontalDimension(result);
        return result.toString();
    }

    private void toStringVerticalDimension(StringBuilder result) {
        for (int i = 0; i < Board.DIMENSION; i++) {
            result.append(String.valueOf(i));
            result.append(" ");
            for (int j = 0; j < Board.DIMENSION; j++) {
                Piece piece = getPiece(new Coordinate(i, j));
                result.append(piece.toString());
            }
            result.append(" ");
            result.append(String.valueOf(i));
            result.append("\n");
        }
    }

    private void toStringHorizontalDimension(StringBuilder result) {
        result.append("  ");
        for (int j = 0; j < Board.DIMENSION; j++) {
            result.append(String.valueOf(j));
        }
        result.append("\n");
    }

    private Square getSquare(Coordinate coordinate) {
        assert checkCoordinate(coordinate);
        return squares[coordinate.getRow()][coordinate.getColumn()];
    }

    private boolean checkCoordinate(Coordinate coordinate) {
        return coordinate != null && coordinate.isValid();
    }

}