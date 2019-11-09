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
		Error result = null;

		Piece piece = game.getPiece(origin);
		if (piece != null && (result = piece.canMove(origin, target)) == null) {
			result = game.move(origin, target);
			if (game.isBlocked()) {
				state.next();
			}
		}
		return result;
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