package Coloring;

import java.util.*;
/*
* Octogonal_Grid extends Grid_Coloring_Solver and its solve method returns a mending solution for the frontier passed as an argument.
* */
public class Octogonal_Grid extends Grid_Coloring_Solver {
    private static Map<int[], int[]> mending = new HashMap<>();
    private static int c = 8;

    private static void recursive_Combinations(List<int[]> combinations, int data[], int start, int end, int index) {
        if (index == data.length) {
            int[] combination = data.clone();
            combinations.add(combination);
        } else if (start <= end) {
            data[index] = start;
            recursive_Combinations(combinations, data, start + 1, end, index + 1);
            recursive_Combinations(combinations, data, start + 1, end, index);
        }
    }

    static List<int[]> generate_Combinations(int n, int r) {
        List<int[]> combinations = new ArrayList<>();
        recursive_Combinations(combinations, new int[r], 0, n-1, 0);
        return combinations;
    }

    static void permute_All(List<int[]> combinations,  List<List<Integer>> rec){
        for (int i = 0; i < combinations.size(); i++) {
            List<Integer> tmpList = new ArrayList<>();
            for (int j : combinations.get(i)) {
                tmpList.add(j);
            }
            permute(tmpList, new ArrayList<>(), rec);
        }
    }

    static void permute(List<Integer> elements, List<Integer> current, List<List<Integer>> rec){
        if(elements.size() == 0) {
            rec.add(current);
            return;
        }
        for (int i = 0; i < elements.size(); i++) {
            List<Integer> left = elements.subList(0,i);
            List<Integer> right = elements.subList(i+1, elements.size());
            int elem = elements.get(i);

            List<Integer> res = new ArrayList<>();
            res.addAll(left);
            res.addAll(right);
            current.add(elem);
            permute(res, current, rec);

        }
    }

    //I strongly recommend running the solve method in a distributed manner instead of computing all mendings in a file for this problem:
    //The later approach may take too long and too much memory on an average PC at the time this work was done.
    @Override
    public int[] solve(int[] frontier){
        int[][] possible = new int[8][];
        for (int i = 0; i < possible.length; i++) {
            Set<Integer> pos;
            if(i%2 == 0){
                possible[i] = new int[3];
                pos = getPossible3(frontier, i);
            }else {
                possible[i] = new int[5];
                pos = getPossible5(frontier, i);
            }
            int k = 0;
            if(pos.size() > possible[i].length){
                int[] sol = {i};//this means we can use a side as a sink to greedily solve it as explained in the observations.
                return sol;
            }
            for (int j: pos) {
                possible[i][k++] = j;
            }
        }

        int[] max = {3,5,3,5,3,5,3,5};
        int[] idx = new int[8];
        int[] hole = new int[8];

        do{
            for (int i = 0; i < 8; i++) {
                hole[i] = possible[i][idx[i]];
            }
            if(isValidHole(hole))
                return hole;
        }while (increment(idx, 0, max).length > 1);
        System.err.println("impossible to solve for frontier " + Arrays.toString(frontier));
        return new int[0];

    }

    public static Set<Integer> getPossible3(int[] frontier, int idx){
        Set<Integer> pos = new HashSet<>();
        for (int i = 0; i < 8; i++) {
            pos.add(i);
        }
        for (int i = 0; i < 5; i++) {
            pos.remove(frontier[(i + 2*idx)% frontier.length]);
        }
        return pos;
    }

    public static Set<Integer> getPossible5(int[] frontier, int idx){
        Set<Integer> pos = new HashSet<>();
        for (int i = 0; i < 8; i++) {
            pos.add(i);
        }
        for (int i = 0; i < 3; i++) {
            pos.remove(frontier[(idx*2 + 1 + i)% frontier.length]);
        }
        return pos;
    }

    @Override
    boolean isValidFrontier(int[] frontier) {
        for (int i = 0; i < 8; i++) {
            if((i%2 == 1) && getPossible5(frontier, i).size() > 5)
                return false;
        }
        return true;
    }
    @Override
    public boolean isValidHole(int[] hole){
        Set<Integer> diff = new HashSet<>();
        for (int i = 0; i < hole.length; i++) {
            if(hole[i] == hole[(i+1)% hole.length] ||
                    (hole[i] == hole[(i+2)% hole.length] && (i%2 == 1)))
                return false;
            diff.add(hole[i]);
        }
        return diff.size() < hole.length;
    }
}
