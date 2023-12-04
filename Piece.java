
import java.awt.Point;

public class Piece {

  public String color;
  public String type;
  public int value;
  Point pos;
  public boolean legalmove;

  public Piece(String color, String type, int value, Point pos) {

    this.color = color;
     this.type = type;
     this.value = value;
     this.pos = pos;
  }
  public String toString() {
    return this.type;
  }
}


class Pawn extends Piece {
  //moves forward 1 square
  public Pawn(String color, String type, int value, Point pos) {
    super(color, type, value, pos);
  }
}

class Rook extends Piece {
  // moves forward, backwards, right and left
  public Rook(String color, String type,int value, Point pos) {
    super(color, type, value, pos);
  }
}

class Knight extends Piece {
  // moves in a L shape
  public Knight(String color, String type, int value, Point pos) {
    super(color, type, value, pos);
  }
}

class Bishop extends Piece {
  // moves down diagonal lines
  public Bishop(String color, String type, int value, Point pos) {
    super(color, type, value, pos);
  }
}

class Queen extends Piece {
  // moves in both directions of bishops and rooks
  public Queen(String color, String type,int value, Point pos) {
    super(color, type, value, pos);
  }
}

class King extends Piece {
  // moves one square in each direction
  public King(String color, String type, int value, Point pos) {
    super(color, type, value, pos);
  }
}




