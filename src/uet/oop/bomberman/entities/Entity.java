package uet.oop.bomberman.entities;

import uet.oop.bomberman.graphics.IRender;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.level.Coordinates;

public abstract class Entity implements IRender {
    protected double x, y;
    protected boolean removed = false;
    protected Sprite sprite;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getTileX() {
        return Coordinates.pixelToTile(x + sprite.getSize() / 2);
    }

    public double getTileY() {
        return Coordinates.pixelToTile(y - sprite.getSize() / 2);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public abstract boolean checkCollision();

    @Override
    public abstract void update();

    @Override
    public abstract void render(Screen screen);

    public void remove() {removed = true;}

    public boolean isRemoved() {
        return removed;
    }
}
