package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener
{

    public boolean upPressed, downPressed, leftPressed, rightPressed; // Creates directions

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode(); // Returns the integer keyCode related to the key in this event

        if (code == KeyEvent.VK_W)
        {
            upPressed = true; // Go up if key W pressed
        }

        if (code == KeyEvent.VK_A)
        {
            leftPressed = true; // Gow left if key A pressed
        }

        if (code == KeyEvent.VK_S)
        {
            downPressed = true; // Go down if key S pressed
        }

        if (code == KeyEvent.VK_D)
        {
            rightPressed = true; // Go right if key D pressed
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W)
        {
            upPressed = false; // Stop if key W is released
        }

        if (code == KeyEvent.VK_A)
        {
            leftPressed = false; // Stop if key A is released
        }

        if (code == KeyEvent.VK_S)
        {
            downPressed = false; // Stop if key S is released
        }

        if (code == KeyEvent.VK_D)
        {
            rightPressed = false; // Stop if key D is released
        }

    }
}
