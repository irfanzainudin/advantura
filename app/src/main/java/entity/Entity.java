package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX, worldY;
    public int speed;

    public BufferedImage idle_up1, idle_up2, idle_down1, idle_down2, idle_right1, idle_right2, idle_left1, idle_left2, move_up1, move_up2, move_down1, move_down2, move_right1, move_right2, move_left1, move_left2;
    // public Boolean idle;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNumber = 1;

    public Rectangle solidArea;
    public boolean collisionOn = false;
    
    public Entity() {}
}
