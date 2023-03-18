import java.util.ArrayList;
import java.util.Stack;

/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    public boolean findMazePath(int x, int y) {
        // COMPLETE HERE FOR PROBLEM 1
//        method to find the path of the maze
        if(maze.getNRows()<=x || maze.getNCols()<=y || x<0 || y<0) {
            return false;
        }
        if(!maze.getColor(x,y).equals(NON_BACKGROUND))
        {
            return false;
        }
        if(maze.getNRows()-1==x && maze.getNCols()-1==y)
        {
            maze.recolor(x,y,PATH);
            return true;
        }
        else
        {
            maze.recolor(x,y,PATH);
            if(findMazePath(x-1,y) || findMazePath(x+1,y) || findMazePath(x,y-1) || findMazePath(x,y+1))
            {
                return true;
            }
            else
            {
                maze.recolor(x,y,TEMPORARY);
                return false;
            }
        }
    }
//method to collect the path coOrdinate points and store
    public void findMazePathStackBased(int x,int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace)
    {
        if((maze.getNRows()<=x || maze.getNCols()<=y || x<0 || y<0) || (!maze.getColor(x,y).equals(NON_BACKGROUND)))
        {
            return;
        }
        else if(maze.getNRows()-1==x && maze.getNCols()-1==y)
        {
            PairInt obj=new PairInt(x,y);
            trace.push(obj);
            ArrayList<PairInt> obj1 =new ArrayList<>(trace);
            result.add(obj1);
        trace.pop();
            maze.recolor(x,y,NON_BACKGROUND);

            return;
        }
        else
        {
            PairInt obj=new PairInt(x,y);
            trace.push(obj);
            maze.recolor(x,y,PATH);
            findMazePathStackBased(x-1,y,result,trace);
            findMazePathStackBased(x+1,y,result,trace);
            findMazePathStackBased(x,y-1,result,trace);
            findMazePathStackBased(x,y+1,result,trace);
            maze.recolor(x,y,NON_BACKGROUND);
            trace.pop();
            return;

        }
    }
    // ADD METHOD FOR PROBLEM 2 HERE
//    method to collect all possible maze path
    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y)
    {
        ArrayList<ArrayList<PairInt>> result=new ArrayList<>();
        Stack<PairInt> trace = new Stack<>();
        findMazePathStackBased(0,0,result,trace);
        System.out.println("Possible Maze Paths\t"+ result);
        return result;
    }
    // ADD METHOD FOR PROBLEM 3 HERE
//    method to find the minimum path from all possible maze path
    public ArrayList<PairInt> findMazePathMin(int x, int y)
    {
        int min=Integer.MAX_VALUE;
        ArrayList<PairInt> minPath=null;
        ArrayList<ArrayList<PairInt>> pointList = findAllMazePaths(x,y);
        for(ArrayList<PairInt> z:pointList)
        {
            if(z.size()<min)
            {
                min=z.size();
                minPath=z;

            }
        }
        System.out.println("min Path is"+minPath);
        return minPath;
    }

    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/
