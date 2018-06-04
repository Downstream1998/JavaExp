package sprites;

public class Plane extends Sprite {
    private boolean alive = true;

    public Plane(int x, int y) {
        super(x, y);
        direction = Direction.RIGHT;
        speed = 3;
    }

    public boolean isAlive() {
        return alive;
    }

    @Override
    public void run() {
        for(;;) {
            for(int i = 0; i < 30; i++) {
                if(coordinate.x < 600)
                    coordinate.x += speed;
                else
                    alive = false;

                try {
                    Thread.sleep(FRAME_TIME);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
