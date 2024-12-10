package krimeano.aoc2024.days.day09;

import krimeano.aoc2024.days.my_lib.SolveDay;

import java.util.ArrayList;

public class Day9x1 extends SolveDay {
    public Day9x1(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        String line = textInput.trim();
//        long result = solveBruteforce(line);
        long result = solveOptimized(line);
        System.out.println(result);
        return (int) result;
    }

    protected long solveBruteforce(String line) {
        long result = 0;
        ArrayList<Integer> disk = new ArrayList<>();
        int ix = 0;
        boolean mode = true;
        for (char c : line.toCharArray()) {
            int width = c - '0';
            if (verbose) {
                System.out.println(ix + " " + c + " " + width + " " + mode);
            }
            for (byte i = 0; i < width; i++) {
                disk.add(mode ? ix : -1);
            }
            if (!mode) {
                ix += 1;
            }
            mode = !mode;
        }
        if (verbose) {
            System.out.println(disk);
        }

        int cursor = 0;

        while (cursor < disk.size()) {
            int value = disk.get(cursor);
            System.out.println(cursor + "/" + disk.size() + "="+ value);
            System.out.println(disk.subList(disk.size() - 20, disk.size()));
            if (value != -1) {
                result += (long) value * (long) cursor;
                cursor++;
                continue;
            }
            while (value == -1 && cursor < disk.size()) {
                value = disk.removeLast();
            }
            if (value == -1) {
                break;
            }
            result += (long) value * (long) cursor;
            disk.set(cursor++, value);
        }
        System.out.println(disk.subList(disk.size() - 20, disk.size()));

        return result;
    }

    protected long solveOptimized(String line) {
        long result = 0;
        int left_cursor = 0;
        int right_cursor = line.length() - 1;
        right_cursor -= (right_cursor % 2);
        long left_width = line.charAt(left_cursor) - '0';
        long right_width = line.charAt(right_cursor) - '0';
        long position = 0;
        long width;
        long ix;
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
        return result;
    }
}
