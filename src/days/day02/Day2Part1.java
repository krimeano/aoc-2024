package days.day02;

import days.my_lib.SolveDay;

public class Day2Part1 extends SolveDay {
    public Day2Part1(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        int result = 0;
        for (String line : getLines(textInput)) {
            int diffMin = Integer.MAX_VALUE;
            int diffMax = Integer.MIN_VALUE;
            String[] words = line.split(" ");
            int prev = Integer.parseInt(words[0]);

            for (int i = 1; i < words.length; i++) {
                int current = Integer.parseInt(words[i]);
                int diff = current - prev;

                if (diff < diffMin) {
                    diffMin = diff;
                }

                if (diff > diffMax) {
                    diffMax = diff;
                }

                prev = current;
            }

            if ((diffMin >= -3 && diffMax <= -1) || (diffMin >= 1 && diffMax <= 3)) {
                result++;
            }
        }

        return result;
    }
}
