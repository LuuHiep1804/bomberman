package uet.oop.bomberman.entities.mob.enemy;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

public class Minvo extends Enemy {
    public Minvo(int x, int y, Board board) {
        super(x, y, board);
        speed = 0.2;
        sprite = Sprite.minvo_left1;
    }

    @Override
    public void move() {
        super.move();
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void render(Screen screen) {
        chooseSprite();
        super.render(screen);
    }

    @Override
    public boolean checkCollision() {
        return true;
    }

    public void chooseSprite() {
        switch (dir) {
            case 0:
                sprite = Sprite.minvo_left1;
                sprite = Sprite.movingSprite(Sprite.minvo_left2, Sprite.minvo_left3, anim, 20);
                break;
            case 1:
                sprite = Sprite.minvo_right1;
                sprite = Sprite.movingSprite(Sprite.minvo_right2, Sprite.minvo_right3, anim, 20);
                break;
            default:
                dir = 1;
        }
    }
}
