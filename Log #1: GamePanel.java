package main;

import entity.Player;

import javax.swing.JPanel;
import java.awt.*;

// We "extend" JPanel to define the "panel" or window of our game
// Our GamePanel class "implements" Runnable in order to set up our game clock (line 31) down below.

public class GamePanel extends JPanel implements Runnable{

    // Screen Settings

    final int originalTileSize = 16; // 16 x 16 tile sizes
    final int scale = 3; // This scales our tiles to 48 x 48

    public final int tileSize = originalTileSize * scale; // 48 x 48 tile size

    // Our window is essentially a rectangle with "squares/pixels" as its units

    final int maxScreenColumn = 16; // Sets the length, in columns, of our window
    final int maxScreenRow = 12; // Sets the width, in rows, of our window

    final int screenWidth = tileSize * maxScreenColumn; // Defines the width of our window, 48 x 16 or 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // Defines the height of our window, 48 x 12 or 576 pixels

    // FPS

    int FPS = 60; // Defining our frame rates per second here

    KeyHandler keyH = new KeyHandler(); // Created to handle keyboard input as defined in the KeyHandler class
    Thread gameThread; // This constructor sets up a game "clock/thread" for processing frame rates per second
    Player player = new Player(this, keyH);

    // Set player's default position

    int playerX = 100; // We've set the player's X-coordinate as a variable
    int playerY = 100; // We've set the player's Y-coordinate as a variable
    int playerSpeed = 4; // The player will move at 4 pixels in each direction

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // Sets the width and height of our window
        this.setBackground(Color.black); // Sets the background color of our window
        this.setDoubleBuffered(true); // All "painting/drawing" is done off-screen if set to "true". Improves performance
        this.addKeyListener(keyH); // We've now included our key handler, "keyH", from line 26
        this.setFocusable(true); // The GamePanel is now "focused" to receive keyboard input
    }

    public void startGameThread()
    {
        gameThread = new Thread(this); // We instantiate a game thread by passing our GamePanel to the Thread constructor
        gameThread.start(); // This officially starts our game thread and "calls" the "run" function below
    }

    // This "calls" our gameThread

    @Override
    public void run() {

        // Screen gets drawn and redrawn every ~0.0167 seconds
        double drawInterval = 1000000000 / FPS; // Defines FPS. "1,000,000,000" used because we're using nanoseconds for our game clock

        double nextDrawTime = System.nanoTime() + drawInterval; // Roughly 0.0167 seconds is spent on each loop

        while (gameThread != null) // As long as our gameThread exists, it "repeats" the code here
        {

            update(); // We call our update() method here

            repaint(); // Confusing, but this is how you call paintComponent()

            // Our update() and repaint() methods continually update and draw the screen as a result of our game loop

            try {
                double remainingTime = nextDrawTime - System.nanoTime(); // This returns how long until the next draw time
                remainingTime = remainingTime / 1000000; // This converts our remainingTime variable to milliseconds

                if (remainingTime < 0)
                {
                    remainingTime = 0; // This thread won't need to sleep if the allocated time to has been used.
                }

                Thread.sleep( (long) remainingTime); // Our thread won't do anything until the "sleep" time is over
                nextDrawTime += drawInterval; // The next draw time will be ~0.0167 seconds later

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update() // Continually updates our game
    {

        player.update();
    }

    public void paintComponent(Graphics g) // Graphics is a class for drawing objects on the screen
    {
        super.paintComponent(g); // We access our Graphics "g" here as a way to draw on the screen
        Graphics2D g2 = (Graphics2D)g; // We convert our Graphics class above to a Graphics2D class

        g2.setColor(Color.white);

        // Draws a rectangle on the screen and fills it with the specified color

        // Coordinates "playerX" and "playerY" are now variables

        player.draw(g2);


        g2.dispose(); // Clear the screen
    }

}
   
