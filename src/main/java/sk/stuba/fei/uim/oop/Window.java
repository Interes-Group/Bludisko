package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;

public class Window {

    private final int width;
    private final int height;
    private JFrame frame;
    private JPanel panel;
    private JLabel completed_label;
    private MazeCanvas canvas;
    private Game game;
    private int game_counter;

    public Window(){
        this(700, 900);
    }

    public Window(int width, int height){
        this.height = height;
        this.width = width;
        this.initFrame();
        this.initCanvas();
        this.game_counter = 0;
    }

    private void initFrame(){
        frame = new JFrame("Maze");
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        panel = new JPanel();
        panel.setVisible(true);
        panel.setBackground(Color.lightGray);
        panel.setLayout(new BorderLayout());
        frame.add(panel);
    }

    private void initCanvas(){
        canvas = new MazeCanvas(this);
        canvas.setSize(new Dimension(700, 700));
        canvas.setBackground(Color.DARK_GRAY);
        canvas.setFocusable(true);
        JPanel canvas_panel = new JPanel();
        panel.add(canvas, BorderLayout.PAGE_START);
        canvas.createBufferStrategy(2);
        this.initMenu();
    }

    private void initMenu(){
        JButton upButton = new JButton("UP");
        upButton.setFocusable(false);
        upButton.addActionListener(canvas);

        JButton leftButton = new JButton("LEFT");
        leftButton.setFocusable(false);
        leftButton.addActionListener(canvas);

        JButton downButton = new JButton("DOWN");
        downButton.setFocusable(false);
        downButton.addActionListener(canvas);

        JButton rightButton = new JButton("RIGHT");
        rightButton.setFocusable(false);
        rightButton.addActionListener(canvas);

        JButton restartButton = new JButton("RESTART");
        restartButton.setFocusable(false);
        restartButton.addActionListener(canvas);

        JLabel label = new JLabel("Completed:    " + game_counter, SwingConstants.CENTER);
        label.setFocusable(false);
        this.completed_label = label;

        JPanel panel_menu = new JPanel();
        panel_menu.setLayout(new GridLayout(2, 3));
        panel_menu.add(label);
        panel_menu.add(upButton);
        panel_menu.add(restartButton);
        panel_menu.add(leftButton);
        panel_menu.add(downButton);
        panel_menu.add(rightButton);
        panel_menu.setVisible(true);
        panel.add(panel_menu, BorderLayout.CENTER);
        canvas.initGame();
    }

    public void setGameCounter(int i){
        this.game_counter = i;
        this.completed_label.setText("Completed:    "+i);
    }
}
