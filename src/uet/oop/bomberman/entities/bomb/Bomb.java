package uet.oop.bomberman.entities.bomb;

import uet.oop.bomberman.DashBoard;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.mob.Mob;
import uet.oop.bomberman.entities.mob.Player;
import uet.oop.bomberman.game_sound.GameSound;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends Entity {

    private int range;
    private int anim;
    private int bombCounter;
    private int timeFlame;
    private final int MAX_ANIM = 7500;
    protected DashBoard board;
    protected FlameSegment[] flames;
    protected boolean explode = false;
    protected boolean allowedToMove = false;

    public Bomb(int x, int y, DashBoard board, int range) {
        this.x = x;
        this.y = y;
        this.board = board;
        this.range = range;
        bombCounter = 120;
        timeFlame = 30;
        anim = 0;
        sprite = Sprite.bomb;
    }

    public void animate(){
        if (anim < MAX_ANIM){
            anim++;
        }else {
            anim = 0;
        }
    }

    public void flame() {
        explode = true;
        allowedToMove = false;
        flames = new FlameSegment[4];
        Mob m = board.getMob((int)x,(int) y, null);
        if (m != null) {
            m.kill();
        }
        for (int i = 0; i < flames.length; i++) {
            flames[i] = new FlameSegment((int)x, (int) y, board, i, range);
        }
        GameSound.play("bomb_bang");
    }

    public Flame flameAt(int x, int y) {
        if (!explode) {
            return null;
        }
        for (int i = 0; i < flames.length; i++) {
            if (flames[i] == null) {
                return null;
            }else {
                Flame f = flames[i].flameSegmentAt(x, y);
                if (f != null) {
                    return f;
                }
            }
        }
        return null;
    }

    public void renderFlame(Screen screen) {
        for (int i = 0; i < flames.length; i++) {
            flames[i].render(screen);
        }
    }


    @Override
    public boolean checkCollision(Entity e) {
        // đi ra sau khi đặt bomb
        if (e instanceof Player) {
            int xa = (int) e.getX() - (int) getX() * 16;
            int ya = (int) e.getY() - (int) getY() * 16;
            if (!(xa >= -10 && xa < 16 && ya >= 1 && ya <= 28)) {
                allowedToMove = true;
            }
            return allowedToMove;
        }

        return true;
    }

    @Override
    public void update() {
        if (bombCounter > 0) {
            bombCounter--;
        }else {
            if (!explode) {
                flame();
            }
            if (timeFlame > 0) {
                timeFlame--;
            }else {
                remove();
            }
        }
        animate();
    }

    @Override
    public void render(Screen screen) {
        if (!explode) {
            sprite = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, anim, 60);
        }else{
            sprite = Sprite.bomb_exploded2;
            renderFlame(screen);
        }
        screen.renderEntity((int) x << 4, (int) y << 4, this);
    }
}
