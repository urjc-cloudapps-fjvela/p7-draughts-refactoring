package es.urjccode.mastercloudapps.adcs.draughts.models;

import java.util.ArrayList;
import java.util.List;

class Board {

    static final int DIMENSION = 8;

    private Square[][] squares;

    Board() {
        this.squares = new Square[Board.DIMENSION][Board.DIMENSION];
        for (int i = 0; i < Board.DIMENSION; i++) {
            for (int j = 0; j < Board.DIMENSION; j++) {
                this.squares[i][j] = new Square();
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < Board.DIMENSION; j++) {
                if (new Coordinate(i, j).isBlack()) {
                    put(new Coordinate(i, j), new Piece(Color.BLACK));
                }
            }
        }
        for (int i = 5; i < Board.DIMENSION; i++) {
            for (int j = 0; j < Board.DIMENSION; j++) {
                if (new Coordinate(i, j).isBlack()) {
                    put(new Coordinate(i, j), new Piece(Color.WHITE));

                }
            }
        }
    }

    public void move(Coordinate origin, Coordinate target) {
        put(target, remove(origin));
    }

    public Piece remove(Coordinate coordinate) {
        assert this.getPiece(coordinate) != null;
        return getSquare(coordinate).remove();
    }

    public Color getColor(Coordinate coordinate) {
        return getSquare(coordinate).getColor();
    }

    public Piece getPiece(Coordinate coordinate) {
        return getSquare(coordinate).getPiece();
    }

    public List<Piece> getPieces(Color color) {
        List<Piece> pieces = new ArrayList<Piece>();
        for (int i = 0; i < Board.DIMENSION; i++) {
            for (int j = 0; j < Board.DIMENSION; j++) {
                pieces.add(this.squares[i][j].getPiece());
            }
        }
        return pieces;
    }

    boolean isEmpty(Coordinate coordinate) {
        return getSquare(coordinate).isEmpty();
    }

    public int getDimension() {
        return Board.DIMENSION;
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
                result.append(piece == null ? " " : piece.toString());
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

    private void put(Coordinate target, Piece piece) {
        assert checkCoordinate(target);
        this.squares[target.getRow()][target.getColumn()].put(piece);
    }

    private Square getSquare(Coordinate coordinate) {
        assert checkCoordinate(coordinate);
        return squares[coordinate.getRow()][coordinate.getColumn()];
    }

    private boolean checkCoordinate(Coordinate coordinate) {
        return coordinate != null && coordinate.isValid();
    }

}