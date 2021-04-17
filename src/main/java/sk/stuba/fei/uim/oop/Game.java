package sk.stuba.fei.uim.oop;

import java.util.ArrayList;

public class Game {
    private Block playerPos;
    private final ArrayList<Block> maze;
    private Block endPos;
    private MazeCanvas canvas;

    public Game(MazeCanvas canvas){
        Maze maze_init = new Maze();
        this.maze = maze_init.getMazeGrid();
        this.canvas = canvas;
        this.playerPos = this.maze.get(0);
        this.endPos = this.maze.get(this.maze.size()-1);
        this.canvas.setMaze(this.maze);
    }

    public Block getPlayerPos(){
        return this.playerPos;
    }

    public Block getEndPos(){
        return this.endPos;
    }
}
