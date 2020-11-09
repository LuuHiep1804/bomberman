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
