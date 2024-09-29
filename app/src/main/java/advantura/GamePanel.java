package advantura;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    final int tileSize = originalTileSize * scale; // 48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // FPS = frames per second
    int fps = 60;

    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();

    // Set player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread()
    {
        gameThread = new Thread(this);
        run();
    }

    @Override
    public void run()
    {
        // Main (?) game loop
        //      ________<____
        //      ||           |
        //      \/           |
        //   update()        ^
        //      ||           |
        //      \/           |
        //   repaint()       |
        //      |            ^
        //      |_______>____|
        //
        double drawInterval = 1000000000 / fps;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread != null) {
            // System.out.println("Game is running...");
            // UPDATE: update information (ie. player positions)
            update();
            // DRAW: draw the screen with the updated info
            repaint();

            try {
                double remainingTime  = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update()
    {
        if (keyHandler.upPressed == true) {
            playerY -= playerSpeed;
        }
        
        if (keyHandler.downPressed == true) {
            playerY += playerSpeed;
        }
        
        if (keyHandler.rightPressed == true) {
            playerX += playerSpeed;
        }

        if (keyHandler.leftPressed == true) {
            playerX -= playerSpeed;
        }
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g; // cast g to Graphics2d which has more functions

        g2.setColor(Color.WHITE);

        g2.fillRect(playerX, playerY, tileSize, tileSize);

        g2.dispose();
    }
}
