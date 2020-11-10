package uet.oop.bomberman.graphics;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.mob.Player;
import uet.oop.bomberman.entities.mob.enemy.Balloom;
import uet.oop.bomberman.entities.mob.enemy.Enemy;
import uet.oop.bomberman.entities.mob.enemy.Oneal;
import uet.oop.bomberman.entities.tile.GrassTile;
import uet.oop.bomberman.entities.tile.PortalTile;
import uet.oop.bomberman.entities.tile.WallTile;

import java.awt.font.GraphicAttribute;

public interface IRender {

        /**
         * Gọi khi 2 vật thể va chạm. Ghi đè định nghĩa trong các lớp con của Entity.
         * Cách dùng: collidingObj.handleCollision(this);   // Đặt hàm này trong phương thức
         * @param collidingObj Vật thể va chạm vào
         * if (x1 == x2 & y1 == y2) (cái mày va chạm vào, vd bomb, bomber, ...).HandleCollisionon(this)
         */
        void onCollisionEnter(Entity collidingObj);

        default void handleCollision(Player collidingObj) {

        }

        default void handleCollision(Enemy collidingObj) {

        }

        default void handleCollision(Oneal collidingObj) {

        }

        default void handleCollision(Bomb collidingObj) {

        }

        default void handleCollision(WallTile collidingObj) {

        }

        default void handleCollision(GrassTile collidingObj) {

        }

        default void handleCollision(PortalTile collidingObj) {

        }

        public void update();

        public void render(Screen screen);

}
