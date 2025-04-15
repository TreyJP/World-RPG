package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class OBJ_Key extends SuperObject{
    GamePanel gp;

    public OBJ_Key(){
        name = "Key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void draw(Graphics g2, GamePanel gp) {
        g2.setColor(Color.YELLOW);
        g2.drawRect(gp.obj[0].worldX, gp.obj[0].worldY, 16,16);
    }
}
