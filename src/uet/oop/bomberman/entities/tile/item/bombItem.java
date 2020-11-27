package uet.oop.bomberman.entities.tile.item;

import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.mob.Player;
import uet.oop.bomberman.graphics.Sprite;

public class bombItem extends Item{
    public bombItem(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }
    @Override
    public boolean checkCollision(Entity e) {
        if (e instanceof Player) {
            Game.addBombRate();
            //System.out.println(Game.getBombRate());
            remove();
        }
        return false;
    }
}
