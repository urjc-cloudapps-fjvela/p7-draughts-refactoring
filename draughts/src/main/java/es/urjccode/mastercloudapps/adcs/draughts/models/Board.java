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
        assert coordinate != null;
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
        String string = " ";
        for (int j = 0; j < Board.DIMENSION; j++) {
            string += j;
        }
        string += "\n";
        for (int i = 0; i < Board.DIMENSION; i++) {
            string += i;
            for (int j = 0; j < Board.DIMENSION; j++) {
                Piece piece = this.squares[i][j].getPiece();
                if (piece == null) {
                    string += " ";
                } else if (piece.isBlack()) {
                    string += "n";
                } else {
                    string += "b";
                }
            }
            string += i + "\n";
        }
        string += " ";
        for (int j = 0; j < Board.DIMENSION; j++) {
            string += j;
        }
        return string;
    }

    private void put(Coordinate target, Piece piece) {
        this.squares[target.getRow()][target.getColumn()].put(piece);
    }

    private Square getSquare(Coordinate coordinate) {
        return squares[coordinate.getRow()][coordinate.getColumn()];
    }

}