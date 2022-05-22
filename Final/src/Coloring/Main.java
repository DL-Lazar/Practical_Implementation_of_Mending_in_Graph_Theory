package Coloring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static Coloring.Octogonal_Grid.generate_Combinations;
import static Coloring.Octogonal_Grid.permute_All;
import static Coloring.Triangular_Grid.possibleFrontiers;

//The Main class in this package runs the solve methods of all grids for all possible frontiers.

public class Main {
    public static void main(String[] args) {

        /**
         * Hexagonal grid coloring
         * */
        solve_Hexagonal_Grid_Coloring();

        /**
         * Triangular grid coloring
         * */
        solve_Triangular_Grid_Coloring();

        /**
         * Octagonal grid coloring
         * */
        solve_Octogonal_Grid_Coloring();

    }

    private static void solve_Hexagonal_Grid_Coloring(){
        Grid_Coloring_Solver solver = new Hexagonal_Grid();

        int[] frontier = new int[6];
        for (int i = 0; i < 6; i++) {
            frontier[i] = 0;
        }
        int idx = 0;
        do {
            idx ++;
            int[] sol = solver.solve(frontier);
            if(sol.length > 1){
//                System.out.println("frontier: ");
                System.out.println(Arrays.toString(frontier));
//                System.out.println("solution: ");
                System.out.println(Arrays.toString(sol));
                System.out.println("----------------");
                solver.addFrontierMending(frontier, sol);
            }else {
                System.err.println("this frontier has no solutions: ");
                System.err.println(Arrays.toString(frontier));
            }
        }while (Grid_Coloring_Solver.increment(frontier, 0, 3).length > 1);
//        System.out.println("total = " + idx);
        System.out.println("Solved successfully!");
    }

    private static void solve_Triangular_Grid_Coloring(){
        Grid_Coloring_Solver solver = new Triangular_Grid();
        int c = 6;
        int[] frontier = new int[12];
        int[] max = new int[frontier.length];
        max[0] = 1;
        max[1] = 1;
        max[2] = c-1;
        max[11] = c-1;
        for (int i = 3; i < 11; i++) {
            max[i] = c;
        }
        Arrays.fill(frontier, 0);
        frontier[1] = 1;
    //init possible
        int[][] possible = possibleFrontiers();
        int[] indices = new int[12];
        Arrays.fill(indices, 0);

        do{
            for (int i = 0; i < frontier.length; i++) {
                frontier[i] = possible[i][indices[i]];
            }
            if(solver.isValidFrontier(frontier)){
                int[] solution = solver.solve(frontier);
                if(solution != null && solution.length > 1){
                    solver.addFrontierMending(frontier.clone(), solution.clone());
                }else{
                    System.err.println("No solution for frontier " + Arrays.toString(frontier));
                }
            }
        }while (Grid_Coloring_Solver.increment(indices, 0, max).length > 1);
        System.out.println("Solved successfully!");
//        Grid_Coloring_Solver.writeToFile("Triangle_Solutions.txt", solver.solutionsToString());
    }


    public static void solve_Octogonal_Grid_Coloring() {
        Grid_Coloring_Solver solver = new Octogonal_Grid();

        List<int[]> index_Combinations4 = generate_Combinations(7,4);
        List<int[]> index_Combinations3_6 = generate_Combinations(6,3);
        List<int[]> index_Combinations3_7 = generate_Combinations(7,3);

        List<List<Integer>> index_Permutations4 = new ArrayList<>();
        List<List<Integer>> index_Permutations3_6 = new ArrayList<>();
        List<List<Integer>> index_Permutations3_7 = new ArrayList<>();

        permute_All(index_Combinations4, index_Permutations4);
        permute_All(index_Combinations3_6, index_Permutations3_6);
        permute_All(index_Combinations3_7, index_Permutations3_7);

        int[] convert1_0 = {7,1,2,3,4,5,6};
        int[] convert1_5 = {0,1,2,3,7,5,6};

        int[] frontier = new int[16];

        frontier[0] = 0;
        frontier[1] = 1;
        frontier[2] = 2;
        frontier[3] = 3;
        frontier[4] = 4;
        //fix the labels of the green area
        int x = 0;
//        long time_start = System.currentTimeMillis();
        for (int i = 0; i < index_Permutations4.size(); i++) {
            for (int j = 0; j < index_Permutations4.size(); j++) {
                for (int k = 0; k < 4; k++) {
                    frontier[5+k]=convert1_5[index_Permutations4.get(i).get(k)];
                    frontier[12+k]=convert1_0[index_Permutations4.get(i).get(k)];
                }
                //create all permutations of the blue areas
//                long time1 = System.currentTimeMillis();
                List<Integer> convert2 = new ArrayList<>();

                for (int k = 0; k < 8; k++) {
                    if(frontier[8] != k && frontier[12] != k){
                        convert2.add(k);
                    }
                }

                //create all permutation of the orange area
                if(convert2.size() == 6){
                    for (int k = 0; k < index_Permutations3_6.size(); k++) {
                        for (int l = 0; l < 3; l++) {
                            frontier[9+l] = convert2.get(index_Permutations3_6.get(k).get(l));
                        }
                        if(solver.isValidFrontier(frontier)){
                            int[] hole = solver.solve(frontier);
                            if(hole.length > 0){
                                solver.addFrontierMending(frontier, hole.clone());
                                x++;
                            }else {
                                System.err.println("unable to solve this one");
                                return;
                            }
                        }

                    }
                }else {//when frontier[8] == frontier[12]
                    for (int k = 0; k < index_Permutations3_7.size(); k++) {
                        for (int l = 0; l < 3; l++) {
                            frontier[9+l] = convert2.get(index_Permutations3_7.get(k).get(l));
                        }
                        if(solver.isValidFrontier(frontier)){
                            int[] hole = solver.solve(frontier);
                            if(hole.length > 0){
                                solver.addFrontierMending(frontier, hole.clone());
                                x++;
                            }else {
                                System.err.println("unable to solve this one");
                                return;
                            }
                        }
                    }
                }
//                System.out.println("solved in " + (time2-time1) + " ms " + x);
            }
        }
        System.out.println("Solved successfully!");
//        System.out.println("Number of cases solved= " + x);
//        System.out.println("total time = " + (System.currentTimeMillis() - time_start)/1000 + " seconds");
    }
}
