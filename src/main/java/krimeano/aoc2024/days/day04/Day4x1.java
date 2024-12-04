package krimeano.aoc2024.days.day04;

import krimeano.aoc2024.days.my_lib.SolveDay;

import java.util.ArrayList;

public class Day4x1 extends SolveDay {
    public static final int XMAS = 4;
    /*                                           |        /       -       \       |       /        -         \    */
    public static final int[][] directions = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};


    public Day4x1(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        int result = 0;

        ArrayList<String> lines = getLines(textInput);

        int height = lines.size();
        if (height == 0) {
            return result;
        }

        int width = lines.getFirst().length();
        if (width == 0) {
            return result;
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (lines.get(i).charAt(j) == 'X') {
                    if (verbose) {
                        System.out.println("X is found at " + i + ", " + j);
                    }
                    for (int[] direction : directions) {
                        int[] m = {i + direction[0], j + direction[1]};
                        int[] a = {m[0] + direction[0], m[1] + direction[1]};
                        int[] s = {a[0] + direction[0], a[1] + direction[1]};
                        if ((0 <= s[0]) && (s[0] < height) && (0 <= s[1]) && (s[1] < width)) {
                            if (lines.get(m[0]).charAt(m[1]) == 'M' && lines.get(a[0]).charAt(a[1]) == 'A' && lines.get(s[0]).charAt(s[1]) == 'S') {
                                if (verbose) {
                                    System.out.println("XMAS found at (" + i + ", " + j + ") to (" + s[0] + ", " + s[1] + ")");
                                }
                                result += 1;
                            }
                        }
                    }
                }
            }
        }

        return result;
    }
}
