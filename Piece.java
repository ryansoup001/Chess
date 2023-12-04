import java.awt.Point;

public class Piece {
  public String color;
  public String type;
  public String value;
  Point pos;
  public boolean legalmove;
  // public Piece(String color,String type,String value,Point pos) {
  //   this.color = color;
  //   this.type = type;
  //   this.value = value;
  //   this.pos = pos;
  // }

}
class Pawn extends Piece {
  // //forward 1 space
  // public Pawn() {
  //  super();
  // }
  
  
}
class Rook extends Piece {
  // //forward,backwards,left and right
  // public Rook() {
  //   super();
  // }
}
class Knight extends Piece {
  //L shape
  // public Knight() {
  //   super();
  // }
}
class Bishop extends Piece {
// diagonal
  // public Bishop() {
  //   super();
  // }
}
class Queen extends Piece {
 //all directions
  // public Queen() {
  //   super();
  // }
}
class King extends Piece {
// all directions 1 space
  // public King() {
  //   super();
  // }
}