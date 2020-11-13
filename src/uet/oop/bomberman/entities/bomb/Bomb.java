package uet.oop.bomberman.entities.bomb;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends Entity {

    private int timeBombLive = 120;
    private int timeAfter = 20;
    private boolean exploded = false;

    protected Board board;
    protected FlameSegment[] flames = null;

    public Bomb(int x, int y, Board board) {
        this.x = x;
        this.y = y;
        this.board = board;
        sprite = Sprite.bomb;
    }

    @Override
    public boolean checkCollision() {
        return true;
    }

    @Override
    public void update() {
        if (timeBombLive > 0) {
            timeBombLive--;
        }else {
            sprite = Sprite.bomb_exploded2;
        }
    }

    public void renderExplosions(Screen screen) {
        for (int i = 0; i < flames.length; i++) {
            flames[i].render(screen);
        }
    }

    @Override
    public void render(Screen screen) {

        if (exploded) {
            sprite = Sprite.bomb_exploded2;
            renderExplosions(screen);
        }
        screen.renderEntity((int) x , (int) y - sprite.getSize(), this);
    }
}
