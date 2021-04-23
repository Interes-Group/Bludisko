package sk.stuba.fei.uim.oop;

import java.util.ArrayList;

public class Game {
    private Block playerPos;
    private final ArrayList<Block> maze;
    private Block endPos;
    private MazeCanvas canvas;

    private static final int ROWS = 14;
    private static final int COLS = 14;

    public Game(MazeCanvas canvas){
        Maze maze_init = new Maze();
        this.maze = maze_init.getMazeGrid();
        this.canvas = canvas;
        this.playerPos = this.maze.get(0);
        this.endPos = this.maze.get(maze.size()-1);
        this.canvas.setMaze(this.maze);
    }

    public Block playerMove(String direction){
        if(direction.equals("Up") && !playerPos.getTopWall()){
            this.playerPos = maze.get(index(playerPos.get_i(), playerPos.get_j()-1));
            System.out.println("Moving UP!");
        }

        if(direction.equals("Right") && !playerPos.getRightWall()){
            this.playerPos = maze.get(index(playerPos.get_i()+1, playerPos.get_j()));
            System.out.println("Moving RIGHT!");
        }

        if(direction.equals("Down") && !playerPos.getDownWall()){
            this.playerPos = maze.get(index(playerPos.get_i(), playerPos.get_j()+1));
            System.out.println("Moving DOWN!");
        }

        if(direction.equals("Left") && !playerPos.getLeftWall()){
            this.playerPos = maze.get(index(playerPos.get_i()-1, playerPos.get_j()));
            System.out.println("Moving LEFT!");
        }
        return this.playerPos;
    }

    public Block getPlayerPos(){
        return this.playerPos;
    }

    public Block getEndPos(){
        return this.endPos;
    }

    private int index(int i, int j){
        if(i<0 || j<0 || i>COLS-1 || j>ROWS-1){
            return -9999;
        }
        else{
            return i + (j * COLS);
        }
    }

    private Block getTopBlock(Block block){
        return maze.get(index(block.get_i(), block.get_j()-1));
    }

    private Block getBottomBlock(Block block){
        return maze.get(index(block.get_i(), block.get_j()+1));
    }

    private Block getRightBlock(Block block){
        return maze.get(index(block.get_i()+1, block.get_j()));
    }

    private Block getLeftBlock(Block block){
        return maze.get(index(block.get_i()-1, block.get_j()));
    }

    public ArrayList<Block> getBlocksToMove(){
        ArrayList<Block> movingBlocks = new ArrayList<>();

        //get top blocks
        var current = playerPos;
        while(!current.getTopWall()){
            movingBlocks.add(current);
            current = getTopBlock(current);
        }
        movingBlocks.add(current);
        movingBlocks.remove(playerPos);

        //get bottom blocks
        current = playerPos;
        while(!current.getDownWall()){
            movingBlocks.add(current);
            current = getBottomBlock(current);
        }
        movingBlocks.add(current);
        movingBlocks.remove(playerPos);

        //get right blocks
        current = playerPos;
        while(!current.getRightWall()){
            movingBlocks.add(current);
            current = getRightBlock(current);
        }
        movingBlocks.add(current);
        movingBlocks.remove(playerPos);

        //get left blocks
        current = playerPos;
        while(!current.getLeftWall()){
            movingBlocks.add(current);
            current = getLeftBlock(current);
        }
        movingBlocks.add(current);
        movingBlocks.remove(playerPos);

        return movingBlocks;
    }

    public void setPlayerPos(Block s){
        this.playerPos = s;
    }

}
