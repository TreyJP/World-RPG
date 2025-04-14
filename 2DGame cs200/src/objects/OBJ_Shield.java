package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Shield extends SuperObject{
    public OBJ_Shield(){
        name = "Shield";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/shield_blue.png"));
        }catch (IOException e) {
            e.printStackTrace();
        }
        collision = false;
    }
}
