package entity;

import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

// import java.awt.Color;
import java.awt.Graphics2D;

import advantura.GamePanel;
import advantura.KeyHandler;


public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyHandler;
    
    public int screenX;
    public int screenY;
    
    // TODO: change this to 30 if you want "relaxed" mode
    static final int SPRITE_REFRESH_RATE = 15;
    
    public Player(GamePanel gp, KeyHandler kh) {
        this.gp = gp;
        this.keyHandler = kh;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2  - (gp.tileSize / 2);

        setDefaultValues();
        getPlayerImages();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        // idle = true;
        direction = "idle_down";
    }

    public void getPlayerImages() {
        try {
            // IDLE
            idle_up1 = ImageIO.read(getClass().getResourceAsStream("/player/idle_up1.png"));
            idle_up2 = ImageIO.read(getClass().getResourceAsStream("/player/idle_up2.png"));
            idle_down1 = ImageIO.read(getClass().getResourceAsStream("/player/idle_down1.png"));
            idle_down2 = ImageIO.read(getClass().getResourceAsStream("/player/idle_down2.png"));
            idle_right1 = ImageIO.read(getClass().getResourceAsStream("/player/idle_right1.png"));
            idle_right2 = ImageIO.read(getClass().getResourceAsStream("/player/idle_right2.png"));
            idle_left1 = ImageIO.read(getClass().getResourceAsStream("/player/idle_left1.png"));
            idle_left2 = ImageIO.read(getClass().getResourceAsStream("/player/idle_left2.png"));
            // MOVE
            move_up1 = ImageIO.read(getClass().getResourceAsStream("/player/move_up1.png"));
            move_up2 = ImageIO.read(getClass().getResourceAsStream("/player/move_up2.png"));
            move_down1 = ImageIO.read(getClass().getResourceAsStream("/player/move_down1.png"));
            move_down2 = ImageIO.read(getClass().getResourceAsStream("/player/move_down2.png"));
            move_right1 = ImageIO.read(getClass().getResourceAsStream("/player/move_right1.png"));
            move_right2 = ImageIO.read(getClass().getResourceAsStream("/player/move_right2.png"));
            move_left1 = ImageIO.read(getClass().getResourceAsStream("/player/move_left1.png"));
            move_left2 = ImageIO.read(getClass().getResourceAsStream("/player/move_left2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update()
    {
        // MOVE
        if (keyHandler.upPressed == true ||
            keyHandler.downPressed == true ||
            keyHandler.rightPressed == true ||
            keyHandler.leftPressed == true)
        {
            if (keyHandler.upPressed == true) {
                worldY -= speed;
                direction = "move_up";
            }
            
            if (keyHandler.downPressed == true) {
                worldY += speed;
                direction = "move_down";
            }
            
            if (keyHandler.rightPressed == true) {
                worldX += speed;
                direction = "move_right";
            }
    
            if (keyHandler.leftPressed == true) {
                worldX -= speed;
                direction = "move_left";
            }
        }
        // IDLE
        // If no movement key is pressed, then make character idle
        // ... in the direction that it's currently facing.
        else
        {
            direction = "idle_" + direction.split("_")[1];
        }
    
        spriteCounter++;

        if (spriteCounter > SPRITE_REFRESH_RATE) {
            if (spriteNumber == 1) {
                spriteNumber = 2;
            } else if (spriteNumber == 2) {
                spriteNumber = 1;
            }

            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2)
    {
        BufferedImage image = null;

        switch (direction) {
            case "idle_up":
                if (spriteNumber == 1) {
                    image = idle_up1;
                }
                if (spriteNumber == 2) {
                    image = idle_up2;
                }
                // image = idle_up1;
                break;
            case "idle_down":
                if (spriteNumber == 1) {
                    image = idle_down1;
                }
                if (spriteNumber == 2) {
                    image = idle_down2;
                }
                // image = idle_down1;
                break;
            case "idle_right":
                if (spriteNumber == 1) {
                    image = idle_right1;
                }
                if (spriteNumber == 2) {
                    image = idle_right2;
                }
                // image = idle_right1;
                break;
            case "idle_left":
                if (spriteNumber == 1) {
                    image = idle_left1;
                }
                if (spriteNumber == 2) {
                    image = idle_left2;
                }
                // image = idle_left1;
                break;
            case "move_up":
                if (spriteNumber == 1) {
                    image = move_up1;
                }
                if (spriteNumber == 2) {
                    image = move_up2;
                }
                break;
            case "move_down":
                if (spriteNumber == 1) {
                    image = move_down1;
                }
                if (spriteNumber == 2) {
                    image = move_down2;
                }
                break;
            case "move_right":
                if (spriteNumber == 1) {
                    image = move_right1;
                }
                if (spriteNumber == 2) {
                    image = move_right2;
                }
                break;
            case "move_left":
                if (spriteNumber == 1) {
                    image = move_left1;
                }
                if (spriteNumber == 2) {
                    image = move_left2;
                }
                break;
        
            default:
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
