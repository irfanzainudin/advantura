package advantura;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = maxWorldCol * tileSize;
    public final int worldHeight = maxWorldRow * tileSize;

    // FPS = frames per second
    int fps = 60;

    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();
    TileManager tileManager = new TileManager(this);
    public Player player = new Player(this, keyHandler);

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
        double delta = 0;
        double lastTime = System.nanoTime();
        double currentTime;
        long timer = 0;
        int drawCount = 0;
        // double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread != null) {
            // System.out.println("Game is running...");
            
            // APPROACH 1
            // Update information (ie. player positions)
            // update();
            // Draw the screen with the updated info
            // repaint();

            // try {
            //     double remainingTime  = nextDrawTime - System.nanoTime();
            //     remainingTime = remainingTime / 1000000;

            //     if (remainingTime < 0) {
            //         remainingTime = 0;
            //     }

            //     Thread.sleep((long) remainingTime);

            //     nextDrawTime += drawInterval;
            // } catch (InterruptedException e) {
            //     e.printStackTrace();
            // }

            // APPROACH 2
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                player.update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g; // cast g to Graphics2d which has more functions

        // NOTE: Draw tiles first unless you want the player
        // ... to be behind the tiles. Do you?
        tileManager.draw(g2);
        
        player.draw(g2);

        g2.dispose();
    }
}
