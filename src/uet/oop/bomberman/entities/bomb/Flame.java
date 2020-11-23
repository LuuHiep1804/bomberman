package uet.oop.bomberman.entities.bomb;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

public class Flame extends Entity {

    protected Board board;
    protected boolean last = false;

    public Flame (int x, int y, Board board, int dir, boolean last){
        this.x = x;
        this.y = y;
        this.board = board;
        this.last = last;
        switch (dir) {
            case 0:
                if(last == false) {
                    sprite = Sprite.explosion_vertical2;
                } else {
                    sprite = Sprite.explosion_vertical_top_last2;
                }
                break;
            case 1:
                if(last == false) {
                    sprite = Sprite.explosion_horizontal2;
                } else {
                    sprite = Sprite.explosion_horizontal_right_last2;
                }
                break;
            case 2:
                if(last == false) {
                    sprite = Sprite.explosion_vertical2;
                } else {
                    sprite = Sprite.explosion_vertical_down_last2;
                }
                break;
            case 3:
                if(last == false) {
                    sprite = Sprite.explosion_horizontal2;
                } else {
                    sprite = Sprite.explosion_horizontal_left_last2;
                }
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
        screen.renderEntity((int) x << 4, (int) y << 4, this);
    }
}
