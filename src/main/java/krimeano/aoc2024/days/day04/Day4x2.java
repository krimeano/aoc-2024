package krimeano.aoc2024.days.day04;

import krimeano.aoc2024.days.my_lib.SolveDay;

import java.util.ArrayList;

public class Day4x2 extends SolveDay {

    public Day4x2(boolean verbose) {
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

        for (int i = 1; i < height - 1; i++) {
            for (int j = 1; j < width - 1; j++) {
                if (lines.get(i).charAt(j) == 'A') {
                    if (verbose) {
                        System.out.println("A is found at " + i + ", " + j);
                    }
                    if (
                            ((lines.get(i - 1).charAt(j - 1) == 'M') && (lines.get(i + 1).charAt(j + 1) == 'S')
                                    || (lines.get(i - 1).charAt(j - 1) == 'S') && (lines.get(i + 1).charAt(j + 1) == 'M'))
                                    && ((lines.get(i - 1).charAt(j + 1) == 'M') && (lines.get(i + 1).charAt(j - 1) == 'S')
                                    || (lines.get(i - 1).charAt(j + 1) == 'S') && (lines.get(i + 1).charAt(j - 1) == 'M'))

                    ) {
                        if (verbose) {
                            System.out.println("X-MAS is found at " + i + ", " + j);
                        }
                        result += 1;
                    }
                }
            }
        }

        return result;
    }
}
