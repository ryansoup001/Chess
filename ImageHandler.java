import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
public class ImageHandler {
  public BufferedImage setup(String path) {
    try { 
      BufferedImage image = ImageIO.read(getClass().getResourceAsStream(path));
      return image;
    }catch(IOException e) {
      return null;
    }
  }
}