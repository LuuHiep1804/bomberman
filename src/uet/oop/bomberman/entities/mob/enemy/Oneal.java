package uet.oop.bomberman.entities.mob.enemy;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Enemy{
    public Oneal(int x, int y, Board board) {
        super(x, y, board);
        sprite = Sprite.oneal_left1;
    }
}
