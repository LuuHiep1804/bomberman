package uet.oop.bomberman.entities.mob.enemy;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.mob.Player;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

public class Minvo extends Enemy {

    private final int Max_Check = 16;
    Player player = (Player) board.getPlayer();
    private double min;
    private int check = Max_Check;
    private int timeDeLay = 50;

    public Minvo(int x, int y, Board board) {
        super(x, y, board);
        speed = 1;
        sprite = Sprite.minvo_left1;
    }

    @Override
    public void move() {
        if (alive) {
            min = getAt(x, y);
            if (min > getAt(x + 8, y) && check == Max_Check) {
                dir = 1;
                min = getAt(x + 8, y);
                check = 0;
            } else if (min > getAt(x - 8, y) && check == Max_Check) {
                dir = 0;
                min = getAt(x - 8, y);
                check = 0;
            } else if (min > getAt(x, y + 8) && check == Max_Check) {
                dir = 2;
                min = getAt(x, y + 8);
                check = 0;
            } else if (min > getAt(x, y - 8) && check == Max_Check) {
                dir = 3;
                min = getAt(x, y - 8);
                check = 0;
            }
            if (dir == 0 && check < Max_Check) {
                x -= speed;
                check++;
            } else if (dir == 1 && check < Max_Check) {
                x += speed;
                check++;
            } else if (dir == 2 && check < Max_Check) {
                y += speed;
                check++;
            } else if (dir == 3 && check < Max_Check) {
                y -= speed;
                check++;
            }
        }
    }

    @Override
    public void update() {
        if (!alive) {
            if (timeDeLay > 0) {
                timeDeLay--;
            }else {
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
            sprite = Sprite.minvo_dead;
            sprite = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, anim, 60);
            super.render(screen);
        }
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

    public double getAt(double x1, double y1) {
        return Math.abs(player.getX() - x1) + Math.abs(player.getY() - y1);
    }
}
