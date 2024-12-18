package krimeano.aoc2024.days.day18;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Day18x2 extends Day18x1 {
    public Day18x2(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput, int limit) {
        List<Byte> xy = this.solveList(textInput, limit);
        System.out.println(xy.getFirst() + "," + xy.getLast());
        return 0;
    }

    public List<Byte> solveList(String textInput, int limit) {
        corrupted = new HashSet<>();
        for (String line : getLines(textInput)) {
            List<Byte> xy = new ArrayList<>();
            for (String value : line.split(",")) {
                byte x = Byte.parseByte(value);
                if (size <= x) {
                    size = (byte) (x + 1);
                }
                xy.addFirst(x);
            }
            corrupted.add(xy);
            if (corrupted.size() > limit && findPath() < 0) {
                return xy;
            }
        }
        return new ArrayList<>();
    }
}
