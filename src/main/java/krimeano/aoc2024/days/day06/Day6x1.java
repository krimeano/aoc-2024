package krimeano.aoc2024.days.day06;

import krimeano.aoc2024.days.my_lib.SolveDay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Day6x1 extends SolveDay {

    protected static final int[] INIT_DIRECTION = {-1, 0};
    protected static final int[][] ROTATE = {{0, 1}, {-1, 0}};
    protected int[] direction = {-1, 0};
    protected int height = 0;
    protected int width = 0;
    protected ArrayList<String> lines = new ArrayList<>();
    protected HashMap<String, HashSet<String>> visited = new HashMap<>();

    public Day6x1(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        int[] start = initSolution(textInput);
        walk(start[0], start[1]);
        return visited.size();
    }

    protected int[] initSolution(String textInput) {

        int[] start = {-1, -1};
        height = 0;
        width = 0;
        lines = getLines(textInput);

        height = lines.size();
        if (height == 0) {
            return start;
        }

        width = lines.getFirst().length();
        if (width == 0) {
            return start;
        }
        printLines(lines);

        for (int i = 0; i < height; i++) {
            int j = lines.get(i).indexOf('^');
            if (j >= 0) {
                start[0] = i;
                start[1] = j;
                break;
            }
        }
        return start;
    }

    /**
     * @param x int
     * @param y int
     * @return 0 if no loop, 1 if there is a loop
     */
    protected int walk(int x, int y) {
        int localRotations = 0;
        visited = new HashMap<>();

        while (isInside(x, y)) {
            String pos = x + "." + y;
            String dir = direction[0] + "." + direction[1];
            HashSet<String> hashSet = visited.getOrDefault(pos, new HashSet<>());

            if (hashSet.contains(dir)) {
                if (verbose) {
                    System.out.println("found a loop");
                }
                return 1;
            } else {
                hashSet.add(dir);
            }

            visited.put(pos, hashSet);

            if (isBlocked(x, y, direction)) {
                direction = rotate(direction);
                localRotations++;
            } else {
                x += direction[0];
                y += direction[1];
                localRotations = 0;
            }

            if (localRotations > 3) {
                System.err.println("Should not happen: localRotations = " + localRotations + " at " + x + "," + y);
            }
        }
        return 0;
    }

    protected void printLines(ArrayList<String> lines) {
        if (!verbose) {
            return;
        }
        System.out.println();
        for (String line : lines) {
            System.out.println(line);
        }
        System.out.println();
    }

    protected boolean isInside(int x, int y) {
        return 0 <= x && x < height && 0 <= y && y < width;
    }

    protected boolean isBlocked(int x0, int y0, int[] direction) {
        int x = x0 + direction[0];
        int y = y0 + direction[1];
        return isInside(x, y) && (lines.get(x).charAt(y) == '#' || lines.get(x).charAt(y) == 'O');
    }

    protected int[] rotate(int[] direction) {
        int vx = direction[0] * ROTATE[0][0] + direction[1] * ROTATE[0][1];
        int vy = direction[0] * ROTATE[1][0] + direction[1] * ROTATE[1][1];
        return new int[]{vx, vy};
    }

    protected void mark(int x, int y) {
        String line = lines.get(x);
        lines.set(x, line.substring(0, y) + 'X' + line.substring(y + 1));
    }
}
