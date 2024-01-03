package chessRules;
import chesspackage.Board;
import chesspackage.PieceManager;

import java.awt.Point;
import java.util.ArrayList;

import Piecespackage.*;
public class Checks {
	Piece[][] board;
	public boolean checkForChecks(Board boardObj, Piece[][] board) {
		this.board = board;
		Piece king = null;
		ArrayList<Piece> blackPieces = boardObj.pieceManager.black;
		ArrayList<Piece> whitePieces = new ArrayList<Piece>();
		whitePieces.addAll(boardObj.pieceManager.white);
		blackPieces.addAll(boardObj.pieceManager.black);
		// Getting opposing Kings position
		if (boardObj.pieceManager.turn == "white") {
			for (Piece piece: whitePieces) {
				if(piece.type  == "King") {
					king = piece;
					System.out.println("Found King!");
				}
			}
			// Checking if any our next moves 
			for (Piece piece: blackPieces) {
				for (int i = 0;  i < piece.posMoves.size(); i++ ) {
					if (piece.posMoves.get(i).equals(king.pos)) {
						return true;
					}
				}
			}
		} else {
			for (Piece piece: blackPieces) {
				if(piece.type  == "King") {
					king = piece;
				}
			}
			for (Piece piece: whitePieces) {
				for (int i = 0;  i < piece.posMoves.size(); i++ ) {
					if (piece.posMoves.get(i).equals(king.pos)) {
						return true;
					}
				}
			}
		}
		return false;
		
	}
	public Piece[][] makeTheoryBoard(PieceManager pHandler ,Piece[][] currentBoard, Piece pieceMoved, Point newPos){
		board = currentBoard;
		board[pieceMoved.pos.x][pieceMoved.pos.y] = null;
		board[newPos.x][newPos.y] = pieceMoved;
		pHandler.updateAllPieces(currentBoard);
		return board;
		
	}
}
