package Orienting;
/*
* Triangular_Grid extends Grid_Orienting_Solver and its solve method returns a mending solution for the frontier passed as an argument and the valid indegrees.
* */
public class Triangular_Grid extends Grid_Orienting_Solver{

    //For the frontier 0 means pointing towards the outside graph and 1 means pointing towards the hole.
    //For the edges within the hole, orientation[k]=1 means edge between vertices k and k-1 is oriented from k-1 to k.

    private static int[] computeHole(int[] frontier, int[] orientation){
        int[] hole = new int[3];
        for (int i = 0; i < 3; i++) {
            hole[i] = orientation[i] + (orientation[(i+1)% orientation.length] == 1 ? 0:1);
            for (int j = 0; j < 4; j++) {
                hole[i] += frontier[i*4 + j];
            }
        }
        return hole;
    }

    @Override
    public int[] solve(int[] frontier){
        int[] orientation = new int[3];
        int max = 2;
        do{
            int[] hole = computeHole(frontier, orientation);
            if(isValidHole(hole))
                return orientation;
        }while (increment(orientation, 0, max).length > 1);
        return new int[0];
    }
}
