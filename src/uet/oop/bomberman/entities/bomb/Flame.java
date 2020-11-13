package uet.oop.bomberman.entities.bomb;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

public class Flame extends Entity {

    protected Board board;

    public Flame (int x, int y,int dir, Board board){
        this.x = x;
        this.y = y;
        this.board = board;
        switch (dir) {
            case 0:
                sprite = Sprite.explosion_vertical2;
                break;
            case 1:
                sprite = Sprite.explosion_horizontal2;
                break;
            case 2:
                sprite = Sprite.explosion_vertical2;
                break;
            case 3:
                sprite = Sprite.explosion_horizontal2;
                break;
        }
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
        screen.renderEntity((int) x, (int) y, this);
    }
}