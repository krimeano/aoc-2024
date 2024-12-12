package krimeano.aoc2024.days.day12;

import krimeano.aoc2024.days.my_lib.OutsideException;
import krimeano.aoc2024.days.my_lib.SolveDay;

import java.util.*;

public class Day12x1 extends SolveDay {
    protected static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    protected ArrayList<String> lines;
    protected int height;
    protected int width;
    protected HashSet<List<Integer>> notVisitedCells;
    /* list of areas, where key is coordinates and value is a fence length in this cell */
    protected ArrayList<HashMap<List<Integer>, Integer>> areas;

    public Day12x1(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        lines = getLines(textInput);
        height = lines.size();
        width = height > 0 ? lines.getFirst().length() : 0;
        notVisitedCells = new HashSet<>();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                notVisitedCells.add(Arrays.asList(i, j));
            }
        }

        areas = new ArrayList<>();

        HashMap<List<Integer>, Integer> area;
        List<Integer> cell;
        ArrayList<List<Integer>> currentCells;
        char currentValue;
        int fences;
        int perimeter;
        int result = 0;
        while (!notVisitedCells.isEmpty()) {
            area = new HashMap<>();
            cell = notVisitedCells.iterator().next();
            notVisitedCells.remove(cell);
            currentCells = new ArrayList<>();
            currentCells.add(cell);
            currentValue = lines.get(cell.getFirst()).charAt(cell.getLast());
            perimeter = 0;

            while (!currentCells.isEmpty()) {
                fences = 0;

                cell = currentCells.removeFirst();

                if (cell.getFirst() == 0 || cell.getFirst() == height - 1) {
                    fences += 1;
                }

                if (cell.getLast() == 0 || cell.getLast() == width - 1) {
                    fences += 1;
                }


                for (List<Integer> neighbour : getNeighbours(cell)) {
                    if (lines.get(neighbour.getFirst()).charAt(neighbour.getLast()) == currentValue) {
                        if (notVisitedCells.contains(neighbour)) {
                            notVisitedCells.remove(neighbour);
                            currentCells.add(neighbour);
                        }
                    } else {
                        fences++;
                    }
                }

                area.put(cell, fences);
                perimeter += fences;
            }
            areas.add(area);
            result += area.size() * perimeter;
            if (verbose) {
                System.out.println("Area " + currentValue + ": " + area);
            }
        }

        return result;
    }

    protected ArrayList<List<Integer>> getNeighbours(List<Integer> xy) {
        ArrayList<List<Integer>> neighbours = new ArrayList<>();
        for (int[] direction : DIRECTIONS) {
            try {
                neighbours.add(getNeighbour(xy, direction));
            } catch (OutsideException e) {
                continue;
            }
        }
        return neighbours;
    }

    protected List<Integer> getNeighbour(List<Integer> xy, int[] direction) throws OutsideException {
        int x = xy.getFirst() + direction[0];
        int y = xy.getLast() + direction[1];
        if (x < 0 || y < 0 || x >= height || y >= width) {
            throw new OutsideException();
        }
        return Arrays.asList(x, y);
    }

}
