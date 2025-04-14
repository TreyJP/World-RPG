package main;

import objects.OBJ_HealPotion;
import objects.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;
    Font arial_40, arial_80B;
    BufferedImage keyImage, heartImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");


    public UI(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 20);
        arial_80B = new Font("Arial", Font.BOLD, 35);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
        OBJ_HealPotion heart = new OBJ_HealPotion();
        heartImage = heart.image;

    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

        if (gameFinished == true) {
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            String text;
            int  textLength;
            int x;
            int y;
            g2.setFont(arial_80B);
            g2.setColor(Color.YELLOW);

            text = "You Have Completed The Game";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - (textLength/2);
            y = gp.screenWidth/2 - (gp.tileSize*2);
            g2.drawString(text, x, y);

            text = "Your Time Is: " + dFormat.format(playTime);
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - (textLength/2);
            y = gp.screenWidth/2 - (gp.tileSize);
            g2.drawString(text, x, y);

            gp.gameThread = null;

        }
        else {
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize/2, gp.tileSize/2, null);
            g2.drawString("x " + gp.player.hasKey, 50, 45);
            g2.drawImage(heartImage, gp.tileSize/2, gp.tileSize+16, gp.tileSize/2, gp.tileSize/2, null);
            g2.drawString("x " + gp.player.healthPoints, 50, 85);


            playTime +=(double)1/60;
            g2.drawString("Time: "+ dFormat.format(playTime),gp.tileSize*12,45);

            if (messageOn==true){
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message,gp.tileSize*7, gp.tileSize*10);


                messageCounter++;

                if (messageCounter > 150) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }

    }
}
