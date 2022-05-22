package Orienting;

import java.util.HashSet;
import java.util.Set;
/*
* Octogonal_Grid extends Grid_Orienting_Solver and its solve method returns a mending solution for the frontier passed as an argument and the valid indegrees.
* */
public class Octogonal_Grid extends Grid_Orienting_Solver {
    //For the frontier 0 means pointing towards the outside graph and 1 means pointing towards the hole.
    //For the edges within the hole, orientation[k]=1 means edge between vertices k and k-1 is oriented from k-1 to k.

    private static int[] computeHole(int[] frontier, int[] orientation){
        int[] hole = new int[4];
        for (int i = 0; i < hole.length; i++) {
            hole[i] = orientation[i] + (orientation[(i+1)% hole.length] == 1 ? 0:1);
            Set<Integer> increments = new HashSet<>();
            increments.add(0);
            increments.add(2);
            increments.add(3);
            increments.add(4);
            increments.add(6);

            for (int j : increments) {
                hole[i] += frontier[(5*i + j)% frontier.length];
            }

        }

        if(orientation[4] == 1) hole[1] += 1;
        else hole[3] += 1;

        if(orientation[5] == 1) hole[0] += 1;
        else hole[2] += 1;

        return hole;
    }
    @Override
    public int[] solve(int[] frontier){
        int[] orientation = new int[6];
        int max = 2;
        do{
            int[] hole = computeHole(frontier, orientation);
            if(isValidHole(hole))
                return orientation;
        }while (increment(orientation, 0, max).length > 1);
        return new int[0];
    }
}
