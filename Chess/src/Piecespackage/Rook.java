package Piecespackage;

import java.awt.Point;
import java.util.ArrayList;

import chesspackage.Board;
import chesspackage.MoveManager;

public class Rook extends Piece {
	MoveManager moveM = new MoveManager();
	public Rook(String color, String type,int value, Point pos,Board boardObj) {
		   super(color, type, value, pos, boardObj);
	}
	
	@Override
	public void updateMoves(Piece[][] board) {
		posMoves.clear();
		posMoves.addAll(moveM.moveset("s", this, board));
	}
}
