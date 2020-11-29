package uet.oop.bomberman;

import uet.oop.bomberman.entities.Entity;

import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.bomb.Flame;
import uet.oop.bomberman.entities.mob.Mob;
import uet.oop.bomberman.exceptions.LoadLevelExceptions;
import uet.oop.bomberman.graphics.IRender;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.input.Keyboard;
import uet.oop.bomberman.load_level.FileLevel;
import uet.oop.bomberman.load_level.Level;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DashBoard implements IRender{

    protected Level levelLoader;
    protected Game game;
    protected Keyboard input;
    protected Screen screen;
    protected Mob player;
    protected int itemAt;

    public Entity[] entities;
    public int[][] path = new int[13][29];
    public List<Mob> listMob = new ArrayList<>();
    public List<Bomb> listBomb = new ArrayList<>();
    public List<Flame> listFlame = new ArrayList<>();

    public DashBoard(Game game, Keyboard input, Screen screen) {
        this.game = game;
        this.input = input;
        this.screen = screen;
        loadLevel(1);
    }

    public Level getLevel() {
        return levelLoader;
    }

    public Game getGame() {
        return game;
    }

    public Keyboard getInput() {
        return input;
    }

    public Mob getPlayer() {
        return player;
    }

    public void setPlayer(Mob player) {
        this.player = player;
    }

    public int getItemAt() {
        return itemAt;
    }

    public void setItemAt(int itemAt) {
        this.itemAt = itemAt;
    }

    @Override
    public void update() {
        updateEntities();
        updateMob();
        updateBomb();
        updateFlame();
        removeMob();
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
        renderBomb(screen);
        renderFlame(screen);
    }


    public void loadLevel(int level){
        try{
            levelLoader = new FileLevel(level, this);
            entities = new Entity[levelLoader.getWidth() * levelLoader.getHeight()];
            levelLoader.creataEntities();
        }catch (LoadLevelExceptions e) {
            ;
        }
    }

    public void addPath(int x, int y, int z) {
        path[x][y] = z;
    }

    public int[][] getPath() {
        return path;
    }

    //--------------------------Entity---------------------------------------------------------------------------
    public void addEntity(int pos, Entity e) {
        entities[pos] = e;
    }

    public void updateEntities() {
        for (int i = 0; i < entities.length; i++) {
            entities[i].update();
        }
    }

    public Entity getTile(double x, double y) {
        return entities[(int) x + (int) y * levelLoader.getWidth()];
    }

    //---------------------------Mob-----------------------------------------------------------------------------
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

    public Mob getMob(int x, int y, Mob a) {
        Iterator<Mob> itr = listMob.iterator();
        Mob m;
        while (itr.hasNext()) {
            m = itr.next();
            if (m == a) continue;
            if (m.getTileX() == x && m.getTileY() == y) {
                return m;
            }
        }
        return null;
    }

    public void removeMob() {
        for (int i = 0; i < listMob.size(); i++) {
            if (listMob.get(i).isRemoved()) {
                listMob.remove(i);
            }
        }
    }

    //-----------------------------BOMB--------------------------------------------------------------------------
    public void addBomb(Bomb b) {
        listBomb.add(b);
    }

    public List<Bomb> getBombs() {
        return listBomb;
    }

    public void renderBomb(Screen screen) {
        Iterator<Bomb> itr = listBomb.iterator();
        while(itr.hasNext()) {
            itr.next().render(screen);
        }
    }

    public void updateBomb() {
        Iterator<Bomb> itr = listBomb.iterator();
        while (itr.hasNext()) {
            itr.next().update();
        }
    }

    public Bomb getBomb(double x, double y) {
        Iterator<Bomb> itr = listBomb.iterator();
        Bomb b;
        while (itr.hasNext()) {
            b = itr.next();
            if (b.getX() == (int) x && b.getY() == (int) y) {
                return b;
            }
        }
        return null;
    }

    //-----------------------------Flame--------------------------------
    public void addFlame(Flame flame) {
        listFlame.add(flame);
    }

    public void renderFlame(Screen screen) {
        Iterator<Flame> itr = listFlame.iterator();
        while(itr.hasNext()) {
            itr.next().render(screen);
        }
    }

    public void updateFlame() {
        Iterator<Flame> itr = listFlame.iterator();
        while(itr.hasNext()) {
            itr.next().update();
        }
    }

    public Flame getFlameAt(int x, int y) {
        Iterator<Bomb> bs = listBomb.iterator();
        Bomb b;
        while (bs.hasNext()) {
            b = bs.next();
            Flame f = b.flameAt(x, y);
            if (f != null) {
                return f;
            }
        }
        return null;
    }
}
