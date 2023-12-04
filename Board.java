public class Board {
  Piece[][] board;
  public Piece[][] makeBoard() {
    Piece[][] board = new Piece[8][8];
    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board[r].length; c++) {
       board[r][c] = null;
      }
    }
    this.board = board;
    return board;
  }
  public Piece[][] getBoard() {
   return this.board;
  }
  public void getBoard(Piece[][] newBoard) {
   this.board = newBoard;
  }
}
