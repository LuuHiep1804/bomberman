package uet.oop.bomberman.entities.mob.enemy;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Enemy{

    private int timeDelay =  50;

    public Oneal(int x, int y, Board board) {
        super(x, y, board);
        speed = 0.15;
        moving = false;
        sprite = Sprite.oneal_left1;
    }

    @Override
    public void move() {
        super.move();
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
            sprite = Sprite.oneal_dead;
            sprite = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, anim, 60);
            super.render(screen);
        }
    }

    public void chooseSprite() {
        switch (dir) {
            case 0:
                sprite = Sprite.oneal_left1;
                sprite = Sprite.movingSprite(Sprite.oneal_left2, Sprite.oneal_left3, anim, 20);
                break;
            case 1:
                sprite = Sprite.oneal_right1;
                sprite = Sprite.movingSprite(Sprite.oneal_right2, Sprite.oneal_right3, anim, 20);
                break;
            default:
                sprite = Sprite.oneal_right1;
                sprite = Sprite.movingSprite(Sprite.oneal_right2, Sprite.oneal_right3, anim, 20);
                break;
        }
    }
}
