package uet.oop.bomberman.entities.mob;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.input.Keyboard;

import java.awt.*;

public class Player extends Mob {

    private final int MAX_ANIM = 7500;

    private double speed = 1.0;
    private int dir = -1;
    private int anim;
    private boolean moving = false;

    public Keyboard input;

    public Player(int x, int y, Board board) {
        super(x, y, board);
        sprite = Sprite.player_right;
        this.input = board.getInput();
        anim = 0;
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
        if (input.space) {
            Bomb bomb = new Bomb((int) x, (int) y, board);
            board.addBomb(bomb);
        }
    }


    @Override
    public void update() {
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
