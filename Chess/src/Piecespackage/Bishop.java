package Piecespackage;

import java.awt.Point;
import java.util.ArrayList;

import chesspackage.Board;
import chesspackage.MoveManager;

public class Bishop extends Piece {
	  // moves down diagonal lines
	MoveManager moveM = new MoveManager();
	public Bishop(String color, String type, int value, Point pos, Board boardObj) {
		super(color, type, value, pos, boardObj);
	}
	  
	  


	@Override
	public void updateMoves(Piece[][] board) {
		posMoves.clear();
		posMoves.addAll(moveM.moveset("d", this, board));
	}
}
