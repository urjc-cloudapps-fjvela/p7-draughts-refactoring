package es.urjccode.mastercloudapps.adcs.draughts.models;


public class Game {

	private final static int START_ROW_INITIAL_BLACKS = 0;
	private final static int END_ROW_INITIAL_BLACKS = 3;
	private final static int START_ROW_INITIAL_WHITES = 5;
	

	private Board board;

	private Turn turn;

	public Game() {
		this.board = new Board();
		this.turn = new Turn();
		initPieces();
	}


	public Error move(Coordinate origin, Coordinate target) {
		assert origin != null && target != null;
		if (!origin.isValid() || !target.isValid()) {
			return Error.OUT_COORDINATE;
		}
		if (!origin.isDiagonal(target)) {
			return Error.NOT_DIAGONAL;
		}
		
		if (origin.diagonalDistance(target) >= 3) {
			return Error.BAD_DISTANCE;
		}
		if (origin.diagonalDistance(target) == 2) {
			Coordinate between = origin.betweenDiagonal(target);
			if (this.board.getPiece(between).isNull()) {
				return Error.EATING_EMPTY;
			}
			this.board.remove(between);
		}
	
		if (board.isEmpty(origin)) {
			return Error.EMPTY_ORIGIN;
		}
		Color color = this.board.getColor(origin);
		if (this.turn.getColor() != color) {
			return Error.OPPOSITE_PIECE;
		}

		if (!this.board.isEmpty(target)) {
			return Error.NOT_EMPTY_TARGET;
		}

		this.board.move(origin, target);
		this.turn.change();
		return null;
	}

	public Color getColor(Coordinate coordinate) {
		return this.board.getColor(coordinate);
	}

	@Override
	public String toString() {
		return this.board + "\n" + this.turn;
	}

	public Color getColor() {
		return this.turn.getColor();
	}

	public Piece getPiece(Coordinate coordinate) {
		return this.board.getPiece(coordinate);
	}

	public boolean isBlocked() {
		return this.board.getPieces(this.turn.getColor()).isEmpty();
	}

	public int getDimension() {
		return this.board.getDimension();
	}

	private void initPieces() {
		initPieces(START_ROW_INITIAL_BLACKS, END_ROW_INITIAL_BLACKS, Color.BLACK);
		initPieces(START_ROW_INITIAL_WHITES, board.getDimension(), Color.WHITE);
	}

	private void initPieces(int startRowInitial, int endRowInitial, Color colorToInit) {
		for (int i = startRowInitial; i < endRowInitial; i++) {
			for (int j = 0; j < board.getDimension(); j++) {
				if (new Coordinate(i, j).isBackgroundColorBlack()) {
					board.put(new Coordinate(i, j), new Piece(colorToInit));
				}
			}
		}
	}
}