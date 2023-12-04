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
    //Placing Pawns
    for (int i  = 0; i < 8; i++) {
      board[i][1] = new Pawn("white","Pawn",1,new Point(i,1));
      board[i][6] = new Pawn("white","Pawn",1,new Point(i,6));
    }
    
    //Placing Rooks
    board[0][0] = new Rook("white","Rook",5,new Point(0,0));
    board[0][7] = new Rook("white","Rook",5,new Point(0,7));
    board[7][0] = new Rook("black","Rook",5,new Point(7,0));
    board[7][7] = new Rook("black","Rook",5,new Point(7,7));
    
    //Placing Knights
    board[1][0] = new Knight("white","Knight",5,new Point(1,0));
    board[1][7] = new Knight("white","Knight",5,new Point(1,7));
    board[6][0] = new Knight("black","Knight",5,new Point(6,0));
    board[6][7] = new Knight("black","Knight",5,new Point(6,7));

    //Placing Bishops
    board[2][0] = new Bishop("white","Bishop",5,new Point(2,0));
    board[2][7] = new Bishop("white","Bishop",5,new Point(2,7));
    board[5][0] = new Bishop("black","Bishop",5,new Point(5,0));
    board[5][7] = new Bishop("black","Bishop",5,new Point(5,7));

    //Placing Queens
    board[4][0] = new Queen("white","Queen",9,new Point(0,5));
    board[4][7] = new Queen("black","Queen",9,new Point(0,5));

    //Placing Kings
    board[3][0] = new King("white","King",10,new Point(0,4));
    board[3][7] = new King("black","King",10,new Point(0,4));
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
