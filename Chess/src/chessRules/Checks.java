package chessRules;
import chesspackage.Board;
import chesspackage.PieceManager;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

import Piecespackage.*;
public class Checks {
	Piece[][] board;
	public boolean checkForChecks(Board boardObj, Piece[][] board) {
		this.board = board;
		Piece king = null;
		ArrayList<Piece> blackPieces = new ArrayList<Piece>();
		ArrayList<Piece> whitePieces = new ArrayList<Piece>();
		for (int r = 0; r < board.length; r++ ) {
			for (int c = 0; c < board[r].length; c++) {
				Piece piece = board[r][c];
				if (piece != null) {
					  if (piece.color == "white") {
						  whitePieces.add(piece);
					  } else {
						  blackPieces.add(piece);
					  }
				}
			}
		}
		// Getting our Kings position
		if (boardObj.pieceManager.turn == "white") {
			for (Piece piece: whitePieces) {
				if(piece.type  == "King") {
					king = piece;
				}
			}
			
			// Checking if any their next moves can capture our king
			for (Piece piece: blackPieces) {
				for (int i = 0;  i < piece.posMoves.size(); i++ ) {
					if (piece.posMoves.get(i).equals(king.pos)) {
						return true;
					}
				}
			}
		} else {
			// Getting our Kings position
			for (Piece piece: blackPieces) {
				if(piece.type  == "King") {
					king = piece;
				}
			}
			// Checking if any their next moves can capture our king
			for (Piece piece: whitePieces) {
				for (int i = 0;  i < piece.posMoves.size(); i++ ) {
					if (piece.posMoves.get(i).equals(king.pos)) {
						System.out.println(piece + " is attacking the " + king.color + " " + king);
						/* For Ensuring theorectical board dont affect the actual current board
					    for (Piece[] row : board){
					     System.out.println(Arrays.toString(row));
					    }
					    for (Piece[] row : boardObj.getBoard()){
						     System.out.println(Arrays.toString(row));
						 }
						 */
						return true;
					}
				}
			}
		}
		return false;
		
	}
	
	
	public Piece[][] makeTheoryBoard(PieceManager pHandler ,Piece[][] currentBoard, Piece pieceMoved, Point newPos){
		board = currentBoard.clone(); // I dont know if this affects the main board or not
		board[pieceMoved.pos.x][pieceMoved.pos.y] = null;
		board[newPos.x][newPos.y] = pieceMoved;
		pHandler.updateAllPieces(currentBoard);
		return board;
		
	}
}
