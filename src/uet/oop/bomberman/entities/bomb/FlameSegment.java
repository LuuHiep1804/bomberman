package uet.oop.bomberman.entities.bomb;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Screen;

public class FlameSegment extends Entity {

    protected Board board;
    protected int dir;
    protected Flame[] flames;

    public FlameSegment(int x, int y, int dir, Board board) {
        this.x = x;
        this.y = y;
        this.board = board;
        this.dir = dir;
        flames = new Flame[1];
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
        flames[0].render(screen);
    }
}
