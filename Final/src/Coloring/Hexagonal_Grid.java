package Coloring;

public class Hexagonal_Grid extends Grid_Coloring_Solver {

    private static int c = 3;//number of colors

    @Override
    boolean isValidFrontier(int[] frontier) {
        return true;
    }

    @Override
    public boolean isValidHole(int[] hole) {
        for (int i = 0; i < hole.length-1; i++) {
            if(hole[i] == hole[i+1]) return false;
        }
        return hole[0] != hole[hole.length - 1];
    }

    @Override
    public int[] solve(int[] frontier) {
        int[][] possible = new int[6][2];
        if(frontier.length>6){
            System.err.println("invalid length");
        }
        for (int i = 0; i < frontier.length; i++) {
            int k = 0;
            for (int j = 0; j < c; j++) {
                if(j != frontier[i]){
                    possible[i][k] = j;
                    k += 1;
                }
            }
        }
        int[] indices = new int[6];

        do{
            int[] hex = new int[6];
            for (int i = 0; i < hex.length; i++) {
                hex[i] = possible[i][indices[i]];
            }
            if(isValidHole(hex)) return hex;
        }while (increment(indices, 0, 2).length > 1);
        return new int[0];
    }
}
