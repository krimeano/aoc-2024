package krimeano.aoc2024.days.day02;

import krimeano.aoc2024.days.my_lib.SolveDay;

import java.util.Iterator;
import java.util.Vector;

public class Day2x1 extends SolveDay {
    protected static final int TOP = 3;
    protected static final int EXCLUDE = 0;
    protected static final int BOTTOM = -3;

    public Day2x1(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        int result = 0;

        for (Vector<Integer> reactor : getMatrix(textInput)) {
            if (isSafe(reactor)) {
                result++;
            }
        }

        return result;
    }

    protected boolean isSafe(Vector<Integer> reactor) {
        Iterator<Integer> iterator = reactor.iterator();
        if (iterator.hasNext()) {
            int prev = iterator.next();
            int current = iterator.next();
            int prevDiff = current - prev;

            if (invalidChange(prevDiff)) {
                return false;
            }

            prev = current;

            while (iterator.hasNext()) {
                current = iterator.next();
                int diff = current - prev;

                if (invalidChange(diff, prevDiff)) {
                    return false;
                }

                prev = current;
                prevDiff = diff;
            }

        }
        return true;
    }

    protected boolean invalidChange(int diff) {
        return diff < BOTTOM || diff == EXCLUDE || diff > TOP;
    }

    protected boolean invalidChange(int diff, int prevDiff) {
        return diff * prevDiff <= 0 || invalidChange(diff);
    }

}
