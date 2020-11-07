package uet.oop.bomberman.exceptions;

public class GameExceptions extends Exception{
    public GameExceptions() {}

    public GameExceptions(String str) {
        super(str);
    }

    public GameExceptions(String str, Throwable cause) {
        super(str, cause);
    }

    public GameExceptions(Throwable cause) {
        super(cause);
    }
}
