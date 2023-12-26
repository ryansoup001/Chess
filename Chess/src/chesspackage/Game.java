package chesspackage;

public class Game implements Runnable {
private final int FPS_SET = 120;
Board board;
  public Game() {
	  board = new Board();
      Thread thread = new Thread(this);
      thread.start();
    }
  public void run() { 
   //Sets up game loop
   double timePerFrame = 1000000000.0/FPS_SET;
   long lastFrame = System.nanoTime();
   long now = System.nanoTime();
   int frames = 0;
   long  lastCheck = System.currentTimeMillis();
   while(true) {
     now = System.nanoTime();
     if(now - lastFrame >= timePerFrame) {
         
        board.repaint();
        //update board
        lastFrame = now;
        frames++;
      }
      if(System.currentTimeMillis() - lastCheck >= 1000) {
        lastCheck = System.currentTimeMillis();
        this.board.display.jframe.setTitle("Game Fps: " + frames);
        frames = 0;
      }
    }
  }
}