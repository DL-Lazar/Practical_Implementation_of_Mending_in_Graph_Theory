package Orienting;

import Solver.Grid_Solver;

import java.util.HashSet;
import java.util.Set;
/*
* This class extends Grid_Solver by adding inbound degree management methods and attributes.
* */
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
