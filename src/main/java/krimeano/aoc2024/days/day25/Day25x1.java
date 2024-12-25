package krimeano.aoc2024.days.day25;

import krimeano.aoc2024.days.my_lib.SolveDay;

import java.util.ArrayList;
import java.util.List;

public class Day25x1 extends SolveDay {

    public Day25x1(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        List<List<Integer>> items = new ArrayList<>();
        List<Integer> item = new ArrayList<>();
        items.add(item);
        for (String line : getLines(textInput, true)) {
            if (line.isEmpty()) {
                if (verbose) {
                    System.out.println(item);
                }
                item = new ArrayList<>();
                items.add(item);
                continue;
            }

            for (int i = 0; i < line.length(); i++) {
                if (item.size() <= i) {
                    item.add(0);
                }
                item.set(i, (item.get(i) << 1) + (line.charAt(i) == '#' ? 1 : 0));
            }
        }
        if (verbose) {
            System.out.println(item);
        }


        int result = 0;
        int itemsCount = items.size();
        for (int i = 0; i < itemsCount - 1; i++) {
            List<Integer> x = items.get(i);

            for (int j = i + 1; j < itemsCount; j++) {
                List<Integer> y = items.get(j);

                boolean fits = true;

                for (int k = 0; k < x.size(); k++) {
                    if ((x.get(k) & y.get(k)) > 0) {
                        fits = false;
                        break;
                    }
                }

                if (fits) {
                    if (verbose) {
                        System.out.println(x + " fits " + y);
                    }
                    result++;
                } else {
                    if (verbose) {
                        System.out.println(x + " doesn't fit " + y);
                    }
                }
            }
        }

        return result;
    }
}
