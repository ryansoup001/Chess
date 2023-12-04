public class Game implements Runnable {
private final int FPS_SET = 20;
Board board;
  public Game() {
      Thread thread = new Thread();
      board = new Board();
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
      // show the board
       lastFrame = now;
       frames++;
     }
      if(System.currentTimeMillis() - lastCheck >= 1000) {
       lastCheck = System.currentTimeMillis();
        frames = 0;
     }
  }

  }
}