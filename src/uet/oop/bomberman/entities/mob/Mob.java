package uet.oop.bomberman.entities.mob;

import uet.oop.bomberman.DashBoard;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.bomb.Flame;
import uet.oop.bomberman.graphics.Screen;

public abstract class Mob extends Entity {
    protected DashBoard board;

    public Mob(int x, int y, DashBoard board) {
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
            Flame flame = board.getFlameAt((int)idX, (int)idY);
            Mob mob = board.getMob((int) idX, (int) idY, null);
            if (bomb != null) {
                return bomb.checkCollision(this);
            }else if (flame != null) {
                return flame.checkCollision(this);
            }else if (mob != null) {
                if (mob.checkCollision(this)) {
                    return true;
                }
            }
            else {
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
