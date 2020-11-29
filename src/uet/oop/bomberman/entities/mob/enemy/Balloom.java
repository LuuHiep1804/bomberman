package uet.oop.bomberman.entities.mob.enemy;

import uet.oop.bomberman.DashBoard;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Balloom extends Enemy{

    private Random ran = new Random();
    private int timeDelay = 50;

    public Balloom(int x, int y, DashBoard board) {
        super(x, y, board);
        speed = 0.5;
        moving = true;
        sprite = Sprite.balloom_left1;
    }

    @Override
    public void move() {
        if (alive) {
            if (!tileCollision(6, 0) && dir == 0) {
                x += speed;
            } else if (!tileCollision(-speed, 0) && dir == 1) {
                x -= speed;
            } else if (!tileCollision(0, 3) && dir == 2) {
                y += speed;
            } else if (!tileCollision(0, -4) && dir == 3) {
                y -= speed;
            } else {
                dir = ran.nextInt(4);
            }
        }
    }

    @Override
    public void update() {
        if (!alive) {
            if (timeDelay > 0) {
                timeDelay--;
            }else {
                remove();
            }
        }
        super.update();
    }

    @Override
    public void render(Screen screen) {
        if (alive) {
            chooseSprite();
            super.render(screen);
        }else {
            sprite = Sprite.balloom_dead;
            sprite = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, anim, 60);
            super.render(screen);
        }

    }

    public void chooseSprite() {
        switch (dir) {
            case 0:
                sprite = Sprite.balloom_left1;
                sprite = Sprite.movingSprite(Sprite.balloom_left2, Sprite.balloom_left3, anim, 20);
                break;
            case 1:
                sprite = Sprite.balloom_right1;
                sprite = Sprite.movingSprite(Sprite.balloom_right2, Sprite.balloom_right3, anim, 20);
                break;
            default:
                sprite = Sprite.balloom_right1;
                sprite = Sprite.movingSprite(Sprite.balloom_right2, Sprite.balloom_right3, anim, 20);
                break;
        }
    }
}
