package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;


    public final int screenX;
    public final int screenY;
    public int hasKey = 0;
    int hasSword = 0;
    int hasStrengthPotion = 0;
    public int healthPoints = 10;
    int hasShield = 0;
    int hasSpeetBoots = 0;
    String playerMessage;


    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDefaultValues();
        getPlayerImage();

    }
    public void setDefaultValues () {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 5;
        direction = "down";
    }

    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/tile012.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/tile013.png"));

            down1 = ImageIO.read(getClass().getResourceAsStream("/player/tile018.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/tile019.png"));

            left1 = ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/left2.png"));

            right1 = ImageIO.read(getClass().getResourceAsStream("/player/tile024.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/tile025.png"));


        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(){
        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {


            if (keyH.upPressed == true) {
                direction = "up";

            }
            if (keyH.downPressed == true) {
                direction = "down";


            }
            if (keyH.leftPressed == true) {
                direction = "left";


            }
            if (keyH.rightPressed == true) {
                direction = "right";

            }
            // check tile collision
            collisionOn = false;
            gp.collisionChecker.checkTile(this);

            //check object collision

            int objIndex = gp.collisionChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // if collision is false player can move
            if (!collisionOn) {
                switch(direction) {
                    case "up":
                        worldY = worldY - speed;
                        break;
                    case "down":
                        worldY = worldY + speed;
                        break;
                    case "left":
                        worldX = worldX - speed;
                        break;
                    case "right":
                        worldX = worldX + speed;
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

        }
    }

    public void pickUpObject(int i) {
        if (i != 999) {
            String objectName = gp.obj[i].name;

            switch(objectName) {
                case "Key":
                    hasKey++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Key Found");
                    break;
                case "Chest":
                    if(hasKey > 0){
                        gp.obj[i] = null;
                        hasKey--;
                        gp.ui.showMessage("You Used A Key To Open A Chest");
                        if(hasSword == 0) {
                            hasSword++;
                            gp.ui.showMessage("Sword Found");
                        }
                        else if (hasSword == 1) {
                            hasStrengthPotion++;
                            gp.ui.showMessage("Damage Multiplier Found");
                        }
                    }
                    else {
                        gp.ui.showMessage("Missing Key");
                    }
                    break;
                case "Door":
                    if(hasKey > 0) {
                        gp.obj[i]=null;
                        hasKey--;
                        gp.ui.showMessage("Door Opened");
                    }
                    else {
                        gp.ui.showMessage("Missing Key");
                        }
                    break;
                case "HealPotion":
                    if (healthPoints != 10) {
                        gp.ui.showMessage("Health Restored");
                        gp.obj[i]=null;
                        healthPoints = 10;
                    } else {
                        gp.ui.showMessage("Health Full");
                    }
                    break;
                case "Shield":
                    if (hasShield == 0) {
                        gp.ui.showMessage("Shield Equipped");
                        String playerMessage = "Shield Equipped";
                        hasShield++;
                        gp.obj[i] = null;
                        break;
                    } break;
                case "SpeedBoots":
                    //if (hasSpeetBoots == 0) {
                    //    gp.obj[i]=null;
                    //    gp.ui.showMessage("Speed Increase");
                    //    speed = 3;
                    //    break;
                    //}
                    gp.ui.gameFinished = true;
                    break;



            }
        }

    }

    public void draw(Graphics2D g2){
        //g2.setColor(Color.white);

        //g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch(direction){
            case "up":
                if(spriteNum == 1) {
                    image = up1;
                }
                if(spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1) {
                    image = down1;
                }
                if(spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1) {
                    image = left1;
                }
                if(spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                }
                if(spriteNum == 2) {
                    image = right2;
                }
                break;

        }

        g2.drawImage(image,screenX,screenY,gp.tileSize*2,gp.tileSize*2, null);
    }
}
