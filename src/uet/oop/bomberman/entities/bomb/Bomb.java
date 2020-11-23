package uet.oop.bomberman.entities.bomb;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends Entity {

    private int range;
    private int anim;
    private int bombCounter;
    private int timeFlame;
    private final int MAX_ANIM = 7500;
    protected Board board;
    protected FlameSegment[] flames;
    protected boolean explode = false;

    public Bomb(int x, int y, Board board, int range) {
        this.x = x;
        this.y = y;
        this.board = board;
        this.range = range;
        bombCounter = 120;
        timeFlame = 20;
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
        flames = new FlameSegment[4];
        for (int i = 0; i < flames.length; i++) {
            flames[i] = new FlameSegment((int)x, (int) y, board, i, range);
        }
    }

    public void renderFlame(Screen screen) {
        for (int i = 0; i < flames.length; i++) {
            flames[i].render(screen);
        }
    }

    @Override
    public boolean checkCollision() {
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
