package chesspackage;

import javax.swing.JFrame;

public class Display {
  public JFrame jframe;
  	public Display(Board board) {
    jframe = new JFrame();
    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jframe.setLocationRelativeTo(null);
    jframe.add(board);
    jframe.setResizable(false);
    jframe.pack();
    jframe.setVisible(true);
  	}
}
