package sk.stuba.fei.uim.oop;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class MazeCanvas extends Canvas implements KeyListener {

    private ArrayList<Block> maze;

    public void keyPressed(KeyEvent event){}
    public void keyReleased(KeyEvent event){}
    public void keyTyped(KeyEvent event){}

    public MazeCanvas(){
        addKeyListener(this);
    }

    public void paint(Graphics g){
        g.setColor(Color.WHITE);
        for (Block tempBlock : maze) {
            if (tempBlock.getTopWall()) {
                g.drawLine(tempBlock.get_i() * 40, tempBlock.get_j() * 40, tempBlock.get_i() * 40 + 40, tempBlock.get_j() * 40);
            }

            if (tempBlock.getRightWall()) {
                g.drawLine(tempBlock.get_i() * 40 + 40, tempBlock.get_j() * 40, tempBlock.get_i() * 40 + 40, tempBlock.get_j() * 40 + 40);
            }

            if (tempBlock.getDownWall()) {
                g.drawLine(tempBlock.get_i() * 40 + 40, tempBlock.get_j() * 40 + 40, tempBlock.get_i() * 40, tempBlock.get_j() * 40 + 40);
            }

            if (tempBlock.getLeftWall()) {
                g.drawLine(tempBlock.get_i() * 40, tempBlock.get_j() * 40 + 40, tempBlock.get_i() * 40, tempBlock.get_j() * 40);
            }

        }
    }

    public void setMaze(ArrayList<Block> maze){
        this.maze = maze;
    }
}


