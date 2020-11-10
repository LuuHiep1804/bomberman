package uet.oop.bomberman.entities.tile.destroyable;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.tile.Tile;
import uet.oop.bomberman.graphics.Sprite;

public class Destroyable extends Tile {

    protected boolean destroyed = false;
    protected Sprite beLowSprite = Sprite.grass;

    public Destroyable(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    public void destroy() {
        destroyed = true;
    }

    public void addBelowSprite(Sprite sprite) {
        beLowSprite = sprite;
    }

    @Override
    public void onCollisionEnter(Entity collidingObj) {

    }
}
