package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH)
    {
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues()
    {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage()
    {
        try
        {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));

        } catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void update()
    {
        // X-value increases if the player goes right
        // Y-value increases if the player goes down
        // In Java, in the upper-left corner of the screen, X = 0 and Y = 0

        // X-value increases if the player goes right
        // Y-value increases if the player goes down
        // In Java, in the upper-left corner of the screen, X = 0 and Y = 0

        if (keyH.upPressed == true)
        {
            direction = "up";
            y -= speed;
            System.out.println("X, Y: " + x + " , " + y);
        }

        else if (keyH.downPressed == true)
        {
            direction = "down";
            y += speed;
            System.out.println("X, Y: " + x + " , " + y);
        }

        else if (keyH.leftPressed == true)
        {
            direction = "left";
            x -= speed;
            System.out.println("X, Y: " + x + " , " + speed);
        }

        else if (keyH.rightPressed == true)
        {
            direction = "right";
            x += speed;
            System.out.println("X, Y: " + x + " , " + y);
        }

        if (x < -60)
        {
            x = 732;
        }

        if (x > 732)
        {
            x = -60;
        }

        if (y > 584)
        {
            y = -4;
        }

        if (y < -4)
        {
            y = 584;
        }

        spriteCounter++;

        // The update() method gets called 60 times a second, in every frame, it gets called, it increases our sprite counter by one
        // If it hits 10, it changes the sprite frame number from 1 to 2, or 2 to 1
        // This means the player image will change every 10 frames

        if (spriteCounter > 10)
        {
            if (spriteCounter == 1)
            {
                spriteNumber = 2;
            }

            else if (spriteNumber == 2)
            {
                spriteNumber = 1;
            }

            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2)
    {
        BufferedImage image = null;

        switch(direction)
        {
            case "up":
                if (spriteNumber == 1)
                {
                    image = up1;
                }

                else if (spriteNumber == 2)
                {
                    image = up2;
                }


                break;

            case "down":
                if (spriteNumber == 1)
                {
                    image = down1;
                }

                else if (spriteNumber == 2)
                {
                    image = down2;
                }

                break;

            case "right":
                if (spriteNumber == 1)
                {
                    image = right1;
                }

                else if (spriteNumber == 2)
                {
                    image = right2;
                }

                break;

            case "left":
                if (spriteNumber == 1)
                {
                    image = left1;
                }

                else if (spriteNumber == 2)
                {
                    image = left2;
                }

                break;

        }

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

    }
}
