package uet.oop.bomberman.graphics;

import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Entity;

import java.awt.*;

public class Screen {
    protected int width, height;
    public int[] pixels;
    private int transparentColor = 0xffff00ff;

    public static int xOffset = 0, yOffset = 0;

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[this.width * this.height];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getRealWidth() {
        return width * Game.SCALE;
    }

    public int getRealHeight() {
        return height * Game.SCALE;
    }

    public void Clear(){
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }

    public void renderEntity(int xa, int ya, Entity entity) {
        for (int y = 0; y < entity.getSprite().getSize(); y++) {
            int yb = y + ya;
            for (int x = 0; x < entity.getSprite().getSize(); x++) {
                int xb = x + xa;
                if (xb < -entity.getSprite().getSize() || xb >= width || yb < 0 || yb >= height){
                    break;
                }
                if (xb < 0) {
                    xb = 0;
                }
                int col = entity.getSprite().getPixel(x + y * entity.getSprite().getSize());
                if (col != transparentColor) {
                    pixels[xb + yb * width] = col;
                }
            }
        }
    }

    public void renderEntityWithBeLowSprite(int xa, int ya, Entity entity, Sprite below) {
        for (int y = 0; y < entity.getSprite().getSize(); y++) {
            int yb = y + ya;
            for (int x = 0; x < entity.getSprite().getSize(); x++) {
                int xb = x + xa;
                if (xb < -entity.getSprite().getSize() || xb >= width || yb < 0 || yb >= height) {
                    break;
                }
                if (xb < 0) {
                    xb = 0;
                }
                int col = entity.getSprite().getPixel(x + y * entity.getSprite().getSize());
                if (col != transparentColor) {
                    pixels[xb + yb * width] = col;
                }else {
                    pixels[xb + yb * width] = below.getPixel(x + y * below.getSize());
                }
            }
        }
    }
}
