package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_HealPotion extends SuperObject{
    public OBJ_HealPotion(){
        name = "HealPotion";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/heart_full.png"));
        }catch (IOException e) {
            e.printStackTrace();
        }
        collision = false;
    }
}
