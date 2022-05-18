package Solver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public abstract class Grid_Solver {
    /**
     * Problem specific methods
     * */
    protected abstract boolean isValidHole(int[] hole);
    public abstract int[] solve(int[] frontier);

    /**
     * To try all possibilities,
     * This recursively increments an array.
     * */
    public static int[] increment(int[] crt, int idx, int[] max){
        if(idx > crt.length-1){
            return new int[0];
        }
        if(crt[idx] == max[idx]-1){
            crt[idx] = 0;
            return increment(crt, idx +1,max);
        }
        crt[idx] += 1;
        return crt;
    }

    public static int[] increment(int[] crt, int idx, int max){
        if(idx > crt.length-1){
            return new int[0];
        }
        if(crt[idx] == max-1){
            crt[idx] = 0;
            return increment(crt, idx +1,max);
        }
        crt[idx] += 1;
        return crt;
    }


    /**
     * Mendings
     * */
    private Map<int[], int[]> mending = new HashMap<>();

    public void addFrontierMending(int[] frontier, int[] mend){
        mending.put(frontier, mend);
    }

    //Converts the mending map to a human-readable format
    public String solutionsToString(){
        StringBuilder sb = new StringBuilder();
        for (int[] f : mending.keySet()){
            sb.append("frontier: " + Arrays.toString(f) + "\n");
            sb.append("solution: " + Arrays.toString(mending.get(f)) + "\n");
            sb.append("\n");
        }
        return sb.toString();
    }

    //Efficiently writes to file
    public static void writeToFile(String FILE_NAME, String content){
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(FILE_NAME), 32768);
            out.write(content);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
