import java.util.ArrayList;



import java.awt.Point;
class PieceManager {
  Board board;
  ArrayList<String> pastMoves = new ArrayList<String>();
  public PieceManager(Board board) {
    this.board = board;
  }

  public String toMove() {
    if (pastMoves.size() % 2 == 0) {
      return "white";
    } else {
      return "black";
    }
  }
  public boolean movePiece(Piece piece , Point newpos ) {
    Piece[][] board = this.board.getBoard();
    if (board[newpos.x][newpos.y] != null) {    //Checks if piece is in the square we're trying to move onto
      Piece capturedPiece = board[newpos.x][newpos.y];
      if (piece.capturePiece(capturedPiece) == true){  // add Piece to captured array and it in a dictionary
      
      
      }
    } else if (piece.move(newpos)) {                                    // Check if piece can legally move this direction
      return true;
    }
 return false;
}
}


