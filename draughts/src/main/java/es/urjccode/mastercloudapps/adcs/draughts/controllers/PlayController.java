package es.urjccode.mastercloudapps.adcs.draughts.controllers;

import es.urjccode.mastercloudapps.adcs.draughts.models.Color;
import es.urjccode.mastercloudapps.adcs.draughts.models.Coordinate;
import es.urjccode.mastercloudapps.adcs.draughts.models.Piece;
import es.urjccode.mastercloudapps.adcs.draughts.models.State;
import es.urjccode.mastercloudapps.adcs.draughts.models.Error;
import es.urjccode.mastercloudapps.adcs.draughts.models.Game;

public class PlayController extends Controller {

	public PlayController(Game game, State state) {
		super(game, state);
	}

	public Error move(Coordinate origin, Coordinate target) {
		//TODO: I don't like this. check patterns for next refactoring
		Error result = game.isTurnValid(origin);
		if (result == null) {
			result = canMovePiece(origin, target);

			if (result == null) {
				result = canMoveToTarget(origin, target);
				if (result == null) {
					result = game.move(origin, target);
					if (game.isBlocked()) {
						state.next();
					}
				}
			}
		}
		return result;
	}

	private Error canMovePiece(Coordinate origin, Coordinate target) {
		Piece piece = game.getPiece(origin);
		return piece == null ? Error.PIECE_NOT_VALID : piece.canMove(origin, target);
	}

	private Error canMoveToTarget(Coordinate origin, Coordinate target) {
		return origin.canMove(target);
	}

	public Piece getPiece(Coordinate coordinate) {
		return game.getPiece(coordinate);
	}

	public Color getColor() {
		return game.getColor();
	}

	public boolean isBlocked() {
		return game.isBlocked();
	}

	@Override
	public void accept(ControllersVisitor controllersVisitor) {
		controllersVisitor.visit(this);
	}

}