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

    private Keyboard input;
    private boolean running = false;
    private Board board;
    private Screen screen;
    private Frame frame;
    private static int bombRange = 1;
    private static double speed = 1.0;
    private static int bombRate = 1;

    public static int getBombRange() {
        return bombRange;
    }

    public static void addBombRange() {
        bombRange = 2;
    }

    public static int getBombRate() {
        return bombRate;
    }

    public static void addBombRate() {
        bombRate = bombRate + 1;
    }

    public static void removeBombRate() {
        bombRate = bombRate - 1;
    }

    public static double getSpeed() {
        return speed;
    }

    public static void addSpeed() {
        speed = speed + 0.15;
    }

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

    public Game(Frame frame) {
        this.frame = frame;
        frame.setTitle(TITLE);
        screen = new Screen(WIDTH, HEIGHT);
        input = new Keyboard();
        board = new Board(this, input, screen);
        addKeyListener(input);
    }

    public void update() {
        input.update();
        board.update();
    }

    public void renderGame() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
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

    public void start() {
        running = true;
        long lastTime = System.nanoTime();
        final double ns = 1000000000.0 / 60.0;
        double delta = 0;
        requestFocus();
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1) {
                update();
                delta--;
            }
            renderGame();
        }
    }

}
