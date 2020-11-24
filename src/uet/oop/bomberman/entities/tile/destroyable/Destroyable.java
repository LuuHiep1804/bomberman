package uet.oop.bomberman.entities.tile.destroyable;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.bomb.Flame;
import uet.oop.bomberman.entities.bomb.FlameSegment;
import uet.oop.bomberman.entities.tile.Tile;
import uet.oop.bomberman.graphics.Sprite;

public class Destroyable extends Tile {

    protected int timeDelay = 10;
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
    public void update() {
        if (destroyed) {
            if (timeDelay > 0) {
                timeDelay--;
            }else {
                remove();
            }
        }
    }

    @Override
    public boolean checkCollision(Entity e) {
        if (e instanceof FlameSegment) {
            destroy();
        }
        return true;
    }
}
