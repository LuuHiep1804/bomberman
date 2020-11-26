package uet.oop.bomberman.entities.tile;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Tile extends Entity {

    public Tile(int x, int y, Sprite sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    @Override
    public boolean checkCollision(Entity e) {
        return true;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Screen screen) {
        screen.renderEntity((int) (x * 16),(int) (y * 16), this);
    }
}
