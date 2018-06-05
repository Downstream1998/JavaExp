package sprites;

import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

public class Cannon extends Sprite {
    private CopyOnWriteArrayList<Bullet> bullets = new CopyOnWriteArrayList<>();

    public Cannon(int x, int y) {
        super(x, y);
        speed = 5;
    }

    public void shoot() {
        Bullet bullet = new Bullet(coordinate.x + 5, coordinate.y - 20);
        bullets.add(bullet);

        Thread t = new Thread(bullet);
        t.start();
    }

    public void moveLeft() {
        if(coordinate.x - speed >= 0)
            coordinate.x -= speed;
    }

    public void moveRight() {
        if(coordinate.x + speed <= 550)
            coordinate.x += speed;
    }

    public CopyOnWriteArrayList<Bullet> getBullets() {
        return bullets;
    }

    @Override
    public void run() {
        // 空方法，无需实现
    }

    @Override
    public String toString() {
        String message = "[sprites.Cannon] { coordinate = " + coordinate;
        return message;
    }
}
