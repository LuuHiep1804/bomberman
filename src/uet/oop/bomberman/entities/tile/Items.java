package uet.oop.bomberman.entities.tile;

import uet.oop.bomberman.graphics.Sprite;

public class Items extends Tile{
    public Items(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public boolean checkCollision() {
        return false;
    }
}
