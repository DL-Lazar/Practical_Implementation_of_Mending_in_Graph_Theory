package Coloring;

import java.util.*;
/*
* Triangular_Grid extends Grid_Coloring_Solver and its solve method returns a mending solution for the frontier passed as an argument.
* */
public class Triangular_Grid extends Grid_Coloring_Solver {

    private static Map<int[], int[]> mending = new HashMap<>();
    private static int c = 6;//cu 6 merge !!! dar nu cu 5

    @Override
    boolean isValidFrontier(int[] frontier) {
        for (int i = 0; i < frontier.length-1; i++) {
            if(frontier[i] == frontier[i+1]) return false;
        }
        return frontier[0] != frontier[frontier.length - 1];
    }

    @Override
    public boolean isValidHole(int[] hole) {
        return !(hole[0] == hole[1] ||
                hole[1] == hole[2] ||
                hole[2] == hole[3] ||
                hole[3] == hole[4] ||
                hole[4] == hole[5] ||
                hole[5] == hole[0] ||
                //middle 3
                hole[1] == hole[3] ||
                hole[3] == hole[5] ||
                hole[5] == hole[1]);
    }

    @Override
    public int[] solve(int[] frontier) {
        int[][] possible = new int[6][];
        if(frontier.length>12){
            System.err.println("invalid length");
        }
        //external constraints from the frontier
        for (int i = 0; i < possible.length; i++) {
            Set<Integer> pos;
            if(i%2 == 0){
                pos = getPossible4(frontier, i);
                possible[i] = new int[pos.size()];
            }else {
                pos = getPossible2(frontier, i);
                possible[i] = new int[pos.size()];
            }
            int j = 0;
            for(int k : pos){
                possible[i][j++] = k;
            }
        }

        //init internal constraints from within the hole
        int[] max  = new int[6];
        for (int i = 0; i < max.length; i++) {
            max[i] = possible[i].length;
        }
        int[] indices = new int[6];
        for (int i = 0; i < 6; i++) {
            indices[i] = 0;
        }

        do{
            int[] hole = new int[6];
            for (int i = 0; i < hole.length; i++) {
                if(indices[i] > possible[i].length-1){
                    System.err.println("i = " + i + " indices = " + Arrays.toString(indices) + " max = " + Arrays.toString(max) +" pos length = " + possible[i].length);
                    return null;
                }
                hole[i] = possible[i][indices[i]];
            }
            if(isValidHole(hole)) return hole;
        }while (increment(indices, 0, max).length > 1);

        return new int[0];
    }


    public static int[][] possibleFrontiers(){
        int[][] possible = new int[12][];
        possible[0] = new int[]{0};
        possible[1] = new int[]{1};

        for (int i = 3; i < 11; i++) {
            possible[i] = new int[c];
            for (int j = 0; j < c; j++) {
                possible[i][j] = j;
            }
        }

        possible[2] = new int[c-1];
        possible[11] = new int[c-1];
        for (int i = 1; i < c; i++) {
            possible[11][i-1] = i;
        }
        for (int i = 2; i < c+1; i++) {
            possible[2][i-2] = i;
        }
        possible[2][c-2] = 0;
        return possible;
    }

    //We suppose that R has always color 0 and G color 1 (else just go use bijection...) //R has index 0

    private static Set<Integer> getPossible4(int[] frontier, int idx){
        Set<Integer> possible = new HashSet<>();
        for (int i = 0; i < c; i++) {
            if(frontier[2*idx] != i && frontier[2*idx + 1] != i && frontier[2*idx + 2] != i && frontier[2*idx + 3] != i)
                possible.add(i);
        }
        return possible;
    }
    private static Set<Integer> getPossible2(int[] frontier, int idx){
        Set<Integer> possible = new HashSet<>();
        for (int i = 0; i < c; i++) {
            if(frontier[2*idx + 1] != i && frontier[(2*idx + 2)% frontier.length] != i)
                possible.add(i);
        }
        return possible;
    }
}
