package uet.oop.bomberman.entities.mob.enemy;

import com.sun.xml.internal.ws.policy.sourcemodel.PolicyModelGenerator;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Balloom extends Enemy{

    private Random ran = new Random();

    public Balloom(int x, int y, Board board) {
        super(x, y, board);
        speed = 0.5;
        moving = true;
        sprite = Sprite.balloom_left1;
    }

    @Override
    public void move() {
        if (!tileCollision(speed, 0) && dir == 0) {
            x += speed;
        } else if (!tileCollision(-speed, 0)  && dir == 1) {
            x -= speed;
        } else if (!tileCollision(0, speed) && dir == 2) {
            y += speed;
        } else if (!tileCollision(0, -speed) && dir == 3 ) {
            y -= speed;
        } else {
            dir = ran.nextInt(4);
        }
    }

    @Override
    public void kill() {

    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void render(Screen screen) {
        chooseSprite();
        super.render(screen);
    }

    public void chooseSprite() {
        switch (dir) {
            case 0:
                sprite = Sprite.balloom_left1;
                sprite = Sprite.movingSprite(Sprite.balloom_left2, Sprite.balloom_left3, anim, 20);
                break;
            case 1:
                sprite = Sprite.balloom_right1;
                sprite = Sprite.movingSprite(Sprite.balloom_right2, Sprite.balloom_right3, anim, 20);
                break;
            default:
                sprite = Sprite.balloom_right1;
                sprite = Sprite.movingSprite(Sprite.balloom_right2, Sprite.balloom_right3, anim, 20);
                break;
        }
    }

    @Override
    public boolean checkCollision(Entity e) {
        return true;
    }
}
