class PieceManager{
  public PieceManager(){
    this.setupBoard();
  }
  public void setupBoard(){
    print("hi");
  }
  public boolean capturePiece(Piece attacker,Piece capturedPiece){
    if (attacker.color == capturedPiece.color){
      return false;
    }
    // Define attack Patterns
    return true;
  }
  public boolean movePiece(Point newpos,Piece piece){
    board = getBoard();
    if (board[newpos.x][newpos.y] != null ){  //Checks if piece is in the square we're trying to move onto
      capturePiece(piece,board[newpos.x][newpos.y]);
    }else{                                    // Check if piece can legally move this direction
      
    }
  }
  public Piece[][] makeBoard(){
    Piece[][] board = new Piece[8][8];
    for(int r = 0; r < board.length; r++){
      for(int c = 0; c < board[r].length; c++){
        board[r][c] = null;
      }
    }
    return board;
  }
  public Piece[][] getBoard(){
    return this.board;
  }
  public void getBoard(Piece[][] newBoard){
    this.board = newBoard;
  }
}