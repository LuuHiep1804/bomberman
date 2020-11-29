package uet.oop.bomberman;

import uet.oop.bomberman.game_sound.GameSound;
import uet.oop.bomberman.user_interface.Frame;

public class BombermanGame {
    public static void main(String[] args) {
        GameSound.play("playgame");
        new Frame();
    }
}
