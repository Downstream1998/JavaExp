package boards;

import sprites.Bullet;
import sprites.Cannon;
import sprites.Plane;
import sprites.Sprite;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameBoard extends JPanel implements Runnable{
    static ScoreBoard scoreBoard = new ScoreBoard();

    private BufferedImage planeImg;
    private BufferedImage cannonImg;
    private BufferedImage bulletImg;

    private Cannon cannon;
    private final CopyOnWriteArrayList<Plane> planes = new CopyOnWriteArrayList<>();

    private static final int PLANE_NUM = 5;
    private static final int PLANE_LOWER_BOUND = 200; // 飞机生成区域的 Y 坐标的最大值（面板最底部的位置）

    private static final int CANNON_START_POS_X = 300;
    private static final int CANNON_START_POS_Y = 400;

    public GameBoard() {
        try {
            planeImg = ImageIO.read(new File("images/plane.png"));
            cannonImg = ImageIO.read(new File("images/cannon.png"));
            bulletImg = ImageIO.read(new File("images/bullet.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }

        cannon = new Cannon(CANNON_START_POS_X, CANNON_START_POS_Y);

        for(int i = 0; i < PLANE_NUM; i++) {
            addNewPlane();
        }
    }

    public Cannon getCannon() {
        return cannon;
    }

    private void addNewPlane() {
        Plane p = new Plane((int) (Math.random() * -100), (int) (Math.random() * PLANE_LOWER_BOUND));
        Thread t = new Thread(p);
        t.start();
        planes.add(p);
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
                g.drawImage(bulletImg, (int)bullet.getCoordinate().x, (int)bullet.getCoordinate().y, this);
            else
                bullets.remove(bullet);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        this.drawCannon(g, (int)cannon.getCoordinate().x, (int)cannon.getCoordinate().y);

        for(Plane plane : planes) {
            this.drawPlane(g, (int)plane.getCoordinate().x, (int)plane.getCoordinate().y);
        }

        this.drawBullets(g, cannon.getBullets());

        this.validate();
    }

    private boolean canHit(Bullet bullet, Plane plane) {
        Point2D.Double bulletCoordinate = bullet.getCoordinate();
        Point2D.Double planeCoordinate = plane.getCoordinate();

        if((bulletCoordinate.x >= planeCoordinate.x && bulletCoordinate.x <= planeCoordinate.x + 55) &&
                (bulletCoordinate.y >= planeCoordinate.y && bulletCoordinate.y <= planeCoordinate.y + 55)) {
            plane.setAlive(false);
            bullet.setAlive(false);
            scoreBoard.increaseScore();
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        for(;;) {
            try {
                Thread.sleep(Sprite.FRAME_TIME);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }

            for(Bullet bullet : cannon.getBullets()) {
                for(Plane plane : planes) {
                    if(plane.isAlive() && canHit(bullet, plane)) {
                        planes.remove(plane);
                        addNewPlane();
                    }
                }
            }

            for(int i = 0; i < planes.size(); i++) {
                if(!planes.get(i).isAlive()) {
                    planes.remove(i);
                    addNewPlane();
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
