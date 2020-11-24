package uet.oop.bomberman.entities.tile.item;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class bombItem extends Item{
    public bombItem(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }
    @Override
    public boolean checkCollision(Entity e) {
        return false;
    }
}
