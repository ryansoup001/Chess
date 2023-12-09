import java.awt.Point;
import java.awt.image.BufferedImage;
public class Piece {
  public ImageHandler imgHndle = new ImageHandler();
  public String color;
  public String type;
  public int value;
  Point pos;

  public Piece(String color, String type, int value, Point pos) {
    this.color = color;
     this.type = type;
     this.value = value;
     this.pos = pos;
  }
  public boolean move(Point newpos) {
    return true;
  }
  public boolean capturePiece( Piece capturedPiece) {
    if (capturedPiece.color == this.color) {
      return false;
    }
    // Define attack Patterns
    return true;
  }
  public BufferedImage getImage() {
    BufferedImage image = imgHndle.setup("/assets/" + this.color.substring(0, 1)  + this.type.substring(0,1) + ".png");
    return image;
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
  public boolean move(Point newpos) {
    if (this.pos.y == (newpos.y - 1)){
      return true;
    }
    return true;
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
  public BufferedImage getImage() {
    BufferedImage image = imgHndle.setup("/assets/" + this.color.substring(0, 1)  + "N.png");
    return image;
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




