// Following a Youtube tutorial: How to Make a 2D Game in Java by RyiSnow on Youtube
// Link: https://www.youtube.com/playlist?list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq
package advantura;

import javax.swing.JFrame;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        JFrame window = new JFrame();
        GamePanel gp = new GamePanel();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Advantura");
        
        window.add(gp);

        window.pack();
        
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gp.startGameThread();
    }
}
