package Piecespackage;

import java.awt.Point;
import java.awt.image.BufferedImage;

import chesspackage.Board;

public class Pawn extends Piece {
	//moves forward 1 square
	public int orientation = 1;
	public boolean firstMove = true;
	public int startingRank = 2;
	public Pawn(String color, String type, int value, Point pos, Board boardObj) {
		super(color, type, value, pos, boardObj);
		if (this.color == "black") {
			orientation  = -1;
			startingRank = 7;
		}
	}
	@Override
	public boolean capturePiece(Piece capturedPiece) {
		if (capturedPiece.color == this.color) {
			return false;
		}
		if (Math.abs(capturedPiece.pos.x - this.pos.x) == 1 && (this.pos.y + orientation) == capturedPiece.pos.y) {
			return true;
		}
		System.out.print("Not Capturing");
		return false;
	}
	@Override
	public void updateMoves(Piece[][] board) {
		int rank = pos.y;
		posMoves.clear();
		if(color == "white") {
			if (rank == (startingRank-orientation)) {
				posMoves.add(new Point(pos.x,pos.y + (orientation * 2)));
				posMoves.add(new Point(pos.x,pos.y + orientation));
			} else {
				posMoves.add(new Point(pos.x,pos.y + orientation));
			}
		}
		if(color == "black") {
			if (rank == (startingRank-1)) {
				posMoves.add(new Point(pos.x,pos.y + (orientation * 2)));
				posMoves.add(new Point(pos.x,pos.y + orientation));
			} else {
				posMoves.add(new Point(pos.x,pos.y + orientation));
			}
		}
	}
}
