package uet.oop.bomberman.load_level;

import uet.oop.bomberman.exceptions.LoadLevelExceptions;
import uet.oop.bomberman.Board;
public abstract class Level extends LoadLevelExceptions {
    protected int width = 20, height = 20;
    protected int level;
    protected Board board;

    public Level(int level, Board board) throws LoadLevelExceptions{
        this.board = board;
        loadLevel(level);
    }

    public abstract void loadLevel(int level) throws LoadLevelExceptions;

    public abstract void creataEntities();

    public int getWidth() {return width;}

    public int getHeight() {return height;}

    public int getLevel() {return level;}
}
