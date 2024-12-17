package krimeano.aoc2024.days.day16;

import java.util.*;

public class Day16x2 extends Day16x1 {
    protected HashMap<List<Integer>, HashSet<List<Integer>>> cameFrom;

    public Day16x2(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        cameFrom = new HashMap<>();
        super.solve(textInput);
        cameFrom.put(Arrays.asList(startingPoint.get(0), startingPoint.get(1)), new HashSet<>());
        return countPath();
    }

    @Override
    protected void compareScalarScore(int x0, int y0, int x, int y, Integer score) {
        List<Integer> xy = Arrays.asList(x, y);
        int oldScore = scalarScores.get(xy);
        System.out.println(x0 + "," + y0 + " > " + x + "," + y + " = " + score + ", WAS " + oldScore);

        if (oldScore >= score) {
            HashSet<List<Integer>> cells;

            if (oldScore > score) {
                scalarScores.put(xy, score);
                cells = new HashSet<>();
            } else {
                cells = cameFrom.getOrDefault(xy, new HashSet<>());
            }

            cells.add(Arrays.asList(x0, y0));

            cameFrom.put(xy, cells);
        }
    }

    protected int countPath() {
        Set<List<Integer>> visited = new HashSet<>();
        ArrayList<List<Integer>> currentCells = new ArrayList<>();
        currentCells.add(endPoint);

        while (!currentCells.isEmpty()) {
            List<Integer> currentCell = currentCells.removeFirst();

            visited.add(currentCell);
            System.out.print(currentCell + " < ");

            for (List<Integer> cell : cameFrom.get(currentCell)) {
                System.out.print(cell + "; ");

                if (!visited.contains(cell)) {
                    currentCells.add(cell);
                }
            }
            System.out.println();
        }
        return visited.size();
    }
}
