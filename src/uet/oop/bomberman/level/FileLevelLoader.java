package uet.oop.bomberman.level;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.LayeredEntity;
import uet.oop.bomberman.entities.mob.Player;
import uet.oop.bomberman.entities.mob.enemy.Balloom;
import uet.oop.bomberman.entities.mob.enemy.Minvo;
import uet.oop.bomberman.entities.mob.enemy.Oneal;
import uet.oop.bomberman.entities.tile.GrassTile;
import uet.oop.bomberman.entities.tile.Items;
import uet.oop.bomberman.entities.tile.PortalTile;
import uet.oop.bomberman.entities.tile.WallTile;
import uet.oop.bomberman.entities.tile.destroyable.BrickTile;
import uet.oop.bomberman.exceptions.LoadLevelExceptions;
import uet.oop.bomberman.graphics.Sprite;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileLevelLoader extends LevelLoader {

    private static char[][] map;

    public FileLevelLoader(int level, Board board) throws LoadLevelExceptions {
        super(level, board);
    }

    @Override
    public void loadLevel(int level) throws LoadLevelExceptions {
        List<String> list = new ArrayList<>();
        try {
            FileReader fr = new FileReader("res\\levels\\Level" + level + ".txt");
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
                    case 's':
                        board.addEntity(pos, new LayeredEntity(x, y, new GrassTile(x, y, Sprite.grass), new Items(x, y, Sprite.powerup_speed)));
                        board.setItemAt(pos);
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
                        Player player = new Player(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILE_SIZE, board);
                        board.addMob(player);
                        board.setPlayer(player);
                        board.addEntity(x + y * width, new GrassTile(x, y, Sprite.grass));
                        break;
                    case '1':
                        board.addMob(new Balloom(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILE_SIZE, board));
                        board.addEntity(x + y * width, new GrassTile(x, y, Sprite.grass));
                        break;
                    case '2':
                        board.addMob(new Oneal(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILE_SIZE, board));
                        board.addEntity(pos, new GrassTile(x, y, Sprite.grass));
                        break;
                    case '3':
                        board.addMob(new Minvo(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILE_SIZE, board));
                        board.addEntity(pos, new GrassTile(x, y, Sprite.grass));
                        break;
                    default:
                        board.addEntity(pos, new GrassTile(x, y, Sprite.grass));
                        break;
                }
            }
        }
    }
}
