package chessRules;
import chesspackage.Board;
import chesspackage.PieceManager;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

import Piecespackage.*;
public class Checks {
	/* make a function called gamestate that checks
	 *  state of game( BUILD OFF CHECKFORMATE... ) 
	 * checks for draws stale mate and checkmate.
	 */
	
	public boolean checkForChecks(Piece[][] makeTheoryBoard, String turn) {
		ArrayList<Piece> black = new ArrayList<Piece>();
		ArrayList<Piece> white = new ArrayList<Piece>();
		Piece[] kings = new Piece[2];
		System.out.println("Ran");
		for (Piece[] row: makeTheoryBoard) {
			for (Piece piece: row) {
				if (piece != null) {
					if (piece.type == "King") {
						if (piece.color == "white") {
							kings[0] = piece;
							//System.out.println("Found the white king");
						} else {
							kings[1] = piece;
							//System.out.println("Found the black king");
						}
					}
					if (piece.color == "black") {
						black.add(piece);
					} else {
						white.add(piece);
					}
				}
			}
		}
		if (turn == "white") {
			for (Piece attacker: black) {
					if (attacker.capturePiece(kings[0])) {
						System.out.print( attacker + " on " + attacker.pos + " attacking the white king.");
						return true;
				}
			}
			} else {
				for (Piece attacker: white) {
					if (attacker.capturePiece(kings[1])) {
						System.out.print( attacker + " on " + attacker.pos + " attacking the black king.");
						return true;
					}
				}
			}
		return false;
}

	public boolean checkForMate(Piece[][] board, String turn, PieceManager pHandler) {
		ArrayList<Piece> black = new ArrayList<Piece>();
		ArrayList<Piece> white = new ArrayList<Piece>();
		ArrayList<Point> escapeSqrs = new ArrayList<Point>();
		Piece[] kings = new Piece[2];
		//System.out.println("Ran");
		for (Piece[] row: board) {
			for (Piece piece: row) {
				if (piece != null) {
					if (piece.type == "King") {
						if (piece.color == "white") {
							kings[0] = piece;
							//System.out.println("Found the white king");
						} else {
							kings[1] = piece;
							//System.out.println("Found the black king");
						}
					}
					if (piece.color == "black") {
						black.add(piece);
					} else {
						white.add(piece);
					}
				}
			}
		}
		if (turn == "white") {
			for (int i = 0; i  < kings[0].posMoves.size(); i++) {
				escapeSqrs.add(kings[0].posMoves.get(i));
			}
			for (int i = 0; i < escapeSqrs.size(); i++) { 
				if (checkForChecks(makeTheoryBoard(board, kings[0], escapeSqrs.get(i), pHandler),turn)) {
					escapeSqrs.remove(i);
					i--;
				}
			}
			} else {
				for (int i = 0; i  < kings[1].posMoves.size(); i++) {
					escapeSqrs.add(kings[1].posMoves.get(i));
				}
				for (int i = 0; i < escapeSqrs.size(); i++) { 
					if (checkForChecks(makeTheoryBoard(board, kings[1], escapeSqrs.get(i), pHandler),turn)) {
						escapeSqrs.remove(i);
						i--;
					}
				}
		}
		for (Point moves: escapeSqrs) {
			System.out.println(moves);
		}
		if (escapeSqrs.size() == 0) {
			return true;
		}
		return false;
	}


	public Piece[][] makeTheoryBoard( Piece[][] currentBoard, Piece pieceMoved, Point newPos, PieceManager pHandler){
		Piece[][] board = new Piece[8][8];
		for (int r = 0; r < currentBoard.length; r++) {
			for (int c = 0; c < currentBoard.length; c++) {
				
				if (currentBoard[r][c] != null) {
					board[r][c] = (Piece) currentBoard[r][c].clone();
					board[r][c].pos.x = currentBoard[r][c].pos.x;
					board[r][c].pos.y = currentBoard[r][c].pos.y;
					//System.out.println("Clone address: " + board[r][c] + " Original address: " + currentBoard[r][c] );
					//System.out.println("Clone position address: " + board[r][c].pos + " Original position address: " +  currentBoard[r][c].pos);
				}
			}
		}
		if (board[newPos.x][newPos.y] != null) {
			board[newPos.x][newPos.y].updatePoint(pHandler.boardObj.REMOVED_PIECES);
		}
		board[newPos.x][newPos.y] = board[pieceMoved.pos.x][pieceMoved.pos.y];
		//System.out.print(board[pieceMoved.pos.x][pieceMoved.pos.y]);
		board[pieceMoved.pos.x][pieceMoved.pos.y] = null;
		if (board[newPos.x][newPos.y] != null) {
			board[newPos.x][newPos.y].updatePoint(newPos);
		}
		//System.out.println("Clone position: " + board[newPos.x][newPos.y].pos + " Original position address: " +  currentBoard[pieceMoved.pos.x][pieceMoved.pos.y].pos);
		//FIX ME ASAP: UPDATE POINT CHANGES BOTH DEEP COPY AND ORIGINAL MAKING IT SEEM AS IF THE CLONE METHOD IS FAULTY
		pHandler.updateAllPieces(board);
		if (board != currentBoard) {
			return board;
		} else { 
			return null;
		}
	}
}
