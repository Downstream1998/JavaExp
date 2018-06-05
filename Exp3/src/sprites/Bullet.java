package sprites;

public class Bullet extends Sprite {
    boolean alive = true;

    public Bullet(int x, int y) {
        super(x, y);
        speed = 5;
    }

    public boolean isAlive() {
        return alive;
    }

    @Override
    public void run() {
        for(;;) {
            try {
                Thread.sleep(FRAME_TIME);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }

            coordinate.y -= speed;

            if(coordinate.y < 0) {
                alive = false;
                break;
            }
        }
    }
}
