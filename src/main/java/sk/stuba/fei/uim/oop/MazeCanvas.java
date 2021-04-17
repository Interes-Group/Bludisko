package sk.stuba.fei.uim.oop;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class MazeCanvas extends Canvas implements KeyListener {

    private ArrayList<Block> maze;
    private Game game;
    private Block playerPos;
    private Block endPos;

    public void keyPressed(KeyEvent event){
        int keyCode = event.getKeyCode();

        if(keyCode == 37){  //left
            this.playerPos = game.playerMove("Left");
        }

        if(keyCode == 38){  //up
            this.playerPos = game.playerMove("Up");
        }

        if(keyCode == 39){  //right
            this.playerPos = game.playerMove("Right");
        }

        if(keyCode == 40){  //down
            this.playerPos = game.playerMove("Down");
        }
        repaint();
    }
    public void keyReleased(KeyEvent event){}
    public void keyTyped(KeyEvent event){}

    public MazeCanvas(){
        addKeyListener(this);
        initGame();
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

        //paint actual pos
        g.setColor(Color.YELLOW);
        g.fillRect(playerPos.get_i() * 40 + 10, playerPos.get_j() * 40 + 10, 20, 20);

        //paint finish pos
        g.setColor(Color.GREEN);
        g.fillRect(endPos.get_i()*40 + 5, endPos.get_j()*40 + 5, 35, 35);
    }

    public void setMaze(ArrayList<Block> maze){
        this.maze = maze;
    }

    private void initGame(){
        this.game = new Game(this);
        playerPos = game.getPlayerPos();
        endPos = game.getEndPos();
    }
}


