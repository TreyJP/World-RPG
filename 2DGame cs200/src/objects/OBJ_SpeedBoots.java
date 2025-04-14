package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_SpeedBoots extends SuperObject{
    public OBJ_SpeedBoots(){
        name = "SpeedBoots";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
        }catch (IOException e) {
            e.printStackTrace();
        }
        collision = false;
    }
}
