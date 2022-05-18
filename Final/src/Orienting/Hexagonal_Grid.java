package Orienting;

public class Hexagonal_Grid extends Grid_Orienting_Solver{

    @Override
    public int[] solve(int[] frontier){
        int[] orientation = new int[frontier.length];//orientation[k]=1 means edge between vertices k and k-1 is oriented from k-1 to k.
                                                     //orientation[k]=0 means edge between vertices k and k-1 is oriented from k to k-1.
        int[] hole = new int[frontier.length];//hole[k] = incoming nr of edges in node k

        do{
            for (int i = 0; i < frontier.length; i++) {
                hole[i] = frontier[i] + orientation[i] + (orientation[(i+1)% frontier.length] == 1 ? 0:1);
            }
            if(isValidHole(hole))
                return orientation;
        }while (increment(orientation, 0, 2).length > 1);
        return new int[0];
    }

}
