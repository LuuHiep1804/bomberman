package uet.oop.bomberman.entities.mob;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.tile.Tile;
import uet.oop.bomberman.entities.tile.WallTile;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.input.Keyboard;

import java.awt.*;

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
        if (this.x + xa > Game.TILE_SIZE && this.x + xa < 26.4 * Game.TILE_SIZE) {
            x += xa * speed;
        }
        if (this.y + ya > 2 * Game.TILE_SIZE - 0.1 && this.y + ya < 12 * Game.TILE_SIZE + 0.1) {
            y += ya * speed;
        }
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
