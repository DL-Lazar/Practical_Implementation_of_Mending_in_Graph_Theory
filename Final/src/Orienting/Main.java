package Orienting;

import java.util.Arrays;

import static Solver.Grid_Solver.increment;

public class Main {
    public static void main(String[] args) {
        /**
         * Hexagonal grid coloring
         * */
        solve_Hexagonal_Grid_Orienting();

        /**
         * Triangular grid coloring
         * */
        solve_Triangular_Grid_Orienting();

        /**
         * Octagonal grid coloring
         * */
        solve_Octogonal_Grid_Orienting();
    }

    private static void solve_Hexagonal_Grid_Orienting() {
        Grid_Orienting_Solver solver = new Hexagonal_Grid();

        solver.addInboundDegree(0);
        solver.addInboundDegree(1);
        solver.addInboundDegree(2);
        solver.addInboundDegree(3);


        int[] frontier = new int[6];

        do {
            int[] sol = solver.solve(frontier);
            if(sol.length > 1)
                solver.addFrontierMending(frontier.clone(), sol);
            else System.err.println("No solution for frontier: " + Arrays.toString(frontier));
        }while (increment(frontier, 0, 2).length > 0);
        System.out.println("Solved successfully!");
//        Grid_Solver.writeToFile("hexagonal_Orientation.txt", solver.solutionsToString());

    }

    private static void solve_Triangular_Grid_Orienting(){
        Grid_Orienting_Solver solver = new Triangular_Grid();

        solver.addInboundDegree(0);//can be removed
        solver.addInboundDegree(1);//remove this and we have no solution for frontier = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
        solver.addInboundDegree(2);//remove this and we have no solution for frontier = [1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0]
        solver.addInboundDegree(3);//remove this and we have no solution for frontier = [1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0]
        solver.addInboundDegree(4);//remove this and we have no solution for frontier = [1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0]
        solver.addInboundDegree(5);//remove this and we have no solution for frontier = [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]
        solver.addInboundDegree(6);//can be removed

        int[] frontier = new int[12];
        int max = 2;
        do {
            int[] sol = solver.solve(frontier);
            if(sol.length > 1)
                solver.addFrontierMending(frontier.clone(), sol);
            else {
                System.err.println("no solution for frontier = " + Arrays.toString(frontier));
                return;
            }
        }while (increment(frontier, 0, max).length > 1);
        System.out.println("Solved successfully!");
//        Grid_Solver.writeToFile("triangular_Orientation.txt", solver.solutionsToString());
    }

    private static void solve_Octogonal_Grid_Orienting() {
        Grid_Orienting_Solver solver = new Octogonal_Grid();

        solver.addInboundDegree(0);//can be removed
        solver.addInboundDegree(1);//can be removed
        solver.addInboundDegree(2);//can be removed
        solver.addInboundDegree(3);//can be removed
        solver.addInboundDegree(4);//can be removed
        solver.addInboundDegree(5);//can be removed
        solver.addInboundDegree(6);//can be removed
        solver.addInboundDegree(7);//can be removed
        solver.addInboundDegree(8);//can be removed
        //But not all at once, The reader is encouraged to comment the in-degrees according to his specific needs to see if the problem is 1-mendable.
        int[] frontier = new int[20];
        int max = 2;
        do {
            int[] sol = solver.solve(frontier);
            if(sol.length > 1)
                solver.addFrontierMending(frontier.clone(), sol);
            else {
                System.err.println("no solution for frontier = " + Arrays.toString(frontier));
                return;
            }
        }while (increment(frontier, 0, max).length > 1);
        System.out.println("Solved successfully!");
//        Grid_Solver.writeToFile("octagonal_Orientation.txt", solver.solutionsToString());
    }
}
