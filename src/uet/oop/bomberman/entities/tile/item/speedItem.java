package uet.oop.bomberman.entities.tile.item;

import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.mob.Player;
import uet.oop.bomberman.game_sound.GameSound;
import uet.oop.bomberman.graphics.Sprite;

public class speedItem extends Item{
    public speedItem(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public boolean checkCollision(Entity e) {
        if (e instanceof Player) {
            GameSound.play("item");
            Game.addSpeed();
            remove();
        }
        return false;
    }
}
