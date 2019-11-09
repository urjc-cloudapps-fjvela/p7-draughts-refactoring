package es.urjccode.mastercloudapps.adcs.draughts.models;

public class Piece extends PieceBase {

	Piece(Color color) {
		super(color);
	}

	public Color getColor() {
		return this.color;
	}

	boolean isBlack() {
		return this.color == Color.BLACK;
	}

	boolean isNull() {
		return false;
	}

	@Override
	public String toString() {
		return color.name().substring(0, 1);
	}

	@Override
	public Error canMove(Coordinate origin, Coordinate target) {
		if (!isAdvanced(origin, target)) {
			return Error.NOT_ADVANCED;
		}

		return null;
	}

	
	private boolean isAdvanced(Coordinate origin, Coordinate target) {
		int difference = origin.getRow() - target.getRow();
		if (color == Color.WHITE) {
			return difference > 0;
		}
		return difference < 0;
	}


}