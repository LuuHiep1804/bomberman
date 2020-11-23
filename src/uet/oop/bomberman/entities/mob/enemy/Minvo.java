package uet.oop.bomberman.entities.mob.enemy;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.mob.Player;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

public class Minvo extends Enemy {

    private final int Max_Check = 32;
    private double Min_Check = 16;
    Player player = (Player) board.getPlayer();
    private double min;
    private int check = Max_Check;

    public Minvo(int x, int y, Board board) {
        super(x, y, board);
        speed = 0.5;
        sprite = Sprite.minvo_left1;
    }

    @Override
    public void move() {
        min = _x(x) + _y(y);
        if (!tileCollision(Min_Check, 0) && min > _x(x + 16) + _y(y) && check == Max_Check) {
            min = _x(x + 16) + _y(y);
            dir = 1;
        }
        if (!tileCollision(-Min_Check, 0) && min > _x(x - 16) + _y(y) && check == Max_Check) {
            min = _x(x - 16) + _y(y);
            dir = 0;
        }
        if (!tileCollision(0, Min_Check) && min > _x(x) + _y(y + 16) && check == Max_Check) {
            min = _x(x) + _y(y + 16);
            dir = 2;
        }
        if (!tileCollision(0, -Min_Check) && min > _x(x) + _y(y - 16) && check == Max_Check) {
            min = _x(x) + _y(y - 16);
            dir = 3;
        }
        if (check == Max_Check) {
            check = 0;
        }
        if (dir == 0) {
            x -= speed;
            check++;
        } else if (dir == 1) {
            x += speed;
            check++;
        } else if (dir == 2) {
            y += speed;
            check++;
        } else if (dir == 3){
            y -= speed;
            check++;
        }
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

    @Override
    public boolean checkCollision() {
        return true;
    }

    public void chooseSprite() {
        switch (dir) {
            case 0:
                sprite = Sprite.minvo_left1;
                sprite = Sprite.movingSprite(Sprite.minvo_left2, Sprite.minvo_left3, anim, 20);
                break;
            case 1:
                sprite = Sprite.minvo_right1;
                sprite = Sprite.movingSprite(Sprite.minvo_right2, Sprite.minvo_right3, anim, 20);
                break;
            default:
                sprite = Sprite.minvo_right1;
                sprite = Sprite.movingSprite(Sprite.minvo_right2, Sprite.minvo_right3, anim, 20);
                break;
        }
    }

    public double _x(double x1) {
        return (player.getX() - x1) * (player.getX() - x1);
    }

    public double _y(double y1) {
        return (player.getY() - y1) * (player.getY() - y1);
    }
}
