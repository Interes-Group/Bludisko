package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;

public class Window {

    private final int width;
    private final int height;
    private JFrame frame;
    private JPanel panel;
    private MazeCanvas canvas;
    private Game game;

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
        panel.setBackground(Color.lightGray);
        frame.add(panel);
    }

    private void initCanvas(){
        canvas = new MazeCanvas();
        canvas.setSize(new Dimension(700, 700));
        canvas.setBackground(Color.DARK_GRAY);
        canvas.setFocusable(true);
        panel.add(canvas);
        canvas.createBufferStrategy(2);
    }

    private void initMenu(){
        JButton upButton = new JButton("UP");
        upButton.setPreferredSize(new Dimension(200, 65));
        upButton.setFocusable(false);
        upButton.addActionListener(canvas);

        JButton leftButton = new JButton("LEFT");
        leftButton.setPreferredSize(new Dimension(200, 65));
        leftButton.setFocusable(false);
        leftButton.addActionListener(canvas);

        JButton downButton = new JButton("DOWN");
        downButton.setPreferredSize(new Dimension(200, 65));
        downButton.setFocusable(false);
        downButton.addActionListener(canvas);

        JButton rightButton = new JButton("RIGHT");
        rightButton.setPreferredSize(new Dimension(200, 65));
        rightButton.setFocusable(false);
        rightButton.addActionListener(canvas);

        JButton restartButton = new JButton("RESTART");
        restartButton.setPreferredSize(new Dimension(200, 65));
        restartButton.setFocusable(false);
        restartButton.addActionListener(canvas);

        JLabel label = new JLabel("");
        label.setPreferredSize(new Dimension(200, 65));
        label.setFocusable(false);

        panel.add(label);
        panel.add(upButton);
        panel.add(restartButton);
        panel.add(leftButton);
        panel.add(downButton);
        panel.add(rightButton);
        canvas.initGame();
    }
}
