package uet.oop.bomberman.exceptions;

public class LoadLevelExceptions extends GameExceptions{
    public LoadLevelExceptions() {}

    public LoadLevelExceptions(String str) {
        super(str);
    }

    public LoadLevelExceptions(Throwable cause) {
        super(cause);
    }

    public LoadLevelExceptions(String str, Throwable cause) {
        super(str, cause);
    }
}
