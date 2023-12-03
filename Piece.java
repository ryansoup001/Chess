class Piece{
  public string color;
  public string type;
  public string value;
  public int[][] pos;
  public boolean legalmove;
  public Piece(string color,string type,string value,int[][] pos){
    this.color = color;
    this.type = type;
    this.value = value;
    this.pos = pos;
  }

}
class Pawn extends Piece{
  int[][] moves = 
  public Pawn(){
    super();
    this.type = "Pawn";
    this.value = "1";
  }
  
  
}
class Rook extends Piece{
  //forward
}
class Knight extends Piece{
  //L shape
  
}
class Bishop extends Piece{
// diagonal
}
class Queen extends Piece{
 //all directions
}
class King extends Piece{
// all directions limited
}