package uet.oop.bomberman.entities.mob;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.tile.Tile;
import uet.oop.bomberman.graphics.Screen;

public abstract class Mob extends Entity {
    protected Board board;

    public Mob(int x, int y, Board board) {
        this.x = x;
        this.y = y;
        this.board = board;
    }

    public abstract void move();

    public boolean tileCollision(double xa, double ya) {
        for (int i = 0; i < 4; i++) {
            double idX = ((this.x + xa) + i % 2 * 9) / 16;
            double idY = ((this.y + ya) + i / 2 * 10 - 13) / 16;
            Entity t = board.getTile(idX, idY);
            Bomb bomb = board.getBomb(idX, idY);
            if (bomb != null) {
                if (bomb.checkCollision(this)) {
                    return true;
                }
            }else {
                if (t.checkCollision(this)) {
                    return true;
                }
            }
        }
        return false;
    }

    public abstract void kill();

    @Override
    public abstract void update();

    @Override
    public abstract void render(Screen screen);
}
