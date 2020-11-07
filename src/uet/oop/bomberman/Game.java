package uet.oop.bomberman;

import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.input.Keyboard;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Game extends Canvas {

    public static final int TILE_SIZE = 16;
    public static final int WIDTH = TILE_SIZE * (28);
    public static final int HEIGHT = TILE_SIZE * 13;

    public static int SCALE = 3;
    public static final String TITLE = "Bomberman";

    protected static int SCREENDELAY = 3;
    protected int screenDelay = SCREENDELAY;

    private Keyboard input;
    private boolean running = false;
    private boolean paused = true;
    private Board board;
    private Screen screen;
    private Frame frame;

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

    public Game(Frame frame) {
        this.frame = frame;
        frame.setTitle(TITLE);
        screen = new Screen(WIDTH, HEIGHT);
        board = new Board(this,input, screen);
        addKeyListener(input);
    }

    public void renderGame() {
        BufferStrategy bs = getBufferStrategy();
        if(bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.Clear();

        board.render(screen);

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }

        Graphics g = bs.getDrawGraphics();

        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

        g.dispose();
        bs.show();
    }

    public void renderScreen() {
        BufferStrategy bs = getBufferStrategy();
        if(bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.Clear();

        Graphics g = bs.getDrawGraphics();

        board.drawScreen(g);

        g.dispose();
        bs.show();
    }

    public void start() {
        running = true;
        while (running){
            if(paused) {
                if(screenDelay <= 0) {
                    board.setShow(-1);
                    paused = false;
                }
                renderScreen();
            } else {
                renderGame();
            }
            if(board.getShow() == 2)
                --screenDelay;
        }
    }
}
