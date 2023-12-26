package chesspackage;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
public class Piece {
  public ImageHandler imgHndle = new ImageHandler();
  public String color;
  public ArrayList<Point> posMoves = new ArrayList<Point>();
  public String type;
  public int value;
  Point pos;

  public Piece(String color, String type, int value, Point pos) {
    this.color = color;
     this.type = type;
     this.value = value;
     this.pos = pos;
  }
  public boolean checkMoves(Point newpos) {
  	for(int i = 0; i <  posMoves.size(); i++) {
  		if (posMoves.get(i).equals(newpos)) {
  			return true;
  		}
  	}
  	return false;
  }
	public void updatePoint(Point newpos) {
		this.pos = newpos;
	}
	
  public boolean capturePiece( Piece capturedPiece) {
    if (capturedPiece.color == this.color) {
      return false;
    }
    // Define attack Patterns in specific classes
    if (this.checkMoves(pos)) {
    	return true;
    } else {
    	return false;
    }
  }
  public BufferedImage getImage() {
    BufferedImage image = imgHndle.setup("/chesspackage/assets/" + this.color.substring(0, 1)  + this.type.substring(0,1) + ".png");
    return image;
  }
  public String toString() {
    return this.type;
  }
  
}


class Pawn extends Piece {
  //moves forward 1 square
	public int orientation = 1;
	public boolean firstMove = true;
	public Pawn(String color, String type, int value, Point pos) {
		super(color, type, value, pos);
		if (this.color == "black") {
			orientation  = -1;
		}
		this.updatePoint(pos);
	}
	public void updatePoint(Point newpos) {
		this.pos = newpos;
		this.findMoves();
	}
	
public void findMoves() {
		posMoves.clear();

		if (firstMove) {
			posMoves.add(new Point(pos.x,pos.y + (orientation * 2)));
			posMoves.add(new Point(pos.x,pos.y + orientation));
			firstMove = false;
		} else {
			posMoves.add(new Point(pos.x,pos.y + orientation));
		}
	}

  public boolean capturePiece(Piece capturedPiece) {
	  if (capturedPiece.color == this.color) {
	      return false;
	   }
	  if (Math.abs(capturedPiece.pos.x - this.pos.x) == 1 && this.pos.y == capturedPiece.pos.y) {
		  return true;
	  }
	  return false;
  }
}

class Rook extends Piece {
  // moves forward, backwards, right and left
  public Rook(String color, String type,int value, Point pos) {
    super(color, type, value, pos);
    updatePoint(this.pos);
  }
	public void updatePoint(Point newpos) {
		this.pos = newpos;
		this.findMoves();
	}
	
	
	  public void findMoves() {
			posMoves.clear();
			// Finding Forward(White) or Backward(Black) Moves 
			for (int i = 0;  i < (8-pos.y) ; i ++) { 
				posMoves.add(new Point(pos.x,pos.y + i));
			}
			// Finding Right(White) or Left(Black) Moves 
			for (int i = 0;  i < (8-pos.x) ; i ++) { 
				posMoves.add(new Point(pos.x + i,pos.y));
			}
			// Finding Backward(White) or Forward(Black) Moves 
			for (int i = 0;  i < pos.y ; i ++) { 
				posMoves.add(new Point(pos.x,pos.y - i));
			}
			// Finding Left(White) or Right(Black) Moves 
			for (int i = 0;  i < pos.x ; i ++) { 
				posMoves.add(new Point(pos.x - i,pos.y));
			}
			
		}
}

class Knight extends Piece {
  // moves in a L shape
  public int up  = 3;
  public int over = 1;
  public Knight(String color, String type, int value, Point pos) {
    super(color, type, value, pos);
  }
  public BufferedImage getImage() {
    BufferedImage image = imgHndle.setup("/chesspackage/assets/" + this.color.substring(0, 1)  + "N.png");
    return image;
  }
  public void findMoves() {

	  }
  }

class Bishop extends Piece {
  // moves down diagonal lines
  public Bishop(String color, String type, int value, Point pos) {
    super(color, type, value, pos);
    updatePoint(this.pos);
  }
  
  
  public void updatePoint(Point newpos) {
	this.pos = newpos;
	this.findMoves();
	}
	
  public void findMoves() {
		posMoves.clear();
		// Finding Forward(White) or Backward(Black) Moves 
		for (int i = 0;  i < (8-pos.y) ; i ++) { 
			posMoves.add(new Point(pos.x+i,pos.y + i));
		}
		// Finding Right(White) or Left(Black) Moves 
		for (int i = 0;  i < (8-pos.x) ; i ++) { 
			posMoves.add(new Point(pos.x + i,pos.y+i));
		}
		// Finding Backward(White) or Forward(Black) Moves 
		for (int i = 0;  i < pos.y ; i ++) { 
			posMoves.add(new Point(pos.x-i,pos.y - i));
		}
		// Finding Left(White) or Right(Black) Moves 
		for (int i = 0;  i < pos.x ; i ++) { 
			posMoves.add(new Point(pos.x - i,pos.y-i));
		}
		
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
  public void updatePoint(Point newpos) {
	  this.pos = newpos;
	  this.findMoves();
  }
  public void findMoves() {
	  for (int r = 0; r < 8; r++) {
		  for (int c = 0; c < 8; c++) {
			  if (new Point(r,c).distance(this.pos) == 1) {
				  posMoves.add(new Point(r,c));
			  }
		  }
	  }
  }
}




