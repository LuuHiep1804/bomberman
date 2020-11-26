package uet.oop.bomberman.entities.tile.destroyable;

import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

public class BrickTile extends Destroyable{

    public BrickTile(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void render(Screen screen) {
        int x = (int)(this.x * 16);
        int y = (int)(this.y * 16);

        if (destroyed) {
            sprite = Sprite.brick_exploded2;
            screen.renderEntityWithBeLowSprite(x, y, this, beLowSprite);
        }else {
            screen.renderEntity(x, y, this);
        }
    }
}
