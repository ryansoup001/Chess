import java.awt.Point;
import java.util.Arrays;
  
public class Board {
  public Board(){
    this.makeBoard();
    this.setupBoard();
    this.showBoard();
    new PieceManager(this);
  }
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
  public void setupBoard() {
    for (int i  = 0; i < 8; i++) {
      board[i][1] = new Pawn("white","Pawn",1,new Point(i,1));
      board[i][6] = new Pawn("white","Pawn",1,new Point(i,6));
    }
  }
  public void showBoard() {
    for (Piece[] row : this.board){
      System.out.println(Arrays.toString(row));
    }
  }
  public void getBoard(Piece[][] newBoard) {
   this.board = newBoard;
  }
}
