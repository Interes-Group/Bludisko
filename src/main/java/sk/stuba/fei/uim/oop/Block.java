package sk.stuba.fei.uim.oop;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Block {
    private final int i;    //column in maze
    private final int j;    //row in maze
    private boolean top_wall;
    private boolean right_wall;
    private boolean down_wall;
    private boolean left_wall;
    private boolean visited;
    private ArrayList<Block> neighbours;

    public Block(int i, int j){
        this.i = i;
        this.j = j;
        this.top_wall = true;
        this.right_wall = true;
        this.down_wall = true;
        this.left_wall = true;
        this.visited = false;

    }

    public int getCols(){
        return i;
    }

    public int getRows(){
        return j;
    }

    public boolean getTopWall(){
        return top_wall;
    }

    public boolean getRightWall(){
        return right_wall;
    }

    public boolean getDownWall(){
        return down_wall;
    }

    public boolean getLeftWall(){
        return left_wall;
    }

    public int get_i(){
        return i;
    }

    public int get_j(){
        return j;
    }

    public boolean isVisited(){
        return this.visited;
    }

    public void setVisited(){
        this.visited = true;
    }

    public void setNeighbours(ArrayList<Block> neighbours){
        this.neighbours = neighbours;
    }

    public void removeTopWall(){
        this.top_wall = false;
    }

    public void removeRightWall(){
        this.right_wall = false;
    }

    public void removeDownWall(){
        this.down_wall = false;
    }

    public void removeLeftWall(){
        this.left_wall = false;
    }
}
