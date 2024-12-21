package krimeano.aoc2024.days.day20;

import krimeano.aoc2024.days.my_lib.SolveDay;

import java.util.*;

public class Day20x1 extends SolveDay {
    static final char START = 'S';
    static final char END = 'E';
    static final char WALL = '#';
    static final char SPACE = '.';
    static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // >, v, <, ^
    Map<List<Integer>, Cell> cells;
    Map<List<Integer>, Cell> walls;
    List<String> racetrack;
    int size;

    List<Integer> start;
    List<Integer> end;

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
        walls = new HashMap<>();
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

                if (c == WALL) {
                    if (canBeCheated(i, j)) {
                        walls.put(index, cell);
                    }
                } else {
                    cells.put(index, cell);
                    if (c == END) {
                        end = index;
                    }
                }

            }
        }
    }

    protected boolean canBeCheated(int row, int col) {
        if (row == 0 || row == size - 1 || col == 0 || col == size - 1 || racetrack.get(row).charAt(col) != WALL) {
            return false;
        }

        for (int[] direction : DIRECTIONS) {
            if (racetrack.get(row + direction[0]).charAt(col + direction[1]) == WALL) {
                return true;
            }
        }
        return false;
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
        int endScore = cells.get(end).score;
        if (verbose) {
            System.out.println("endScore: " + endScore);
        }
        int foundCheats = 0;
        for (Cell cell : cells.values()) {
            for (int[] direction : DIRECTIONS) {
                Cell nextWall = walls.get(Arrays.asList(cell.index.get(0) + direction[0], cell.index.get(1) + direction[1]));
                if (nextWall == null) {
                    continue;
                }
                for (int[] cheatDirection : DIRECTIONS) {
                    Cell nextCell = cells.get(Arrays.asList(nextWall.index.get(0) + cheatDirection[0], nextWall.index.get(1) + cheatDirection[1]));
                    if (nextCell == null) {
                        continue;
                    }
                    int cheatScore = nextCell.score - cell.score - 2;
                    if (cheatScore >= threshold) {
                        if (verbose) {
                            System.out.println("CHEAT: " + nextWall.index + " > " + nextCell.index + " = " + cheatScore);
                        }
                        foundCheats++;
                    }
                }
            }
        }
        return foundCheats;
    }
}
