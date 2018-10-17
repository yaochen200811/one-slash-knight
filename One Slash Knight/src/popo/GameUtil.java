package popo;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author yao
 */
public class GameUtil {
    public static Image getImage(String path){
        URL u = GameUtil.class.getClassLoader().getResource(path);
        BufferedImage imag=null;
        try {
            imag =ImageIO.read(u);
        } catch (IOException ex) {
            Logger.getLogger(GameUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return imag;
    }
}
