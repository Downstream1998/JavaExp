package sprites;

public class Cannon extends Sprite {
    public Cannon(int x, int y) {
        super(x, y);
        speed = 5;
        direction = Direction.RIGHT;
    }

    public void moveLeft() {
        if(coordinate.x - speed >= 0)
            coordinate.x -= speed;
    }

    public void moveRight() {
        if(coordinate.x + speed <= 550)
            coordinate.x += speed;
    }

    @Override
    public void run() {
        // 空方法，无需实现
    }

    @Override
    public String toString() {
        String message = "[sprites.Cannon] { coordinate = " + coordinate + ", direction = " + direction;
        return message;
    }
}
