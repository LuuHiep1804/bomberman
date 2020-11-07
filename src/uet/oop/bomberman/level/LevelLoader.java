package uet.oop.bomberman.level;

import uet.oop.bomberman.exceptions.LoadLevelExceptions;
import uet.oop.bomberman.Board;
public abstract class LevelLoader extends LoadLevelExceptions {
    protected int width = 31, height = 31;
    protected int level;
    protected Board board;

    public LevelLoader(int level, Board board) throws LoadLevelExceptions{
        this.board = board;
        loadLevel(level);
    }

    public abstract void loadLevel(int level) throws LoadLevelExceptions;

    public abstract void creataEntities();

    public int getWidth() {return width;}

    public int getHeight() {return height;}

    public int getLevel() {return level;}
}
