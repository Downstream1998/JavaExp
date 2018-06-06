package sprites;

import java.util.concurrent.CopyOnWriteArrayList;

public class Cannon extends Sprite {
    private static final double RAD_PER_DEG = Math.PI / 180;    // 将角度制化为弧度制
    private static final int ROTATE_FACTOR = 5;                 // 每次旋转的角度
    private static final double MAX_DEGREE = 60 * RAD_PER_DEG;  // 最大角度

    private CopyOnWriteArrayList<Bullet> bullets = new CopyOnWriteArrayList<>();
    private double shootingAngel = 0;   // 炮弹发射的角度（以面板 x 轴负方向为准，向左为负，向右为正）

    public Cannon(int x, int y) {
        super(x, y);
        speed = 5;
    }

    public void shoot() {
        Bullet bullet = new Bullet(coordinate.x + 5, coordinate.y - 20, shootingAngel);
        bullets.add(bullet);

        Thread t = new Thread(bullet);
        t.start();
    }

    public void rotateLeft() {
        if(shootingAngel > -MAX_DEGREE)
            shootingAngel -= ROTATE_FACTOR * RAD_PER_DEG;
    }

    public void rotateRight() {
        if(shootingAngel < MAX_DEGREE)
            shootingAngel += ROTATE_FACTOR * RAD_PER_DEG;
    }

    public CopyOnWriteArrayList<Bullet> getBullets() {
        return bullets;
    }

    @Override
    public void run() {
        // 空方法，无需实现
    }
}
