package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;

public class Window {

    private final int width;
    private final int height;
    private JFrame frame;
    private JPanel panel;
    private Canvas canvas;

    public Window(){
        this(700, 900);
    }

    public Window(int width, int height){
        this.height = height;
        this.width = width;
        this.initFrame();
        this.initCanvas();
        this.initMenu();
    }

    private void initFrame(){
        frame = new JFrame("Maze");
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        panel = new JPanel();
        frame.add(panel);
    }

    private void initCanvas(){
        canvas = new Canvas();
        canvas.setBackground(Color.darkGray);
        canvas.setSize(new Dimension(700, 700));
        panel.add(canvas);
    }

    private void initMenu(){
        JButton upButton = new JButton("UP");
        upButton.setPreferredSize(new Dimension(200, 65));
        upButton.setFocusable(false);

        JButton leftButton = new JButton("LEFT");
        leftButton.setPreferredSize(new Dimension(200, 65));
        leftButton.setFocusable(false);

        JButton downButton = new JButton("DOWN");
        downButton.setPreferredSize(new Dimension(200, 65));
        downButton.setFocusable(false);

        JButton rightButton = new JButton("RIGHT");
        rightButton.setPreferredSize(new Dimension(200, 65));
        rightButton.setFocusable(false);

        JButton restartButton = new JButton("RESTART");
        restartButton.setPreferredSize(new Dimension(200, 65));
        restartButton.setFocusable(false);

        JLabel levelLabel = new JLabel("Level: ");
        levelLabel.setPreferredSize(new Dimension(200, 65));
        levelLabel.setFocusable(false);

        panel.add(levelLabel);
        panel.add(upButton);
        panel.add(restartButton);
        panel.add(leftButton);
        panel.add(downButton);
        panel.add(rightButton);
    }
}
