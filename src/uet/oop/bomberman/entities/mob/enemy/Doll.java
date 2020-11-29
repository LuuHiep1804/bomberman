package uet.oop.bomberman.entities.mob.enemy;

import uet.oop.bomberman.DashBoard;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.mob.Player;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

import java.util.LinkedList;
import java.util.Queue;

public class Doll extends Enemy {

    private final int Max_Check = 32;
    Player player = (Player) board.getPlayer();
    private int [][] path = board.getPath();
    private int [][] checkPathY;
    private int [][] checkPathX;
    private int [][] checkPath;
    public boolean[][] visit;
    private int check = Max_Check;
    private int timeDeLay = 50;
    private int xDoll;
    private int yDoll;
    private int xPlayer;
    private int yPlayer;

    public Doll(int x, int y, DashBoard board) {
        super(x, y, board);
        speed = 0.5;
        sprite = Sprite.doll_left1;
    }

    @Override
    public void move() {
        if(alive) {
            if (check == Max_Check) {
                setGraph();
                bfs();
                check = 0;
            }
            if (checkPath[yDoll - 1][xDoll] == 1 && check < Max_Check && !tileCollision(0, -4)) {
                y -= speed;
                check++;
            }
            if (checkPath[yDoll + 1][xDoll] == 1 && check < Max_Check && !tileCollision(0, 3)) {
                y += speed;
                check++;
            }
            if (checkPath[yDoll][xDoll - 1] == 1 && check < Max_Check && !tileCollision(-speed, 0)) {
                x -= speed;
                check++;
            }
            if (checkPath[yDoll][xDoll + 1] == 1 && check < Max_Check && !tileCollision(6, 0)) {
                x += speed;
                check++;
            }
        }
    }

    @Override
    public void update() {
        if (!alive) {
            if (timeDeLay > 0) {
                timeDeLay--;
            }else {
                remove();
            }
        }
        super.update();
    }

    @Override
    public void render(Screen screen) {
        if (alive) {
            chooseSprite();
            super.render(screen);
        } else {
            sprite = Sprite.doll_dead;
            sprite = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, anim, 60);
            super.render(screen);
        }
    }

    public void chooseSprite() {
        switch (dir) {
            case 0:
                sprite = Sprite.doll_left1;
                sprite = Sprite.movingSprite(Sprite.doll_left2, Sprite.doll_left3, anim, 20);
                break;
            case 1:
                sprite = Sprite.doll_right1;
                sprite = Sprite.movingSprite(Sprite.doll_right2, Sprite.doll_right3, anim, 20);
                break;
            default:
                sprite = Sprite.doll_right1;
                sprite = Sprite.movingSprite(Sprite.doll_right2, Sprite.doll_right3, anim, 20);
                break;
        }
    }

    public void setGraph() {
        xDoll = (int) (x/16);
        yDoll = (int) (y/16) - 1;
        xPlayer = (int) player.getX()/16;
        yPlayer = (int) player.getY()/16;
        checkPathY = new int[13][29];
        checkPathX = new int[13][29];
        checkPath = new int [13][29];
        visit = new boolean[13][29];
    }

    public void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(yPlayer);
        queue.add(xPlayer);
        visit[yPlayer][xPlayer] = true;
        while (queue.size() != 0) {
            int qY = queue.remove();
            int qX = queue.remove();
            if (path[qY][qX-1] == 1 && visit[qY][qX-1] == false) {
                queue.add(qY);
                queue.add(qX-1);
                visit[qY][qX-1] = true;
                checkPathY[qY][qX-1] = qY;
                checkPathX[qY][qX-1] = qX;
            }
            if (path[qY][qX+1] == 1 && visit[qY][qX+1] == false) {
                queue.add(qY);
                queue.add(qX+1);
                visit[qY][qX+1] = true;
                checkPathY[qY][qX+1] = qY;
                checkPathX[qY][qX+1] = qX;
            }
            if (path[qY-1][qX] == 1 && visit[qY-1][qX] == false) {
                queue.add(qY-1);
                queue.add(qX);
                visit[qY-1][qX] = true;
                checkPathY[qY-1][qX] = qY;
                checkPathX[qY-1][qX] = qX;
            }
            if (path[qY+1][qX] == 1 && visit[qY+1][qX] == false) {
                queue.add(qY+1);
                queue.add(qX);
                visit[qY+1][qX] = true;
                checkPathY[qY+1][qX] = qY;
                checkPathX[qY+1][qX] = qX;
            }
        }
        if (visit[yDoll][xDoll] == false) {
            System.out.println("Error!");
            return;
        }
        int getCheckPathY = checkPathY[yDoll][xDoll];
        int getCheckPathX = checkPathX[yDoll][xDoll];
        checkPath[getCheckPathY][getCheckPathX] = 1;
    }
}
