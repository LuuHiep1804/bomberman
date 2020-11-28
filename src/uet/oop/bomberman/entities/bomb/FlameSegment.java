package uet.oop.bomberman.entities.bomb;

import uet.oop.bomberman.DashBoard;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.mob.Player;
import uet.oop.bomberman.entities.mob.enemy.Enemy;
import uet.oop.bomberman.graphics.Screen;

public class FlameSegment extends Entity {

    protected DashBoard board;
    protected int dir;
    private int radius;
    protected Flame[] flames;

    public FlameSegment(int x, int y, DashBoard board, int dir, int radius) {
        this.x = x;
        this.y = y;
        this.board = board;
        this.radius = radius;
        this.dir = dir;
        addExplosions();
    }

    public void addExplosions() {
        flames = new Flame[flameLength()];
        boolean last = false;
        int xa = (int)x;
        int ya = (int)y;
        for (int i = 0; i < flames.length; i++) {
            if (i == flames.length - 1) {
                last = true;
            }
            switch (dir) {
                case 0:
                    ya--;
                    break;
                case 1:
                    xa++;
                    break;
                case 2:
                    ya++;
                    break;
                case 3:
                    xa--;
                    break;
            }
            flames[i] = new Flame(xa, ya, board, dir, last);
        }
    }

    public int flameLength() {
        int radius = 0;
        int x = (int) this.x;
        int y = (int) this.y;
        while (radius < this.radius) {
            if (dir == 0) y--;
            if (dir == 1) x++;
            if (dir == 2) y++;
            if (dir == 3) x--;
            Entity e = board.getTile(x, y);
            Entity m = board.getMob(x, y, null);
            if (e.checkCollision(this) || m != null && m.checkCollision(this)) {
                break;
            }
            radius++;
        }
        return radius;
    }

    public Flame flameSegmentAt(int x, int y) {
        for (int i = 0; i < flames.length; i++) {
            if (flames[i].getX() == x && flames[i].getY() == y) {
                return flames[i];
            }
        }
        return null;
    }

    @Override
    public boolean checkCollision(Entity e) {
        if (e instanceof Player) {
            ((Player) e).kill();
        }
        if (e instanceof Enemy) {
            ((Enemy) e).kill();
        }
        return false;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Screen screen) {
        for (int i = 0; i < flames.length; i++) {
            flames[i].render(screen);
        }
    }
}
