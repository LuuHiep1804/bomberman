package uet.oop.bomberman.entities.tile;

import uet.oop.bomberman.DashBoard;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.mob.Player;
import uet.oop.bomberman.graphics.Sprite;

public class PortalTile extends Tile {
    protected DashBoard board;

    public PortalTile(int x, int y, DashBoard board, Sprite sprite) {
        super(x, y, sprite);
        this.board = board;
    }


    @Override
    public boolean checkCollision(Entity e) {
        if (e instanceof Player) {
            if (board.listMob.size() > 1) {
                return true;
            }

            if (board.listMob.size() == 1) {
                board.updateLevel();
            }
            return false;
        }
        return false;
    }
}
