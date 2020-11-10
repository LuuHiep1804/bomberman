package uet.oop.bomberman.entities.mob;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.input.Keyboard;

public class Player extends Mob{

    private double speed = 1.0;
    public Keyboard input;

    public Player(int x, int y, Board board) {
        super(x, y, board);
        sprite = Sprite.player_right;
        this.input = board.getInput();
    }

    @Override
    public void move() {
        int xa = 0, ya = 0;
        if (input.up) {
            sprite = Sprite.player_up_1;
            ya--;
        }
        if (input.down) {
            sprite = Sprite.player_down_1;
            ya++;
        }
        if (input.right) {
            sprite = Sprite.player_right_1;
            xa++;
        }
        if (input.left) {
            sprite = Sprite.player_left_1;
            xa--;
        }
        for (int i = 0; i < board.listTile.size(); i++) {
            if (board.listTile.get(i).getX() == x + 12 && board.listTile.get(i).getY() >= y - 13
                    && board.listTile.get(i).getY() <= y + 12) {
                if (input.right) {
                    xa = 0;
                    ya = 0;
                    break;
                }
            }
            if (board.listTile.get(i).getX() == x - 16 && board.listTile.get(i).getY() >= y - 13
                    && board.listTile.get(i).getY() <= y + 12) {
                if (input.left) {
                    xa = 0;
                    ya = 0;
                    break;
                }
            }
            if (board.listTile.get(i).getX() >= x - 12 && board.listTile.get(i).getX() <= x + 10
                    && board.listTile.get(i).getY() == y + 15) {
                if (input.down) {
                    xa = 0;
                    ya = 0;
                    break;
                }
            }
            if (board.listTile.get(i).getX() >= x - 10 && board.listTile.get(i).getX() <= x + 10
                    && board.listTile.get(i).getY() == y + 16) {
                if (input.up) {
                    xa = 0;
                    ya = 0;
                    break;
                }
            }
        }
        if (this.x + xa > Game.TILE_SIZE && this.x + xa < 26.4 * Game.TILE_SIZE) {
            x += xa * speed;
        }
        if (this.y + ya > 2 * Game.TILE_SIZE - 0.1 && this.y + ya < 12 * Game.TILE_SIZE + 0.1) {
            y += ya * speed;
        }
    }

    @Override
    public void onCollisionEnter(Entity collidingObj) {

    }

    @Override
    public void update() {
        move();
    }

    @Override
    public void render(Screen screen) {
        screen.renderEntity((int) x, (int) y - sprite.getSize(), this );
    }
}
