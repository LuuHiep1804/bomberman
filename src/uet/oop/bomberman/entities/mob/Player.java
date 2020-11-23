package uet.oop.bomberman.entities.mob;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.input.Keyboard;
import uet.oop.bomberman.level.Coordinates;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Player extends Mob {

    private final int MAX_ANIM = 7500;

    private double speed = 1.0;
    private int dir = -1;
    private int anim;
    private int bombrange;
    private boolean moving = false;

    protected int timeBetweenPutBombs = 0;

    public Keyboard input;
    public List<Bomb> bombs;

    public Player(int x, int y, Board board) {
        super(x, y, board);
        sprite = Sprite.player_right;
        this.input = board.getInput();
        anim = 0;
        bombrange = 1;
        bombs = board.getBombs();
    }

    public void animate() {
        if (anim < MAX_ANIM) {
            anim++;
        } else {
            anim = 0;
        }
    }

    @Override
    public void move() {
        int xa = 0, ya = 0;
        if (input.up) {
            ya--;
            dir = 0;
        }
        if (input.down) {
            ya++;
            dir = 2;
        }
        if (input.right) {
            xa++;
            dir = 1;
        }
        if (input.left) {
            xa--;
            dir = 3;
        }
        if (!tileCollision(xa, ya) && (xa != 0 || ya != 0)) {
            x += xa * speed;
            y += ya * speed;
            moving = true;
        } else {
            moving = false;
        }
    }

    public void chooseSprite() {
        switch (dir) {
            case 0:
                sprite = Sprite.player_up;
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.player_up_1, Sprite.player_up_2, anim, 20);
                }
                break;
            case 1:
                sprite = Sprite.player_right;
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, anim, 20);
                }
                break;
            case 2:
                sprite = Sprite.player_down;
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.player_down_1, Sprite.player_down_2, anim, 20);
                }
                break;
            case 3:
                sprite = Sprite.player_left;
                if (moving) {
                    sprite = Sprite.movingSprite(Sprite.player_left_1, Sprite.player_left_2, anim, 20);
                }
                break;
            default:
                dir = 1;
        }
    }

    public void placeBomb() {
        if(input.space && timeBetweenPutBombs < 0) {
            int xt = Coordinates.pixelToTile(x + sprite.getSize() / 2);
            int yt = Coordinates.pixelToTile((y + sprite.getSize() / 2) - sprite.getSize());
            Bomb bomb = new Bomb(xt, yt, board,bombrange);
            board.addBomb(bomb);
            timeBetweenPutBombs = 30;
        }
    }

    private void clearBombs() {
        Iterator<Bomb> bs = bombs.iterator();

        Bomb b;
        while(bs.hasNext()) {
            b = bs.next();
            if(b.isRemoved())  {
                bs.remove();
            }
        }

    }


    @Override
    public void update() {
        clearBombs();
        if(timeBetweenPutBombs < -7500) timeBetweenPutBombs = 0;
        else timeBetweenPutBombs--;
        animate();
        move();
        placeBomb();
    }

    @Override
    public void render(Screen screen) {
        chooseSprite();
        screen.renderEntity((int) x, (int) y - sprite.getSize(), this);
    }
}
