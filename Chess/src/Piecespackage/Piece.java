package Piecespackage;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import chesspackage.Board;
import chesspackage.ImageHandler;
public abstract class Piece implements Cloneable {
  public boolean showMoves = true;
  final int RAD = 10;
  public ImageHandler imgHndle = new ImageHandler();
  public String color;
  public ArrayList<Point> posMoves = new ArrayList<Point>();
  public String type;
  public int value;
  public Point pos;
  public Board boardObj;
  
  
  public Piece(String color, String type, int value, Point pos, Board boardObj){
	  this.color = color;
	  this.type = type;
	  this.value = value;
	  this.pos = pos;
	  this.boardObj = boardObj;
	  if (this.color == "white") {
		  this.boardObj.pieceManager.white.add(this);
	  } else {
		  this.boardObj.pieceManager.black.add(this);
	  }
  }
  @Override
public Object clone() {
	  try {
		  Object clone = super.clone();
		  Piece piece = (Piece)(clone);
		  piece.boardObj = boardObj;
		  piece.color = color;
		  piece.type = type;
		  piece.value = value;
		  piece.pos = pos;
		return piece;
	} catch (CloneNotSupportedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	  
	  
  }
  public boolean checkMoves(Point newpos) {
  	for(int i = 0; i < posMoves.size(); i++) {
  		if (posMoves.get(i).equals(newpos)) {
  			return true;
  		}
  	}
  	return false;
  }
  
  
	public void updatePoint(Point newpos) {
		this.pos = newpos;
	}
	public abstract void updateMoves(Piece[][] board);
  public boolean capturePiece( Piece capturedPiece) {
    if (capturedPiece.color == color) {
    	System.out.println("Cannot capture your own piece");
    	return false;
    }
    // Define attack Patterns in specific classes
    if (this.checkMoves(capturedPiece.pos)) {
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
  
  
  public void showposMoves(Graphics g) {
	  for (int i = 0; i < posMoves.size(); i++)  {
		  g.fillOval(((posMoves.get(i).x) * boardObj.TILESIZE) + boardObj.TILESIZE/2 , ((posMoves.get(i).y ) * boardObj.TILESIZE) + boardObj.TILESIZE/2,RAD,RAD);
	  }
  }
  
}















