package Coloring;

import Solver.Grid_Solver;
/*
* This class extends Grid _Solver by adding one more abstract method that checks whether the frontier is valid (2 adjacent nodes in the frontier can’t have the same color).
* */
public abstract class Grid_Coloring_Solver extends Grid_Solver {

    /**
     * Problem specific methods
     * */
    abstract boolean isValidFrontier(int[] frontier);

}
