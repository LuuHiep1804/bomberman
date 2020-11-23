package uet.oop.bomberman.entities.bomb;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Screen;

public class FlameSegment extends Entity {

    protected Board board;
    protected int dir;
    private int radius;
    protected Flame flames;

    public FlameSegment(int x, int y, Board board, int dir, int radius) {
        this.x = x;
        this.y = y;
        this.board = board;
        this.radius = radius;
        this.dir = dir;
        addExplosions();
    }

    private void addExplosions() {
        int xa = (int)x;
        int ya = (int)y;
        switch (dir) {
            case 0:
                ya--;
                break;
            case 1:
                xa++;
                break;
            case 2:
                ya++;
                break;
            case 3:
                xa--;
                break;
        }
        flames = new Flame(xa, ya, board, dir);
    }

    @Override
    public boolean checkCollision() {
        return true;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Screen screen) {
        flames.render(screen);
    }
}
