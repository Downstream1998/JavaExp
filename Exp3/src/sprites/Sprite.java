package sprites;

import java.awt.*;

public abstract class Sprite implements Runnable {
    public static int FRAME_TIME = 1000 / 60;
    protected Point coordinate; // 对象坐标
    protected int speed;    // 速度

    public Sprite(int x, int y) {
        coordinate = new Point(x, y);
    }

    public Point getCoordinate() {
        return coordinate;
    }
}
