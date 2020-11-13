package uet.oop.bomberman.entities.mob.enemy;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.mob.Mob;
import uet.oop.bomberman.graphics.Screen;

import java.util.Random;

public abstract class Enemy extends Mob {

    private Random random;


    public Enemy(int x, int y, Board board) {
        super(x, y, board);
        random = new Random();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public void move() {

    }

    @Override
    public void update() {

    }


    @Override
    public void render(Screen screen) {
        screen.renderEntity((int) x, (int) y - sprite.getSize(), this);
    }
}
