package uet.oop.bomberman.entities.mob.enemy;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.mob.Mob;
import uet.oop.bomberman.graphics.Screen;

public abstract class Enemy extends Mob {

    public Enemy(int x, int y, Board board) {
        super(x, y, board);
    }

    @Override
    public abstract void move();

    @Override
    public abstract void update();

    @Override
    public void render(Screen screen) {
        screen.renderEntity((int) x, (int) y - sprite.getSize(), this);
    }
}
