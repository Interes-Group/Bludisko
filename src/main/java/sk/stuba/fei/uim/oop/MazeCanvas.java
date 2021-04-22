package sk.stuba.fei.uim.oop;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MazeCanvas extends Canvas implements KeyListener, ActionListener, MouseListener {

    private ArrayList<Block> maze;
    private Game game;
    private Block playerPos;
    private Block endPos;
    private int level_counter;
    private final Window window;
    private int click_num;      //0 -> select point, 1 -> select position you want to move to
    private ArrayList<Block> moving_blocks;

    private static final int POS_MID = 65;

    public MazeCanvas(Window window){
        addKeyListener(this);
        this.level_counter = 1;
        this.window = window;
        addMouseListener(this);
        this.click_num = 0;
        this.moving_blocks = new ArrayList<>();
    }

    public void keyPressed(KeyEvent event){
        int keyCode = event.getKeyCode();
        moving_blocks.clear();

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
        var button = button_event.getActionCommand();
        moving_blocks.clear();

        if(button.equals("UP")){
            this.playerPos = game.playerMove("Up");
            repaint();
        }

        if(button.equals("RIGHT")){
            this.playerPos = game.playerMove("Right");
            repaint();
        }

        if(button.equals("DOWN")){
            this.playerPos = game.playerMove("Down");
            repaint();
        }

        if(button.equals("LEFT")){
            this.playerPos = game.playerMove("Left");
            repaint();
        }

        if(button.equals("RESTART")){
            this.level_counter = 1;
            initGame();
            window.setGameCounter(0);
            repaint();
        }
        checkIfEnd();
    }

    public void mouseClicked(MouseEvent mouse_event){
        var x = mouse_event.getX();
        var y = mouse_event.getY();

        if((x>playerPos.get_i()*40+POS_MID) && (x<playerPos.get_i()*40+40+POS_MID) && (y>playerPos.get_j()*40+POS_MID) && (y<playerPos.get_j()*40+40+POS_MID) && (click_num == 0)){
            moving_blocks = game.getBlocksToMove();
            if(moving_blocks.size()>0){
                repaint();
                click_num = 1;
            }
        }
        else{
            if(moving_blocks.size()>0){
                Block foundBlock = null;
                for(Block s: moving_blocks){
                    if((x>s.get_i()*40+POS_MID) && (x<s.get_i()*40+40+POS_MID) && (y>s.get_j()*40+POS_MID) && (y<s.get_j()*40+40+POS_MID) && (click_num==1)){
                        foundBlock = s;
                    }
                }
                if(foundBlock!=null){
                    this.playerPos = foundBlock;
                    game.setPlayerPos(this.playerPos);
                    click_num = 0;
                    moving_blocks.clear();
                    repaint();
                    checkIfEnd();
                }
                else{
                    click_num = 0;
                    moving_blocks.clear();
                    repaint();
                }
            }
            else{
                repaint();
                click_num = 0;
            }

        }
    }

    public void mouseEntered(MouseEvent mouse_event){}
    public void mouseExited(MouseEvent mouse_event){}
    public void mousePressed(MouseEvent mouse_event){}
    public void mouseReleased(MouseEvent mouse_event){}


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

        //moving blocks
        g.setColor(Color.LIGHT_GRAY);
        if(moving_blocks.size()>0){
            for(Block s: moving_blocks){
                g.fillRect(s.get_i()*40 + 5 + POS_MID, s.get_j()*40 + 5 + POS_MID, 35, 35);
            }
        }
    }

    public void setMaze(ArrayList<Block> maze){
        this.maze = maze;
    }

    public void initGame(){
        this.game = new Game(this);
        playerPos = game.getPlayerPos();
        endPos = game.getEndPos();
    }

    private void checkIfEnd(){
        if(this.playerPos==this.endPos){
            initGame();
            System.out.println("New Level!");
            level_counter++;
            window.setGameCounter(level_counter-1);
        }
    }
}


