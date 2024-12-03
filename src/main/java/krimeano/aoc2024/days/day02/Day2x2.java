package krimeano.aoc2024.days.day02;

import java.util.Vector;

public class Day2x2 extends Day2x1 {

    public Day2x2(boolean verbose) {
        super(verbose);
    }

    @Override
    protected boolean isSafe(Vector<Integer> reactor) {
        if (super.isSafe(reactor)) {
            return true;
        }

        for (int i = 0; i < reactor.size(); i++) {
            Vector<Integer> reactorCopy = (Vector<Integer>) reactor.clone();
            reactorCopy.remove(i);
            if (super.isSafe(reactorCopy)) {
                return true;
            }
        }

        return false;
    }

}
