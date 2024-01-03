package chesspackage;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

import Piecespackage.Piece;

public class MoveManager {
	ArrayList<Point> posMoves = new ArrayList<Point>();
	public ArrayList<Point> moveset(String type,Piece piece,Board boardObj) {
		posMoves.clear();
		for (int i = 0; i < type.length();i++) {
			switch(type.charAt(i)) {
				case 's':
					this.straight(piece,boardObj);
					break;
				case 'd':
					this.diagonal(piece, boardObj);
					break;
				case 'k':		//Knight Movement
					this.knight(piece,boardObj);					
			}
		}
		return posMoves;
	}
	public void knight(Piece piece, Board boardObj) {
		Point posCopy = piece.pos.getLocation();
		final int up = 2;
		final int over = 1;
		final int flip = -1;
		final Point[] allMoves = {new Point(over,up),new Point(over,flip*up),new Point(flip*over,up),new Point(flip*over,flip*up),new Point(up,over), new Point(up*flip,over),new Point(up,over*flip),new Point(up*flip,over*flip)}; 
		for(Point translation: allMoves) {
			posCopy = piece.pos.getLocation();
			posCopy.translate(translation.x,translation.y);
			posMoves.add(posCopy);
		}
		for (int i = 0; i < posMoves.size(); i++) {
			if (posMoves.get(i).x < 0 || posMoves.get(i).x > 8 ) {
				posMoves.remove(i);
				i--;
			}
			if (posMoves.get(i).y < 0 || posMoves.get(i).y > 8 ) {
				posMoves.remove(i);
				i--;
			}
		}
	}
	public void straight(Piece piece,Board boardObj) {
		Point pos = piece.pos;
		Piece[][] board = boardObj.getBoard(); 
		// Finding Forward(White) or Backward(Black) Moves 
		for (int i = 1;  i < (board[i].length-pos.y); i ++) {
			
			if (board[pos.x][pos.y + i] != null ) { //Checks if a piece is blocking
				if (board[pos.x][pos.y + i].color != piece.color) {
					posMoves.add(new Point(pos.x,pos.y + i));
					break;
				} else {
					break;
				}
			}
			posMoves.add(new Point(pos.x,pos.y + i));
		}
			// Finding Left(White) or right(Black) Moves 
		for (int i = 1;  i < (board.length - pos.x); i ++) { 
			if (board[pos.x + i][pos.y] != null) { //Checks if a piece is blocking
				if (board[pos.x + i][pos.y].color != piece.color) {
					posMoves.add(new Point(pos.x + i,pos.y));
					break;
				} else {
					break;
				}
			}
			posMoves.add(new Point(pos.x + i,pos.y));
		}
		// Finding Backward(White) or Forward(Black) Moves 
		for (int i = 1;  i < pos.y ; i ++) { 
			if (board[pos.x][pos.y - i] != null ) { //Checks if a piece is blocking
				if (board[pos.x][pos.y - i].color != piece.color) {
					posMoves.add(new Point(pos.x,pos.y - i));
					break;
				} else {
					break;
				}
		}
			posMoves.add(new Point(pos.x,pos.y - i));
		}
		// Finding Left(White) or Right(Black) Moves 
		for (int i = 1;  i < pos.x ; i ++) { 
			if (board[pos.x - i][pos.y] != null ) { //Checks if a piece is blocking
				if (board[pos.x - i][pos.y].color != piece.color) {
					posMoves.add(new Point(pos.x - i,pos.y));
					break;
				} else {
					break;
				}
			}
			posMoves.add(new Point(pos.x - i,pos.y));
		}
	}
	
	
	public void diagonal(Piece piece,Board boardObj) {
		Point pos = piece.pos; 
		Piece[][] board = boardObj.getBoard(); 
//		for (int i = 0; i < board.length; i ++) {
//			// Finding Forward Right(White) or Backward Left(Black) Moves 
//			posMoves.add(new Point(pos.x + i,pos.y + i));
//			// Finding Backward Left(White) or Forward Right(Black) Moves 
//			posMoves.add(new Point(pos.x - i, pos.y - i));
//			// Finding Forward Left(White) or Backwards Right(Black) Moves 
//			posMoves.add(new Point(pos.x + i,pos.y - i));
//			// Finding Backwards Right(White) or Forward Left(Black) Moves 
//			posMoves.add(new Point(pos.x - i, pos.y + i));
//		}
		//Forwards Left (White)
for (int i = 1;  i < Math.min(pos.x + 1,board[i].length-pos.y); i ++) {
	if (board[pos.x - i][pos.y + i] != null ) { //Checks if a piece is blocking
		if (board[pos.x - i][pos.y + i].color != piece.color) {
			posMoves.add(new Point(pos.x - i,pos.y + i));
			break;
		} else {
			break;
		}
	}
			posMoves.add(new Point(pos.x - i,pos.y + i));
		}
			// Finding Left(White) or right(Black) Moves 
		for (int i = 1;  i < Math.min(board[pos.x].length - pos.y,board.length - pos.x); i ++) { 
			if (board[pos.x + i][pos.y + i] != null ) { //Checks if a piece is blocking
				if (board[pos.x + i][pos.y + i].color != piece.color) {
					posMoves.add(new Point(pos.x + i,pos.y + i));
					break;
				} else {
					break;
				}
			}
			posMoves.add(new Point(pos.x + i,pos.y + i));
		}
		// Finding Backward(White) or Forward(Black) Moves 
		for (int i = 1;  i < Math.min(pos.y + 1, board.length - pos.x) ; i ++) { 
			if (board[pos.x + i][pos.y - i] != null ) { //Checks if a piece is blocking
				if (board[pos.x + i][pos.y - i].color != piece.color) {
					posMoves.add(new Point(pos.x + i,pos.y - i));
					break;
				} else {
					break;
				}
			}
			posMoves.add(new Point(pos.x + i,pos.y - i));
		}
		// Finding Left(White) or Right(Black) Moves 
		for (int i = 1;  i < Math.min(pos.x + 1,pos.y + 1) ; i ++) { 
			if (board[pos.x - i][pos.y - i] != null ) { //Checks if a piece is blocking
				if (board[pos.x - i][pos.y - i].color != piece.color) {
					posMoves.add(new Point(pos.x - i,pos.y - i));
					break;
				} else {
					break;
				}
			}
			posMoves.add(new Point(pos.x - i,pos.y - i));
		}
		
	}

}
