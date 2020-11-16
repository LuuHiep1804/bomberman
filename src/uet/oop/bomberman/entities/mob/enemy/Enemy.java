package uet.oop.bomberman.entities.mob.enemy;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.mob.Mob;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public abstract class Enemy extends Mob {

    private final int MAX_ANIM = 7500;
    private Random random;
    protected double speed;
    protected int dir = -1;
    protected int anim;
    protected boolean moving;
    protected boolean moving_2;


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

    public void animate() {
        if (anim < MAX_ANIM) {
            anim++;
        } else {
            anim = 0;
        }
    }

    @Override
    public void move() {
        if (!tileCollision(speed, 0) && moving == true) {
            x += speed;
            dir = 0;
        } else {
            moving = false;
        }
        if (!tileCollision(-speed, 0) && moving == false) {
            x -= speed;
            dir = 1;
        } else {
            moving = true;
        }
    }

    @Override
    public void update() {
        animate();
        move();
    }


    @Override
    public void render(Screen screen) {
        screen.renderEntity((int) x, (int) y - sprite.getSize(), this);
    }
}
