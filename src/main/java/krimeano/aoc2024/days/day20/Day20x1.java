package krimeano.aoc2024.days.day20;

import krimeano.aoc2024.days.my_lib.SolveDay;

import java.util.*;

public class Day20x1 extends SolveDay {
    protected static final int CHEAT_LIMIT = 2;
    protected static final char START = 'S';
    protected static final char END = 'E';
    protected static final char WALL = '#';
    protected static final char SPACE = '.';
    protected static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // >, v, <, ^
    protected Map<List<Integer>, Cell> cells;
    protected List<String> racetrack;
    protected int size;
    protected List<Integer> start;
    protected List<Integer> end;

    public Day20x1(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        return solve(textInput, 100);
    }

    public int solve(String textInput, int threshold) {
        init(textInput);
        findPath();
        return findCheats(threshold);
    }

    protected void init(String textInput) {
        racetrack = getLines(textInput);
        size = racetrack.size();
        int maxSteps = size * size;
        cells = new HashMap<>();
        Cell cell;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                char c = racetrack.get(i).charAt(j);
                List<Integer> index = Arrays.asList(i, j);
                if (c == START) {
                    cell = new Cell(index);
                    start = index;
                } else {
                    cell = new Cell(index, maxSteps);
                }

                if (c != WALL) {
                    cells.put(index, cell);
                    if (c == END) {
                        end = index;
                    }
                }

            }
        }
    }


    protected void findPath() {
        Set<List<Integer>> wave = new HashSet<>();
        wave.add(start);

        while (!wave.isEmpty()) {
            Set<List<Integer>> newWave = new HashSet<>();
            for (List<Integer> current : wave) {
                Cell currentCell = cells.get(current);
                for (int[] direction : DIRECTIONS) {
                    List<Integer> next = Arrays.asList(current.get(0) + direction[0], current.get(1) + direction[1]);
                    Cell cell = cells.get(next);
                    if (cell != null && cell.moveFrom(currentCell, 1)) {
                        newWave.add(next);
                    }
                }
            }
            wave = newWave;
        }
    }

    protected int findCheats(int threshold) {
        return findCheats(threshold, CHEAT_LIMIT);
    }

    protected int findCheats(int threshold, int cheatLimit) {
        int endScore = cells.get(end).score;
        if (verbose) {
            System.out.println("endScore: " + endScore);
        }
        int foundCheats = 0;
        System.out.println("cheatLimit: " + cheatLimit);
        for (Cell cell : cells.values()) {
            for (int i = -cheatLimit; i <= cheatLimit; i++) {
                int orthogonalLimit = cheatLimit - Math.abs(i);
                for (int j = -orthogonalLimit; j <= orthogonalLimit; j++) {
                    int cheatSize = Math.abs(i) + Math.abs(j);
                    int row = cell.index.get(0) + i;
                    int col = cell.index.get(1) + j;
                    Cell nextCell = cells.get(Arrays.asList(row, col));
                    if (nextCell == null) {
                        continue;
                    }

                    int cheatScore = nextCell.score - cell.score - cheatSize;

                    if (cheatScore >= threshold) {
                        if (verbose) {
                            System.out.println("CHEAT: " + cell.index + " > " + i + ", " + j + " > " + nextCell.index + " = " + cheatScore);
                        }
                        foundCheats++;
                    }
                }
            }
        }
        return foundCheats;
    }
}
