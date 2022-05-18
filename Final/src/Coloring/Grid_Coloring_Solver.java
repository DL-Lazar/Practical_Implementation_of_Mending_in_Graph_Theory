package Coloring;

import Solver.Grid_Solver;

public abstract class Grid_Coloring_Solver extends Grid_Solver {

    /**
     * Problem specific methods
     * */
    abstract boolean isValidFrontier(int[] frontier);

}
