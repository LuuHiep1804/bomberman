package uet.oop.bomberman.user_interface;

import uet.oop.bomberman.Game;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame{

    public GamePanel gamePanel;
    public JPanel containerPane;
    public Game game;

    public Frame() {
        containerPane = new JPanel(new BorderLayout());
        gamePanel = new GamePanel(this);
        containerPane.add(gamePanel, BorderLayout.PAGE_END);
        game = gamePanel.getGame();
        add(containerPane);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        game.start();
    }

}
