package main;

import objects.*;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject(){
        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = 23 * gp.tileSize;
        gp.obj[0].worldY = 7 * gp.tileSize;

        gp.obj[1] = new OBJ_Key();
        gp.obj[1].worldX = 22 * gp.tileSize;
        gp.obj[1].worldY = 7 * gp.tileSize;

        gp.obj[2] = new OBJ_Chest();
        gp.obj[2].worldX = 26* gp.tileSize;
        gp.obj[2].worldY = 7*gp.tileSize;
        gp.obj[3] = new OBJ_Door();
        gp.obj[3].worldX = 30* gp.tileSize;
        gp.obj[3].worldY = 7*gp.tileSize;
        gp.obj[4] = new OBJ_HealPotion();
        gp.obj[4].worldX = 28*gp.tileSize;
        gp.obj[4].worldY = 7*gp.tileSize;
        gp.obj[5] = new OBJ_Shield();
        gp.obj[5].worldX = 28*gp.tileSize;
        gp.obj[5].worldY = 6*gp.tileSize;
        gp.obj[6] = new OBJ_SpeedBoots();
        gp.obj[6].worldX = 29*gp.tileSize;
        gp.obj[6].worldY = 10*gp.tileSize;
    }
}
