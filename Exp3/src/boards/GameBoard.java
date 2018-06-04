package boards;

import sprites.Cannon;
import sprites.Direction;
import sprites.Plane;
import sprites.Sprite;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class GameBoard extends JPanel implements Runnable, KeyListener {
    private BufferedImage planeImg;
    private BufferedImage cannonImg;

    private Cannon cannon;
    private final Vector<Plane> planes = new Vector<>();

    private static int PLANE_NUM = 5;
    private static int PLANE_LOWER_BOUND = 200; // 飞机生成区域的 Y 坐标的最大值（面板最底部的位置）

    private static int CANNON_START_POS_X = 10;
    private static int CANNON_START_POS_Y = 400;
//    private int PLANE_HEIGHT;
//    private int PLANE_WIDTH;

    public GameBoard() {
        try {
            planeImg = ImageIO.read(new File("images/plane.png"));
            cannonImg = ImageIO.read(new File("images/cannon.png"));
//            PLANE_HEIGHT = planeImg.getHeight();
//            PLANE_WIDTH = planeImg.getWidth();
        } catch(IOException e) {
            e.printStackTrace();
        }

        cannon = new Cannon(CANNON_START_POS_X, CANNON_START_POS_Y);
        addKeyListener(this);

        synchronized (planes) {
            for(int i = 0; i < PLANE_NUM; i++) {
                int x = (int) (Math.random() * -100);
                Plane p = new Plane(x, (int) (Math.random() * PLANE_LOWER_BOUND));
                Thread t = new Thread(p);
                t.start();
                planes.add(p);
            }
        }
    }

    public Cannon getCannon() {
        return cannon;
    }

    private void drawPlane(Graphics g, int x, int y, Direction direction) {
        switch(direction) {
            case RIGHT:
                g.drawImage(planeImg, x, y, this);
                break;
        }
    }

    private void drawCannon(Graphics g, int x, int y, Direction directon) {
        g.drawImage(cannonImg, x, y, this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        this.drawCannon(g, cannon.getCoordinate().x, cannon.getCoordinate().y, cannon.getDirection());

        for(Plane plane : planes) {
            this.drawPlane(g, plane.getCoordinate().x, plane.getCoordinate().y, plane.getDirection());
        }

        this.validate();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_A) {
            cannon.moveLeft();
            cannon.setDirection(Direction.LEFT);
        } else if(e.getKeyCode() == KeyEvent.VK_D) {
            cannon.moveRight();
            cannon.setDirection(Direction.RIGHT);
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // 空方法，无需实现
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // 空方法，无需实现
    }

    @Override
    public void run() {
        for(;;) {
            try {
                Thread.sleep(Sprite.FRAME_TIME);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (planes) {
                for(int i = 0; i < planes.size(); i++) {
                    if(!planes.get(i).isAlive()) {
                        planes.remove(i);
                        Plane p = new Plane((int) (Math.random() * -100), (int) (Math.random() * PLANE_LOWER_BOUND));
                        Thread t = new Thread(p);
                        t.start();
                        planes.add(p);
                    }
                }
            }

            this.repaint();
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 450);
    }
}
