package uet.oop.bomberman.entities.mob;

import uet.oop.bomberman.DashBoard;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.bomb.FlameSegment;
import uet.oop.bomberman.entities.mob.enemy.Enemy;
import uet.oop.bomberman.game_sound.GameSound;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.input.Keyboard;


import java.util.Iterator;
import java.util.List;

public class Player extends Mob {

    private final int MAX_ANIM = 7500;

    private double speed;
    private int dir = -1;
    private int anim;
    private int bombRange;
    private boolean moving = false;
    private boolean alive = true;

    protected int timeBetweenPutBombs = 0;
    protected int timeDelay = 50;
    protected int bombRate;

    public Keyboard input;
    public List<Bomb> bombs;

    public Player(int x, int y, DashBoard board) {
        super(x, y, board);
        sprite = Sprite.player_right;
        this.input = board.getInput();
        anim = 0;
        bombRange = Game.getBombRange();
        bombs = board.getBombs();
        speed = Game.getSpeed();
        bombRate = Game.getBombRate();
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
        if (alive) {
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

    //------------Bomb-----------------------------------------------------------------------------
    public void placeBomb() {
        if(input.space && timeBetweenPutBombs < 0 && Game.getBombRate() > 0) {
            int xt = (int) (x + sprite.getSize() / 2) / 16;
            int yt = (int) ((y + sprite.getSize() / 2) - sprite.getSize()) / 16;
            Bomb bomb = new Bomb(xt, yt, board, bombRange);
            board.addBomb(bomb);
            timeBetweenPutBombs = 30;
            Game.removeBombRate();
            GameSound.play("newbomb");
        }
    }

    private void clearBombs() {
        Iterator<Bomb> bs = bombs.iterator();

        Bomb b;
        while(bs.hasNext()) {
            b = bs.next();
            if(b.isRemoved())  {
                bs.remove();
                Game.addBombRate();
            }

        }


    }

    @Override
    public void kill() {
        if (!alive) {
            return;
        }
        alive = false;
        GameSound.play("bomber_die");
    }

    @Override
    public boolean checkCollision(Entity e) {
        if (e instanceof FlameSegment) {
            kill();
            return true;
        }
        if (e instanceof Enemy) {
            kill();
            return true;
        }
        return false;
    }

    @Override
    public void update() {
        clearBombs();
        if (!alive) {
            if (timeDelay > 0) {
                timeDelay--;
            }else {
                remove();
            }
        }
        if(timeBetweenPutBombs < -7500) timeBetweenPutBombs = 0;
        else timeBetweenPutBombs--;
        animate();
        move();
        placeBomb();
        speed = Game.getSpeed();
        bombRate = Game.getBombRate();
        bombRange = Game.getBombRange();
    }

    @Override
    public void render(Screen screen) {
        if (alive) {
            chooseSprite();
        }else {
            sprite = Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3, anim, 60);
        }
        screen.renderEntity((int) x, (int) y - sprite.getSize(), this);
    }
}
