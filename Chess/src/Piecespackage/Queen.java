package Piecespackage;

import java.awt.Point;
import java.util.ArrayList;

import chesspackage.Board;
import chesspackage.MoveManager;

public class Queen extends Piece {
	// moves in both directions of bishops and rooks
	MoveManager moveM = new MoveManager();
	public Queen(String color, String type,int value, Point pos,Board boardObj) {
		super(color, type, value, pos,boardObj);
	}

	@Override
	public void updateMoves() {
		posMoves.clear();
		posMoves.addAll(moveM.moveset("sd", this, boardObj));
	}
}

