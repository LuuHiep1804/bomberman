package uet.oop.bomberman;

import uet.oop.bomberman.entities.Entity;

import uet.oop.bomberman.entities.mob.Mob;
import uet.oop.bomberman.exceptions.LoadLevelExceptions;
import uet.oop.bomberman.graphics.IRender;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.input.Keyboard;
import uet.oop.bomberman.level.FileLevelLoader;
import uet.oop.bomberman.level.LevelLoader;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Board implements IRender{

    protected LevelLoader levelLoader;
    protected Game game;
    protected Keyboard input;
    protected Screen screen;

    public Entity[] entities;
    public List<Mob> listMob = new ArrayList<>();

    private int screenShow = -1;

    public Board(Game game, Keyboard input, Screen screen) {
        this.game = game;
        this.input = input;
        this.screen = screen;
        loadLevel(1);
    }

    public LevelLoader getLevel() {
        return levelLoader;
    }

    public Game getGame() {
        return game;
    }

    public int getShow() {
        return screenShow;
    }

    public void setShow(int i) {
        screenShow = i;
    }

    public Keyboard getInput() {
        return input;
    }

    @Override
    public void update() {
        updateMob();
    }

    @Override
    public void render(Screen screen) {
        int x0 = Screen.xOffset >> 4;
        int x1 = (Screen.xOffset + screen.getWidth() + Game.TILE_SIZE) / Game.TILE_SIZE;
        int y0 = Screen.yOffset >> 4;
        int y1 = (Screen.yOffset + screen.getHeight()) / Game.TILE_SIZE;

        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                entities[x + y * levelLoader.getWidth()].render(screen);
            }
        }
        renderMob(screen);
    }


    public void loadLevel(int level){
        screenShow = 2;
        try{
            levelLoader = new FileLevelLoader(level, this);
            entities = new Entity[levelLoader.getWidth() * levelLoader.getHeight()];
            levelLoader.creataEntities();
        }catch (LoadLevelExceptions e) {
            ;
        }
    }

    public void addEntity(int pos, Entity e) {
        entities[pos] = e;
    }

    public void addMob(Mob e) {
        listMob.add(e);
    }

    protected void renderMob(Screen screen) {
        Iterator<Mob> itr = listMob.iterator();

        while(itr.hasNext())
            itr.next().render(screen);
    }

    protected void updateMob() {
        Iterator<Mob> itr = listMob.iterator();

        while(itr.hasNext()) {
            itr.next().update();
        }
    }
}
