package krimeano.aoc2024.days.day18;

import krimeano.aoc2024.days.my_lib.SolveDay;

import java.util.*;

public class Day18x1 extends SolveDay {
    byte[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    Set<List<Byte>> corrupted;
    Set<List<Byte>> path;
    byte size = 0;

    public Day18x1(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        return solve(textInput, 1024);
    }

    public int solve(String textInput, int limit) {
        parseInput(textInput, limit);
        if (verbose) {
            System.out.println(corrupted);
            printSpace();
        }
        return findPath();
    }

    protected void parseInput(String textInput, int limit) {
        corrupted = new HashSet<>();
        for (String line : getLines(textInput)) {
            List<Byte> xy = new ArrayList<>();
            for (String value : line.split(",")) {
                byte x = Byte.parseByte(value);
                if (size <= x) {
                    size = (byte) (x + 1);
                }
                xy.addFirst(x);
            }
            corrupted.add(xy);
            if (corrupted.size() >= limit) {
                break;
            }
        }
    }

    protected void printSpace() {
        for (byte i = 0; i < size; i++) {
            for (byte j = 0; j < size; j++) {
                List<Byte> xy = Arrays.asList(i, j);
                if (corrupted.contains(xy)) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }

    protected int findPath() {
        path = new HashSet<>();
        Set<List<Byte>> currentCells = new HashSet<>();

        Set<List<Byte>> visited = new HashSet<>();
        List<Byte> start = Arrays.asList((byte) 0, (byte) 0);
        visited.add(start);
        currentCells.add(start);
        int steps = 0;
        while (!currentCells.isEmpty()) {
            steps++;
            Set<List<Byte>> newCells = new HashSet<>();
            for (List<Byte> current : currentCells) {
                for (byte[] direction : DIRECTIONS) {
                    byte x = (byte) (current.get(0) + direction[0]);
                    byte y = (byte) (current.get(1) + direction[1]);
                    List<Byte> xy = Arrays.asList(x, y);
                    if (x < 0 || x >= size || y < 0 || y >= size || corrupted.contains(xy) || visited.contains(xy)) {
                        continue;
                    }
                    if (x == (size - 1) && y == (size - 1)) {
                        return steps;
                    }
                    visited.add(xy);
                    newCells.add(xy);
                }
            }
            if (verbose) {
                System.out.println(newCells);
            }
            currentCells = newCells;
        }
        return -1;
    }
}
