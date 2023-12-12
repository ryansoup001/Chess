package chesspackage;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import javax.swing.JPanel;

public class Board extends JPanel {
  BufferedImage chessBoardImg;
  Display display;
  Piece[][] board;
  public final int PIECESIZE  = 35;
  public final int TILESIZE = 64;
  String[] rows = {"a", "b", "c", "d", "e", "f", "g", "h"};
  PieceManager pieceManager;
  public Board() {
    setPreferredSize(new Dimension(900,900));
    this.makeBoard();
    this.setupBoard();
    chessBoardImg = this.getBoardImg();
    display = new Display(this);
    pieceManager = new PieceManager(this);
  }
  public void makeBoard() {
    Piece[][] board = new Piece[8][8];
    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board[r].length; c++) {
       board[r][c] = null;
      }
    }
    this.board = board;
  }
  public Piece[][] getBoard() {
   return this.board;
  }
  public BufferedImage getBoardImg() {
    ImageHandler imghandle = new ImageHandler();
    BufferedImage boardImg = imghandle.setup("/chesspackage/assets/brown.png");
    return boardImg;
  }
  public void setupBoard() {
    //Placing Pawns
    for (int i  = 0; i < 8; i++) {
      board[i][1] = new Pawn("white","Pawn",1,new Point(i,1));
      board[i][6] = new Pawn("black","Pawn",1,new Point(i,6));
    }
    
    //Placing Rooks
    board[0][0] = new Rook("white","Rook",5,new Point(0,0));
    board[7][0] = new Rook("white","Rook",5,new Point(7,0));
    board[0][7] = new Rook("black","Rook",5,new Point(0,7));
    board[7][7] = new Rook("black","Rook",5,new Point(7,7));
    
    //Placing Knights
    board[1][0] = new Knight("white","Knight",5,new Point(1,0));
    board[6][0] = new Knight("white","Knight",5,new Point(6,0));
    board[1][7] = new Knight("black","Knight",5,new Point(1,7));
    board[6][7] = new Knight("black","Knight",5,new Point(6,7));

    //Placing Bishops
    board[2][0] = new Bishop("white","Bishop",5,new Point(2,0));
    board[5][0] = new Bishop("white","Bishop",5,new Point(5,0));
    board[2][7] = new Bishop("black","Bishop",5,new Point(2,7));
    board[5][7] = new Bishop("black","Bishop",5,new Point(5,7));

    //Placing Queens
    board[4][0] = new Queen("white","Queen",9,new Point(4,0));
    board[3][7] = new Queen("black","Queen",9,new Point(3,7));

    //Placing Kings
    board[3][0] = new King("white","King",10,new Point(3,0));
    board[4][7] = new King("black","King",10,new Point(4,7));
  }
  public void showBoard(Graphics g) {
//    System.out.print("\033[H\033[2J");
//    System.out.flush();
//    for (Piece[] row : this.board){
//     System.out.println(Arrays.toString(row));
//    }
    g.drawImage(chessBoardImg,0,0,TILESIZE * 8,TILESIZE * 8,null);
    for (int r = 0; r < this.board.length; r++) {
      for (int c  = 0; c < this.board[r].length;c++) {
        if (this.board[r][c] != null) {
          g.drawImage(this.board[r][c].getImage(),this.board[r][c].pos.x * TILESIZE,this.board[r][c].pos.y * TILESIZE,PIECESIZE,PIECESIZE,null);
        }
      }
    }
  }
  public void updateBoard(Piece[][] newBoard) {   // Dont think i need this but keeping it just in case
   this.board = newBoard;
  }
  
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    this.showBoard(g);
    
    g.dispose();
  }
}
