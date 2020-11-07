package uet.oop.bomberman.entities.mob.enemy;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.graphics.Sprite;

public class Balloom extends Enemy{
    public Balloom(int x, int y, Board board) {
        super(x, y, board);
        sprite = Sprite.balloom_left1;
    }
}
