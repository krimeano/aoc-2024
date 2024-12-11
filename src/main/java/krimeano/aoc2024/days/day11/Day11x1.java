package krimeano.aoc2024.days.day11;

import krimeano.aoc2024.days.my_lib.SolveDay;

import java.util.HashMap;

public class Day11x1 extends SolveDay {

    protected static HashMap<String, HashMap<Integer, Long>> cache = new HashMap<>();
    protected static final long MULTIPLIER = 2024;
    public static final int TIMES = 25;

    public Day11x1(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        return solve(textInput, TIMES);
    }

    public int solve(String textInput, int blinks) {
        long result = 0;
        for (String item : textInput.trim().split(" ")) {
            result += getNumberOfStones(item, blinks);
        }
        System.out.println("(long) " + result);
        return (int) result;
    }

    public long getNumberOfStones(String number, int blinks) {

        if (verbose) {
            System.out.println(number + " after " + blinks + " blinks");
        }

        if (blinks <= 0) {
            return 1;
        }

        HashMap<Integer, Long> currentCache = cache.getOrDefault(number, new HashMap<>());

        if (currentCache.containsKey(blinks)) {
            return currentCache.get(blinks);
        }

        long value;

        if (number.equals("0")) {
            /*
            If the stone is engraved with the number 0, it is replaced by a stone engraved with the number 1
             */
            value = getNumberOfStones("1", blinks - 1);
        } else if (number.length() % 2 == 0) {
            /*
            If the stone is engraved with a number that has an even number of digits, it is replaced by two stones.
            The left half of the digits are engraved on the new left stone,
            and the right half of the digits are engraved on the new right stone.
            (The new numbers don't keep extra leading zeroes: 1000 would become stones 10 and 0.)
             */
            int half = number.length() / 2;
            String left = number.substring(0, half);
            String right = number.substring(half);
            while (right.startsWith("0")) {
                right = right.substring(1);
            }
            if (right.isEmpty()) {
                right = "0";
            }
            value = getNumberOfStones(left, blinks - 1) + getNumberOfStones(right, blinks - 1);
        } else {
            /* If none of the other rules apply, the stone is replaced by a new stone;
            the old stone's number multiplied by 2024 is engraved on the new stone.
             */
            long next = Long.parseLong(number) * MULTIPLIER;
            value = getNumberOfStones(next + "", blinks - 1);
        }

        if (verbose) {
            System.out.println("    N(" + number + "," + blinks + ") = " + value);
        }
        currentCache.put(blinks, value);
        cache.put(number, currentCache);
        return value;
    }
}
