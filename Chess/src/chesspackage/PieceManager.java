package chesspackage;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Point;
import java.util.Dictionary;
import java.util.Hashtable;
class PieceManager {
  Board board;
  Piece pieceSelected;
  ArrayList<String> pastMoves = new ArrayList<String>();
  public String turn = "white";
  Dictionary[] capturedPieces = new Dictionary[2];
  public PieceManager(Board board) {
	for (int i = 0; i > capturedPieces.length; i ++){
		 Dictionary<String, Integer> Captures= new Hashtable<>();
	     Captures.put("Pawn",0);
	     Captures.put("Knight",0);
	     Captures.put("Bishop", 0);
	     Captures.put("Rook",0);
	     Captures.put("Queen",0);
	}
    this.board = board;
    System.out.print(turn + " to move" );
  }

  public void changeMove() {
   if (turn == "white") {
	   turn = "black";
   } else {
	   turn = "white";
   }
  }
  public void clicks(Point pos) {
	  int x = (pos.x/board.TILESIZE);
	  int y = (pos.y/board.TILESIZE);
	  Piece posclicked = board.getBoard()[x][y];
	  //System.out.print(posclicked + "at Point " + posclicked.pos);
	  if (posclicked != null) {  // either selecting a piece capturing piece or selecting a different piece
		  if (posclicked.color == turn) {
			  pieceSelected = posclicked;
		  }	else if (pieceSelected != null ) {
			  this.movePiece(pieceSelected,posclicked.pos);
		  }
	  } else if (pieceSelected != null) {
		  this.movePiece(pieceSelected,new Point(x,y));
	  }
  }
  public void movePiece(Piece piece , Point newpos ) {
	 
    Piece[][] board = this.board.getBoard();
    if (board[newpos.x][newpos.y] != null) {    //Checks if piece is in the square we're trying to move onto
      Piece capturedPiece = board[newpos.x][newpos.y];
      if (piece.capturePiece(capturedPiece) == true){  // add Piece to captured array and it in a dictionary
    	  if (capturedPiece.color == "black") {
    		  //capturedPieces[0].put(capturedPiece.type,(capturedPieces[0].get(capturedPiece.type)));//IDK
    	  }
    	  board[piece.pos.x][piece.pos.y] = null;
    	  board[newpos.x][newpos.y] = piece;
    	  
      }
    } else if (piece.checkMoves(newpos)) { // Check if piece can legally move this direction
    	piece.updatePoint(newpos);
    	board[piece.pos.x][piece.pos.y] = null;
    	board[newpos.x][newpos.y] = piece;
    	this.changeMove();
    } else {
    	System.out.println("Moved piece to Point:" + newpos + "  possible moves:" + piece.posMoves);
    }
}
}


