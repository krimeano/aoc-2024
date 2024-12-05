package krimeano.aoc2024.days.day05;

import java.util.ArrayList;
import java.util.Arrays;

public class Day5x2 extends Day5x1 {
    public Day5x2(boolean verbose) {
        super(verbose);
    }

    protected int processData(String[] items) {
        if (isSorted(items)) {
            return 0;
        }
        ArrayList<String> list = new ArrayList<>(Arrays.asList(items));
        list.sort(new ItemsComparator(rules));
        String item = list.get(items.length / 2);
        return Integer.parseInt(item);
    }
}
