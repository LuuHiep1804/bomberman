package uet.oop.bomberman.entities;

import uet.oop.bomberman.entities.tile.destroyable.Destroyable;
import uet.oop.bomberman.graphics.Screen;

import java.util.LinkedList;

public class LayeredEntity extends Entity{

    protected LinkedList<Entity> _entities = new LinkedList<>();

    public LayeredEntity(int x, int y, Entity ... entities) {
        this.x = x;
        this.y = y;
        for (int i = 0; i < entities.length; i++) {
            _entities.add(entities[i]);

            if (i > 1) {
                if (entities[i] instanceof Destroyable) {
                    ((Destroyable)entities[i]).addBelowSprite(entities[i - 1].getSprite());
                }
            }
        }
    }

    @Override
    public boolean checkCollision(Entity e) {
        return getTopEntity().checkCollision(e);
    }

    @Override
    public void update() {
        clearRemoved();
        getTopEntity().update();
    }

    @Override
    public void render(Screen screen) {
        getTopEntity().render(screen);
    }

    public Entity getTopEntity() {

        return _entities.getLast();
    }

    private void clearRemoved() {
        Entity top  = getTopEntity();

        if(top.isRemoved())  {
            _entities.removeLast();
        }
    }
}
