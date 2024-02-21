package Piecespackage;

import java.awt.Point;
import java.util.ArrayList;

import chesspackage.Board;
import chesspackage.MoveManager;

public class King extends Piece {
	// moves one square in each direction
	MoveManager moveM = new MoveManager();
	public King(String color, String type, int value, Point pos, Board boardObj) {
		super(color, type, value, pos, boardObj);
	}
	public boolean checks() {
		return true;
	}
	@Override
	public void updateMoves(Piece[][] board) {
		posMoves.clear();
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board.length; c++) {
				if (new Point(r,c).distance(this.pos) == 1.0 || new Point(r,c).distance(this.pos) == (double)Math.sqrt(2) ) {
					/* Quick Note From Ryan!
					 * // if distance method returns one square is either in front, left, right or behind king 
					 * if diagonally away from king it is root of 2 (because of Pythagereom thereom)
					 */
					if (board[r][c] == null) {
						posMoves.add(new Point(r,c)); 
					}
				}
			}
		}
	}
}