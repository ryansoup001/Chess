package chesspackage;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import chessRules.Checks;
import Piecespackage.Piece;
import chesspackage.PieceManager;
/* Modify this program so that it Doesnt include moves that are checks or illegal moves(pins, skewers, etc)
 * 
 */
public class MoveManager {
	Checks cHandler = new Checks();
	ArrayList<Point> posMoves = new ArrayList<Point>();
	public ArrayList<Point> moveset(String type,Piece piece,Piece[][] board) {
		posMoves.clear();
		for (int i = 0; i < type.length();i++) {
			switch(type.charAt(i)) {
				case 's':
					this.straight(piece,board);
					break;
				case 'd':
					this.diagonal(piece, board);
					break;
				case 'k':		//Knight Movement
					this.knight(piece,board);					
			}
		}
		return posMoves;
	}
	public void knight(Piece piece, Piece[][] board) {
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
			if (posMoves.get(i).x < 0 || posMoves.get(i).x > 7 ) {
				posMoves.remove(i);
				i--;
			}
			if (posMoves.get(i).y < 0 || posMoves.get(i).y > 7 ) {
				posMoves.remove(i);
				i--;
			}
		}
	}
	
	
	public void straight(Piece piece,Piece[][] board) {
		Point pos = piece.pos;
		// Finding Forward(White) or Backward(Black) Moves 
		for (int i = 1;  i < (board.length) - pos.y; i ++) {
			if (!cHandler.checkForChecks(cHandler.makeTheoryBoard(board, piece, new Point(pos.x,pos.y + i),piece.boardObj.pieceManager), piece.color)) {
				if (board[pos.x][pos.y + i] != null ) {
				//Checks if a piece is blocking
					if (board[pos.x][pos.y + i].color != piece.color) {
						posMoves.add(new Point(pos.x,pos.y + i));
						break;
					} else {
						break;
					}
				}
				posMoves.add(new Point(pos.x,pos.y + i));
			}
		}
			// Finding Left(White) or right(Black) Moves 
		for (int i = 1;  i < ((board.length) - pos.x); i ++) { 
			if (!cHandler.checkForChecks(cHandler.makeTheoryBoard(board, piece, new Point(pos.x + i,pos.y),piece.boardObj.pieceManager), piece.color)) {
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
		}
		// Finding Backward(White) or Forward(Black) Moves 
		for (int i = 1;  i <= (pos.y) ; i ++) { 
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
		for (int i = 1;  i <= pos.x  ; i ++) { 
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
	
	
	public void diagonal(Piece piece,Piece[][] board) {
		Point pos = piece.pos;  
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
		for (int i = 1;  i <= Math.min(pos.x,board[i].length -1 - pos.y); i ++) {
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
		for (int i = 1;  i <= Math.min(board[pos.x].length - 1 - pos.y,board.length - 1 - pos.x); i ++) { 
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
		for (int i = 1;  i <= Math.min(pos.y, board.length - 1 - pos.x) ; i ++) { 
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
		for (int i = 1;  i <= Math.min(pos.x,pos.y) ; i ++) { 
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
