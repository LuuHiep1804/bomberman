package uet.oop.bomberman.entities.tile.item;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class flameItem extends Item{
    public flameItem(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }
    @Override
    public boolean checkCollision(Entity e) {
        return false;
    }
}
