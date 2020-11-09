package uet.oop.bomberman.entities.mob;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Screen;

public abstract class Mob extends Entity {
    protected Board board;

    public Mob(int x, int y, Board board) {
        this.x = x;
        this.y = y;
        this.board = board;
    }

    public abstract void move();

    @Override
    public abstract void update();

    @Override
    public abstract void render(Screen screen);
}
