package uet.oop.bomberman.entities.mob.enemy;

import uet.oop.bomberman.DashBoard;
import uet.oop.bomberman.entities.mob.Player;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Oneal extends Enemy {

    private int timeDelay = 50;
    Player player = (Player) board.getPlayer();
    private boolean movingAi;
    private double speedAi;
    private Random ran = new Random();

    public Oneal(int x, int y, DashBoard board) {
        super(x, y, board);
        speed = 0.5;
        speedAi = 1.0;
        movingAi = false;
        sprite = Sprite.oneal_left1;
    }

    @Override
    public void move() {
        if (alive) {
            if (!tileCollision(0, -speed) && x - 9 < player.getX() && x + 12 > player.getX() && y > player.getY()) {
                movingAi = true;
                dir = 3;
            } else if (!tileCollision(0, speed) && x - 9 < player.getX() && x + 12 > player.getX() && y < player.getY()) {
                movingAi = true;
                dir = 2;
            } else if (!tileCollision(speed, 0) && y - 16 < player.getY() && y + 15 > player.getY() && x < player.getX()) {
                movingAi = true;
                dir = 1;
            } else if (!tileCollision(-speed, 0) && y - 16 < player.getY() && y + 15 > player.getY() && x > player.getX()) {
                movingAi = true;
                dir = 0;
            } else {
                movingAi = false;
            }
            if (dir == 0 && movingAi) {
                x -= speedAi;
            } else if (dir == 1 && movingAi) {
                x += speedAi;
            } else if (dir == 2 && movingAi) {
                y += speedAi;
            } else if (dir == 3 && movingAi) {
                y -= speedAi;
            } else {
                if (!tileCollision(6, 0) && dir == 0) {
                    x += speed;
                } else if (!tileCollision(-speed, 0) && dir == 1) {
                    x -= speed;
                } else if (!tileCollision(0, 3) && dir == 2) {
                    y += speed;
                } else if (!tileCollision(0, -4) && dir == 3) {
                    y -= speed;
                } else {
                    dir = ran.nextInt(4);
                }
            }
        }
    }

    @Override
    public void update() {
        if (!alive) {
            if (timeDelay > 0) {
                timeDelay--;
            } else {
                remove();
            }
        }
        super.update();
    }

    @Override
    public void render(Screen screen) {
        if (alive) {
            chooseSprite();
            super.render(screen);
        } else {
            sprite = Sprite.oneal_dead;
            sprite = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, anim, 60);
            super.render(screen);
        }
    }

    public void chooseSprite() {
        switch (dir) {
            case 0:
                sprite = Sprite.oneal_left1;
                sprite = Sprite.movingSprite(Sprite.oneal_left2, Sprite.oneal_left3, anim, 20);
                break;
            case 1:
                sprite = Sprite.oneal_right1;
                sprite = Sprite.movingSprite(Sprite.oneal_right2, Sprite.oneal_right3, anim, 20);
                break;
            default:
                sprite = Sprite.oneal_right1;
                sprite = Sprite.movingSprite(Sprite.oneal_right2, Sprite.oneal_right3, anim, 20);
                break;
        }
    }
}
