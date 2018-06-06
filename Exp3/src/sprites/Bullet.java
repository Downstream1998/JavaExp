package sprites;

public class Bullet extends Sprite {
    private boolean alive = true;
    private double xAxisSpeed;
    private double yAxisSpeed;

    public Bullet(double x, double y, double shootingAngle) {
        super(x, y);
        speed = 5;

        xAxisSpeed = speed * Math.sin(shootingAngle);
        yAxisSpeed = speed * Math.cos(shootingAngle);
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    @Override
    public void run() {
        for(;;) {
            try {
                Thread.sleep(FRAME_TIME);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }

            coordinate.x += xAxisSpeed;
            coordinate.y -= yAxisSpeed;

            if(coordinate.y < 0 || coordinate.x < 0 || coordinate.x > 600) {
                alive = false;
                break;
            }
        }
    }
}
