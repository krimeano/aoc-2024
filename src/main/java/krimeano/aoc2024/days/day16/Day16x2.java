package krimeano.aoc2024.days.day16;

import java.util.*;

public class Day16x2 extends Day16x1 {
    protected Map<List<Integer>, Set<List<Integer>>> cameFrom;

    public Day16x2(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        cameFrom = new HashMap<>();
        super.solve(textInput);
        cameFrom.put(startingPoint, new HashSet<>());
        return countPath();
    }

    @Override
    protected boolean compareScore(List<Integer> prevXyz, List<Integer> xyz, Integer score) {
        Integer oldScore = vectorScores.get(xyz);
        if (verbose) {
            System.out.println(prevXyz + " > " + xyz + " = " + score + ", WAS " + oldScore);
        }
        if (oldScore >= score) {
            Set<List<Integer>> cells = oldScore.equals(score)
                    ? cameFrom.getOrDefault(xyz, new HashSet<>())
                    : new HashSet<>();
            cells.add(prevXyz);
            cameFrom.put(xyz, cells);
            vectorScores.put(xyz, score);
            return true;
        }
        return false;
    }

    protected int countPath() {
        Set<List<Integer>> visited = new HashSet<>();
        int endMinScore = getMinEndScore();
        List<List<Integer>> currentCells = new ArrayList<>();
        for (List<Integer> endPoint : endPoints) {
            if (endMinScore == vectorScores.get(endPoint)) {
                currentCells.add(endPoint);
            }
        }

        while (!currentCells.isEmpty()) {
            List<Integer> currentCell = currentCells.removeFirst();
            visited.add(currentCell);
            if (verbose) {
                System.out.print(currentCell + " < ");
            }

            for (List<Integer> cell : cameFrom.get(currentCell)) {
                if (verbose) {
                    System.out.print(cell + "; ");
                }

                if (!visited.contains(cell)) {
                    currentCells.add(cell);
                }
            }
            if (verbose) {
                System.out.println();
            }
        }

        Set<List<Integer>> result = new HashSet<>();
        for (List<Integer> xyz : visited) {
            result.add(Arrays.asList(xyz.get(0), xyz.get(1)));
        }

        return result.size();
    }
}
