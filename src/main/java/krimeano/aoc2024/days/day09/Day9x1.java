package krimeano.aoc2024.days.day09;

import krimeano.aoc2024.days.my_lib.SolveDay;

public class Day9x1 extends SolveDay {
    public Day9x1(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        String line = textInput.trim();
        long result = 0;
        int left_cursor = 0;
        int right_cursor = line.length() - 1;
        right_cursor -= (right_cursor % 2);
        int left_width = line.charAt(left_cursor) - '0';
        int right_width = line.charAt(right_cursor) - '0';
        int position = 0;
        int width;
        int ix;
        long volume;
        while (left_cursor < right_cursor) {
            if (left_cursor % 2 == 0) {
                ix = left_cursor / 2;
                width = left_width;
            } else {
                ix = right_cursor / 2;
                width = Math.min(left_width, right_width);
                right_width -= width;
            }

            left_width -= width;
            volume = ix * (position * width + width * (width - 1) / 2);
            result += volume;
            position += width;
            System.out.println(volume + ", " + result);

            if (left_width == 0) {
                left_cursor += 1;
                left_width = line.charAt(left_cursor) - '0';
            }

            if (right_width == 0) {
                right_cursor -= 2;
                right_width = line.charAt(right_cursor) - '0';
            }
        }

        if (left_cursor == right_cursor) {
            ix = right_cursor / 2;
            width = right_width;
            volume = ix * (position * width + width * (width - 1) / 2);
            result += volume;
        }
        System.out.println(result);
        return (int) result;
    }
}
