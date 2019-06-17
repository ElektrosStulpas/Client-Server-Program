package Pacman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

public class Pacman extends JFrame {
    private Robot robot;

    {
        try {
           robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public Pacman() {
        initUI();
    }

    private void initUI() {
        add(new Board());

        setTitle("Pacman");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(380, 425);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Pacman ex = new Pacman();
            ex.setVisible(true);
        });
    }

    public void startGame() {
        robot.keyPress(KeyEvent.VK_S);
        robot.keyRelease(KeyEvent.VK_S);
    }

    public void moveUp() {
        robot.keyPress(KeyEvent.VK_UP);
        robot.keyRelease(KeyEvent.VK_UP);
    }

    public void moveDown() {
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
    }

    public void moveRight() {
        robot.keyPress(KeyEvent.VK_RIGHT);
        robot.keyRelease(KeyEvent.VK_RIGHT);
    }

    public void moveLeft() {
        robot.keyPress(KeyEvent.VK_LEFT);
        robot.keyRelease(KeyEvent.VK_LEFT);
    }

    public void circleStructure(int firstKeyEvent, int secondKeyEvent, int thirdKeyEvent, int fourthKeyEvent){
        try {
            robot.keyPress(firstKeyEvent);
            robot.keyRelease(firstKeyEvent);

            TimeUnit.MILLISECONDS.sleep(200);

            robot.keyPress(secondKeyEvent);
            robot.keyRelease(secondKeyEvent);

            TimeUnit.MILLISECONDS.sleep(200);

            robot.keyPress(thirdKeyEvent);
            robot.keyRelease(thirdKeyEvent);

            TimeUnit.MILLISECONDS.sleep(200);

            robot.keyPress(fourthKeyEvent);
            robot.keyRelease(fourthKeyEvent);
        }catch (InterruptedException e){
            System.out.println("sleep interrupted");
        }
    }

    public void pause() {
        robot.keyPress(KeyEvent.VK_PAUSE);
        robot.keyRelease(KeyEvent.VK_PAUSE);
    }

    public void escape() {
        robot.keyPress(KeyEvent.VK_ESCAPE);
        robot.keyRelease(KeyEvent.VK_ESCAPE);
    }
}
