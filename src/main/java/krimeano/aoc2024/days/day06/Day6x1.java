package krimeano.aoc2024.days.day06;

import krimeano.aoc2024.days.my_lib.SolveDay;

import java.util.ArrayList;

public class Day6x1 extends SolveDay {

    protected static final int[] INIT_DIRECTION = {-1, 0};
    protected static final int[][] ROTATE = {{0, 1}, {-1, 0}};
    protected int[] direction = {-1, 0};
    protected int height = 0;
    protected int width = 0;
    protected ArrayList<String> lines = new ArrayList<>();

    public Day6x1(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        lines = getLines(textInput);

        height = lines.size();
        if (height == 0) {
            return 0;
        }

        width = lines.getFirst().length();
        if (width == 0) {
            return 0;
        }
        printLines(lines);

        int x = -1;
        int y = -1;

        for (int i = 0; i < height; i++) {
            int j = lines.get(i).indexOf('^');
            if (j >= 0) {
                x = i;
                y = j;
                break;
            }
        }
        int result = 0;
        int localRotations = 0;
        while (isInside(x, y)) {
            if (lines.get(x).charAt(y) != 'X') {
                result += 1;
                mark(x, y);
            }

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
        printLines(lines);
        return result;
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
        return isInside(x, y) && lines.get(x).charAt(y) == '#';
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
