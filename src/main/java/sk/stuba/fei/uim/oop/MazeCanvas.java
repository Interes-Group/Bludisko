package sk.stuba.fei.uim.oop;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class MazeCanvas extends Canvas implements KeyListener, ActionListener {

    private ArrayList<Block> maze;
    private Game game;
    private Block playerPos;
    private Block endPos;
    private int level_counter = 1;

    private static final int POS_MID = 65;

    public void keyPressed(KeyEvent event){
        int keyCode = event.getKeyCode();

        if(keyCode == 37){  //left
            this.playerPos = game.playerMove("Left");
            repaint();
        }

        if(keyCode == 38){  //up
            this.playerPos = game.playerMove("Up");
            repaint();
        }

        if(keyCode == 39){  //right
            this.playerPos = game.playerMove("Right");
            repaint();
        }

        if(keyCode == 40){  //down
            this.playerPos = game.playerMove("Down");
            repaint();
        }

        checkIfEnd();
    }
    public void keyReleased(KeyEvent event){}
    public void keyTyped(KeyEvent event){}


    public void actionPerformed(ActionEvent button_event){
        System.out.println("Test");
    }

    public MazeCanvas(){
        addKeyListener(this);
        initGame();
    }

    public void paint(Graphics g){
        g.setColor(Color.WHITE);
        for (Block tempBlock : maze) {
            if (tempBlock.getTopWall()) {
                g.drawLine(tempBlock.get_i() * 40 + POS_MID, tempBlock.get_j() * 40 + POS_MID, tempBlock.get_i() * 40 + 40 + POS_MID, tempBlock.get_j() * 40 + POS_MID);
            }

            if (tempBlock.getRightWall()) {
                g.drawLine(tempBlock.get_i() * 40 + 40 + POS_MID, tempBlock.get_j() * 40 + POS_MID, tempBlock.get_i() * 40 + 40 + POS_MID, tempBlock.get_j() * 40 + 40 + POS_MID);
            }

            if (tempBlock.getDownWall()) {
                g.drawLine(tempBlock.get_i() * 40 + 40 + POS_MID, tempBlock.get_j() * 40 + 40 + POS_MID, tempBlock.get_i() * 40 + POS_MID, tempBlock.get_j() * 40 + 40 + POS_MID);
            }

            if (tempBlock.getLeftWall()) {
                g.drawLine(tempBlock.get_i() * 40 + POS_MID, tempBlock.get_j() * 40 + 40 + POS_MID, tempBlock.get_i() * 40 + POS_MID, tempBlock.get_j() * 40 + POS_MID);
            }
        }

        //paint level_counter
        g.drawString("Level: " + level_counter, this.getWidth()/2 - 15, 40);

        //paint actual pos
        g.setColor(Color.YELLOW);
        g.fillRect(playerPos.get_i() * 40 + 10 + POS_MID, playerPos.get_j() * 40 + 10 + POS_MID, 20, 20);

        //paint finish pos
        g.setColor(Color.GREEN);
        g.fillRect(endPos.get_i()*40 + 5 + POS_MID, endPos.get_j()*40 + 5 + POS_MID, 35, 35);
    }

    public void setMaze(ArrayList<Block> maze){
        this.maze = maze;
    }

    private void initGame(){
        this.game = new Game(this);
        playerPos = game.getPlayerPos();
        endPos = game.getEndPos();
    }

    private void checkIfEnd(){
        if(this.playerPos==this.endPos){
            initGame();
            System.out.println("New Level!");
            level_counter++;
        }
    }
}


