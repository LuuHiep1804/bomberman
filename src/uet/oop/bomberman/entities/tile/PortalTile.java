package uet.oop.bomberman.entities.tile;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class PortalTile extends Tile{
    protected Board board;
    public PortalTile(int x, int y,Board board, Sprite sprite) {
        super(x, y, sprite);
        this.board = board;
    }

    @Override
    public void onCollisionEnter(Entity collidingObj) {

    }
}
