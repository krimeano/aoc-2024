package krimeano.aoc2024.days.day12;

import java.util.*;

public class Day12x2 extends Day12x1 {
    protected static final String NESW = "NESW";
    protected static final int[][][] SIDE_DIRECTIONS = {
            {{0, 1}, {0, -1}},
            {{1, 0}, {-1, 0}}
    };

    public Day12x2(boolean verbose) {
        super(verbose);
    }

    @Override
    protected int calculateFences(HashMap<List<Integer>, HashSet<Character>> area) {
        if (verbose) {
            System.out.println(area);
        }
        HashSet<List<Integer>> remainedFences = new HashSet<>();
        for (List<Integer> xy : area.keySet()) {
            HashSet<Character> sides = area.get(xy);
            for (char side : sides) {
                remainedFences.add(Arrays.asList(xy.getFirst(), xy.getLast(), NESW.indexOf(side)));
            }

        }
        if (verbose) {
            System.out.println(remainedFences);
        }
        ArrayList<List<Integer>> fencesToVisit = new ArrayList<>();
        List<Integer> current;

        int numberOfSides = 0;
        while (!remainedFences.isEmpty()) {
            current = remainedFences.iterator().next();
            remainedFences.remove(current);
            fencesToVisit.add(current);

            while (!fencesToVisit.isEmpty()) {
                current = fencesToVisit.removeFirst();
                for (List<Integer> fence : getNeighbourFences(current)) {
                    if (remainedFences.contains(fence)) {
                        fencesToVisit.add(fence);
                        remainedFences.remove(fence);
                    }
                }
            }

            numberOfSides++;
        }

        return numberOfSides;
    }

    public ArrayList<List<Integer>> getNeighbourFences(List<Integer> current) {
        int dirIx = current.getLast() % 2;
        int[][] dirs = SIDE_DIRECTIONS[dirIx];
        ArrayList<List<Integer>> out = new ArrayList<>();
        for (int[] dir : dirs) {
            out.add(Arrays.asList(current.getFirst() + dir[0], current.get(1) + dir[1], current.getLast()));
        }
        return out;
    }
}
