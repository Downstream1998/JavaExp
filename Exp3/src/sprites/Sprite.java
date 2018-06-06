package sprites;

import java.awt.*;
import java.awt.geom.Point2D;

public abstract class Sprite implements Runnable {
    public static int FRAME_TIME = 1000 / 60;
    protected Point2D.Double coordinate; // 对象坐标
    protected int speed;    // 速度

    public Sprite(double x, double y) {
        coordinate = new Point2D.Double(x, y);
    }

    public Point2D.Double getCoordinate() {
        return coordinate;
    }
}
