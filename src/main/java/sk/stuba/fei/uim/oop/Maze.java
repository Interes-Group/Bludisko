package sk.stuba.fei.uim.oop;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Maze {
    private final int mazeWidth;    //columns
    private final int mazeHeight;   //rows
    private ArrayList<Block> grid;
    private Block current;
    private Stack<Block> stack;
    private int visited = 0;
    public int count = 0;

    public Maze(int width, int height){
        this.mazeWidth = width;
        this.mazeHeight = height;
        grid = new ArrayList<>();
        stack = new Stack<>();
        this.createMaze();
    }

    public Maze(){
        this(14, 14);   //default value
    }

    private void createMaze(){
        for(var j = 0; j < mazeHeight; j++){
            for(var i = 0; i < mazeWidth; i++){
                var block = new Block(i, j);
                grid.add(block);
                count++;
            }
        }
        this.current = grid.get(0);
        grid.get(0).setVisited();
        visited++;
        stack.push(current);

        while(visited<mazeHeight*mazeHeight){
            Block next = getNeighbor(current);
            if(next!=null){
                next.setVisited();
                stack.push(next);
                removeWalls(current, next);
                current = next;
                visited++;
                System.out.println("Stack " + stack.size() + " Visited: " + visited);
            }
            else{
                current = stack.pop();
            }
        }
    }

    public ArrayList<Block> getMazeGrid(){
        return grid;
    }

    public Block getCurrent(){
        return current;
    }

    private int index(int i, int j){
        if(i<0 || j<0 || i>mazeWidth-1 || j>mazeHeight-1){
            return -9999;
        }
        else{
            return i + (j * mazeWidth);
        }
    }

    private Block getNeighbor(Block block){
        ArrayList<Block> neigh = new ArrayList<>();

        Random rand = new Random();

        Block top = null;
        Block right = null;
        Block bottom = null;
        Block left = null;

        if(index(block.get_i(), block.get_j()-1)>=0 && index(block.get_i(), block.get_j()-1)<196){
            top = grid.get(index(block.get_i(), block.get_j()-1));
        }

        if(index(block.get_i()+1, block.get_j())>=0 && index(block.get_i()+1, block.get_j())<196){
            right = grid.get(index(block.get_i()+1, block.get_j()));
        }

        if(index(block.get_i(), block.get_j()+1)>=0 && index(block.get_i(), block.get_j()+1)<196){
            bottom = grid.get(index(block.get_i(), block.get_j()+1));
        }

        if(index(block.get_i()-1, block.get_j())>=0 && index(block.get_i()-1, block.get_j())<196){
            left = grid.get(index(block.get_i()-1, block.get_j()));
        }


        if(top!=null && !top.isVisited()){
            neigh.add(top);
        }

        if(right!=null && !right.isVisited()){
            neigh.add(right);
        }

        if(bottom!=null && !bottom.isVisited()){
            neigh.add(bottom);
        }

        if(left!=null && !left.isVisited()){
            neigh.add(left);
        }

        if(neigh.size()>0){
            int random = rand.nextInt(neigh.size());
            return neigh.get(random);
        }

        else{
            return null;
        }
    }

    private void removeWalls(Block a, Block b){
        var x = a.get_i() - b.get_i();
        if(x==1){
            a.removeLeftWall();
            b.removeRightWall();
        }
        else if(x==-1){
            a.removeRightWall();
            b.removeLeftWall();
        }

        var y = a.get_j() - b.get_j();
        if(y==1){
            a.removeTopWall();
            b.removeDownWall();
        }
        if(y==-1){
            a.removeDownWall();
            b.removeTopWall();
        }
    }

}


