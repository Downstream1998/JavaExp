package boards;

import sprites.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameBoard extends JPanel implements Runnable, KeyListener {
    private BufferedImage planeImg;
    private BufferedImage cannonImg;
    private BufferedImage bulletImg;

    private Cannon cannon;
    private final Vector<Plane> planes = new Vector<>();

    private static final int PLANE_NUM = 5;
    private static int PLANE_LOWER_BOUND = 200; // 飞机生成区域的 Y 坐标的最大值（面板最底部的位置）

    private static final int CANNON_START_POS_X = 10;
    private static final int CANNON_START_POS_Y = 400;

    private static final int BULLET_NUM = 10;   // 同屏子弹的最大数量
//    private int PLANE_HEIGHT;
//    private int PLANE_WIDTH;

    public GameBoard() {
        try {
            planeImg = ImageIO.read(new File("images/plane.png"));
            cannonImg = ImageIO.read(new File("images/cannon.png"));
            bulletImg = ImageIO.read(new File("images/bullet.png"));
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

    private void drawPlane(Graphics g, int x, int y) {
        g.drawImage(planeImg, x, y, this);
    }

    private void drawCannon(Graphics g, int x, int y) {
        g.drawImage(cannonImg, x, y, this);
    }

    private void drawBullets(Graphics g, CopyOnWriteArrayList<Bullet> bullets) {
        for(Bullet bullet : bullets) {
            if(bullet.isAlive())
                g.drawImage(bulletImg, bullet.getCoordinate().x, bullet.getCoordinate().y, this);
            else
                bullets.remove(bullet);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        this.drawCannon(g, cannon.getCoordinate().x, cannon.getCoordinate().y);

        for(Plane plane : planes) {
            this.drawPlane(g, plane.getCoordinate().x, plane.getCoordinate().y);
        }

        this.drawBullets(g, cannon.getBullets());

        this.validate();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                cannon.moveLeft();
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                cannon.moveRight();
                break;
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                if(cannon.getBullets().size() < BULLET_NUM)
                    cannon.shoot();
                break;
        }
//        if(e.getKeyCode() == KeyEvent.VK_A) {
//            cannon.moveLeft();
//            cannon.setDirection(Direction.LEFT);
//        } else if(e.getKeyCode() == KeyEvent.VK_D) {
//            cannon.moveRight();
//            cannon.setDirection(Direction.RIGHT);
//        } else if(e.getKeyCode() == KeyEvent.VK_A)
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
