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
		for (Piece[] row: makeTheoryBoard) {
			for (Piece piece: row) {
				if (piece != null) {
					if (piece.type == "King") {
						if (piece.color == "white") {
							kings[0] = piece;
						} else {
							kings[1] = piece;
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
				for (Point moves: attacker.posMoves) {
					if (moves.equals(kings[0].pos)) {
						return true;
					}
				}
			}
			} else {
				for (Piece attacker: white) {
					for (Point moves: attacker.posMoves) {
						if (moves.equals(kings[1].pos)) {
							return true;
						}
					}
				}
		}
		return false;
	}

	public boolean checkForMate(PieceManager pHandler) {
		return false;
	}


	public Piece[][] makeTheoryBoard( Piece[][] currentBoard, Piece pieceMoved, Point newPos, PieceManager pHandler){
		Piece[][] board = new Piece[8][8];
		for (int r = 0; r < currentBoard.length; r++) {
			for (int c = 0; c < currentBoard.length; c++) {
				if ( currentBoard[r][c] != null) {
					board[r][c] = (Piece) currentBoard[r][c].clone();
				}
			}
		}
		board[newPos.x][newPos.y] = board[pieceMoved.pos.x][pieceMoved.pos.y];
		board[pieceMoved.pos.x][pieceMoved.pos.y] = null;
		if (board[newPos.x][newPos.y] != null) {
			board[newPos.x][newPos.y].updatePoint(newPos);
		}
		pHandler.updateAllPieces(board);
		return board;
	}
}
