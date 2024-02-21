package Piecespackage;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import chesspackage.Board;
import chesspackage.MoveManager;

public class Knight extends Piece {
	// moves in a L shape
	MoveManager moveM = new MoveManager();
	public Knight(String color, String type, int value, Point pos, Board boardObj) {
		super(color, type, value, pos, boardObj);
	}
	public BufferedImage getImage() {
		BufferedImage image = imgHndle.setup("/chesspackage/assets/" + this.color.substring(0, 1)  + "N.png");
	   	return image;
	}
	@Override
	public void updateMoves(Piece[][] board) {
		posMoves.clear();
		posMoves.addAll(moveM.moveset("k", this, board));
		
	}
	  }