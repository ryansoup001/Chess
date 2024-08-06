package chesspackage;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Point;
import java.util.Dictionary;
import java.util.Hashtable;

import Piecespackage.Piece;
import chessRules.Checks;
public class PieceManager {
  public Board boardObj;
  public Piece pieceSelected;
  public ArrayList<String> pastMoves = new ArrayList<String>();
  public String turn = "white";
  public String oppositeTurn = "black";
  public Dictionary[] capturedPieces = new Dictionary[2];
  public ArrayList<Piece> black = new ArrayList<Piece>();
  public ArrayList<Piece> white = new ArrayList<Piece>();
  public Checks checkHandler; 
  
  public PieceManager(Board boardObj) {
	checkHandler = new Checks();
	for (int i = 0; i > capturedPieces.length; i ++){
		 Dictionary<String, Integer> Captures = new Hashtable<>();
	     Captures.put("Pawn",0);
	     Captures.put("Knight",0);
	     Captures.put("Bishop", 0);
	     Captures.put("Rook",0);
	     Captures.put("Queen",0);
	}
    this.boardObj = boardObj;
    printToMove();
  }
  public void updateAllPieces(Piece[][] board) {
	  
//	  for (int r = 0; r < board.length; r++ ) {
//		  for (int c = 0; c < board[r].length; c++ ) {
//			  if (board[r][c] != null) {
//				  board[r][c].protectedByPiece = false;
//			  }
//		  }
//	  }
	  for (int r = 0; r < board.length; r++ ) {
		  for (int c = 0; c < board[r].length; c++ ) {
			  if (board[r][c] != null) {
				  board[r][c].updateMoves(board);
			  }
		  }
	  }
  }
  public void printToMove() {
	  System.out.println(turn + " to move" );
  }
  // Handles Move
  public String changeMove() {
   if (turn == "white") {
	   oppositeTurn = turn;
	   return "black";
   } else {
	   oppositeTurn = turn;
	   return "white";
   }
  }
  public String pointToString(Point pos){
	  return "("+pos.x + "," + pos.y+")";
  }
  //Handles Piece Selection
  public void clicks(Point pos) {
	  int x = (pos.x/boardObj.TILESIZE);
	  int y = (pos.y/boardObj.TILESIZE);
	  if (x < boardObj.getBoard().length && y < boardObj.getBoard()[0].length) {
		  Piece posclicked = boardObj.getBoard()[x][y];
		  if (posclicked != null) {  // either selecting a piece capturing piece or selecting a different piece
			  if (posclicked.color == turn) {
				  pieceSelected = posclicked;
				  System.out.println(pieceSelected + " has been selected");
				  this.boardObj.pieceSelected = pieceSelected; 
			  } else if (pieceSelected != null ) {
				  //System.out.println("You requested to move " + pieceSelected + " to " + pointToString(new Point(x,y)));
				  this.movePiece(pieceSelected,posclicked.pos);
				  this.boardObj.pieceSelected = null; 
			  }
	  		} else if (pieceSelected != null) {
	  			this.movePiece(pieceSelected,new Point(x,y));
	  			this.boardObj.pieceSelected = null; 
	  		}
	 }
	  this.boardObj.repaint();
  }
  // Handles Piece Moves
  public void movePiece(Piece piece , Point newpos ) {
    Piece[][] board = this.boardObj.getBoard();
    Piece[][] futureBoard = checkHandler.makeTheoryBoard(board,piece,newpos,this);
    if (board[newpos.x][newpos.y] != null) {    //Checks if piece is in the square we're trying to move onto is occupied	
      Piece capturedPiece = board[newpos.x][newpos.y];
    //  System.out.println(piece.checkMoves(newpos));
      if (piece.checkMoves(newpos) && !checkHandler.checkForChecks(futureBoard ,turn)){ 
    	  // add Piece to captured array and it in a dictionary
    	  if (capturedPiece.color == "black") {
    		  //capturedPieces[0].put(capturedPiece.type,(capturedPieces[0].get(capturedPiece.type)));
    	  }
    	  System.out.println("Captured Piece");
    	  board[newpos.x][newpos.y] = piece;
    	  board[piece.pos.x][piece.pos.y] = null;
    	  piece.updatePoint(newpos);
    	  updateAllPieces(board);
    	  turn = this.changeMove();
      	  printToMove();
      	if(checkHandler.checkForChecks(board,turn)) {
    		String result = "Check";
    		if(checkHandler.checkForMate(board,turn, this)) {
    			result += "mate!";
    			boardObj.changeGameState(2);
    		}
    		System.out.println(result);
    	} 
    	
      }
    } else if (piece.checkMoves(newpos) && !checkHandler.checkForChecks(futureBoard ,turn)) { // Check if piece can legally move this direction
    	System.out.println("Move is legal . . Moving Piece");
    	board[piece.pos.x][piece.pos.y] = null;
    	board[newpos.x][newpos.y] = piece;
    	piece.updatePoint(newpos);
    	updateAllPieces(board);
    	turn = this.changeMove();
    	printToMove();
    	
    	
    	
    	if(checkHandler.checkForChecks(board,turn)) {
    		String result = "Check";
    		if(checkHandler.checkForMate(board,turn,this)) {
    			result += "mate!";
    			boardObj.changeGameState(2);
    		}
    		System.out.println(result);
    	} 
    	
    	
    } else {
    	System.out.println("Moved piece to Point:" + newpos + "  possible moves:" + piece.posMoves);
    }
    pieceSelected = null;
    this.boardObj.repaint();
}
  
}


