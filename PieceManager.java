import java.awt.Point;
class PieceManager {
  Board board;
  public PieceManager(Board board) {
    this.board = board;
  }
  
  public boolean capturePiece(Piece attacker,Piece capturedPiece) {
    if (attacker.color == capturedPiece.color) {
      return false;
    }
    // Define attack Patterns
    return true;
  }
  public boolean movePiece(Point newpos,Piece piece) {
    Piece[][] board = this.board.getBoard();
    if (board[newpos.x][newpos.y] != null) {    //Checks if piece is in the square we're trying to move onto
      capturePiece(piece,board[newpos.x][newpos.y]);
    } else {                                    // Check if piece can legally move this direction
      return true;
    }
    return false;
  }
  
}