package sk.stuba.fei.uim.oop;

public class Assignment2 {
    public static void main(String[] args) {
        Window window = new Window();


        Maze maze = new Maze();
        var grid = maze.getMazeGrid();
        for(Block s:grid){
            System.out.println(s.getRows() + "," + s.getCols() + " visited: " + s.isVisited());
        }
    }
}
