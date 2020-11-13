package uet.oop.bomberman.entities.mob.enemy;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Balloom extends Enemy{

    private double speed = 0.5;

    public Balloom(int x, int y, Board board) {
        super(x, y, board);
        sprite = Sprite.balloom_left1;
    }

    @Override
    public boolean checkCollision() {
        return true;
    }
}
