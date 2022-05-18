package Orienting;

import Solver.Grid_Solver;

import java.util.HashSet;
import java.util.Set;

public abstract class Grid_Orienting_Solver extends Grid_Solver {

    /**
     * Valid inbound degrees management
     * */
    private Set<Integer> valid_Indegrees = new HashSet<>();

    public void addInboundDegree(int degree){
        valid_Indegrees.add(degree);
    }

    public boolean isValidHole(int[] hole){
        for (int i : hole){
            if(!valid_Indegrees.contains(i))
                return false;
        }
        return true;
    }
}
