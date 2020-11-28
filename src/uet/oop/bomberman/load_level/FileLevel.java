package uet.oop.bomberman.load_level;

import uet.oop.bomberman.DashBoard;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.LayeredEntity;
import uet.oop.bomberman.entities.mob.Player;
import uet.oop.bomberman.entities.mob.enemy.Balloom;
import uet.oop.bomberman.entities.mob.enemy.Kondoria;
import uet.oop.bomberman.entities.mob.enemy.Oneal;
import uet.oop.bomberman.entities.tile.GrassTile;
import uet.oop.bomberman.entities.tile.PortalTile;
import uet.oop.bomberman.entities.tile.WallTile;
import uet.oop.bomberman.entities.tile.destroyable.BrickTile;
import uet.oop.bomberman.entities.tile.item.bombItem;
import uet.oop.bomberman.entities.tile.item.flameItem;
import uet.oop.bomberman.entities.tile.item.speedItem;
import uet.oop.bomberman.exceptions.LoadLevelExceptions;
import uet.oop.bomberman.graphics.Sprite;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileLevel extends Level {

    private static char[][] map;

    public FileLevel(int level, DashBoard board) throws LoadLevelExceptions {
        super(level, board);
    }

    @Override
    public void loadLevel(int level) throws LoadLevelExceptions {
        List<String> list = new ArrayList<>();
        try {
            FileReader fr = new FileReader("res\\levels\\Level1.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (!line.equals("")) {
                list.add(line);
                line = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] input = list.get(0).trim().split(" ");
        this.level = Integer.parseInt(input[0]);
        height = Integer.parseInt(input[1]);
        width = Integer.parseInt(input[2]);
        map = new char[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                map[i][j] = list.get(i+1).charAt(j);
            }
        }
    }

    @Override
    public void creataEntities() {
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                int pos = x + y * getWidth();
                char c = map[y][x];
                switch (c) {
                    case ' ':
                        board.addEntity(pos, new GrassTile(x, y, Sprite.grass));
                        break;
                    case '#':
                        board.addEntity(pos, new WallTile(x, y, Sprite.wall));
                        break;
                    case 'x':
                        board.addEntity(pos, new LayeredEntity(x, y, new GrassTile(x, y, Sprite.grass), new PortalTile(x, y, board, Sprite.portal), new WallTile(x, y, Sprite.wall)));
                        break;
                    case '*':
                        board.addEntity(x + y * width,
                                new LayeredEntity(x, y,
                                        new GrassTile(x, y, Sprite.grass),
                                        new BrickTile(x, y, Sprite.brick)
                                )
                        );
                        break;
                    case 'p':
                        Player player = new Player(x * 16, y * 16 + Game.TILE_SIZE, board);
                        board.addMob(player);
                        board.setPlayer(player);
                        board.addEntity(x + y * width, new GrassTile(x, y, Sprite.grass));
                        break;
                    case '1':
                        board.addMob(new Balloom(x * 16, y * 16 + Game.TILE_SIZE, board));
                        board.addEntity(x + y * width, new GrassTile(x, y, Sprite.grass));
                        break;
                    case '2':
                        board.addMob(new Oneal(x * 16, y * 16 + Game.TILE_SIZE, board));
                        board.addEntity(pos, new GrassTile(x, y, Sprite.grass));
                        break;
                    case '3':
                        board.addMob(new Kondoria(x * 16, y * 16 + Game.TILE_SIZE, board));
                        board.addEntity(pos, new GrassTile(x, y, Sprite.grass));
                        break;
                    case 'X':
                        board.addEntity(pos, new LayeredEntity(x, y,
                                new GrassTile(x, y, Sprite.grass),
                                new PortalTile(x, y, board, Sprite.portal),
                                new BrickTile(x, y, Sprite.brick)));
                        break;
                    case 'b':
                        LayeredEntity layer = new LayeredEntity(x, y,
                                new GrassTile(x, y, Sprite.grass),
                                new bombItem(x, y, Sprite.powerup_bombs),
                                new BrickTile(x, y, Sprite.brick));
                        board.addEntity(pos, layer);
                        break;
                    case 'S':
                        layer = new LayeredEntity(x, y,
                                new GrassTile(x, y, Sprite.grass),
                                new speedItem(x, y, Sprite.powerup_speed),
                                new BrickTile(x, y, Sprite.brick));
                        board.addEntity(pos, layer);
                        break;
                    case 'f':
                        layer = new LayeredEntity(x, y,
                                new GrassTile(x, y, Sprite.grass),
                                new flameItem(x, y, Sprite.powerup_flames),
                                new BrickTile(x, y, Sprite.brick));
                        board.addEntity(pos, layer);
                        break;
                    default:
                        board.addEntity(pos, new GrassTile(x, y, Sprite.grass));
                        break;
                }
            }
        }
    }
}
