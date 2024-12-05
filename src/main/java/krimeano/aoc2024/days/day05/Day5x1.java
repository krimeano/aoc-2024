package krimeano.aoc2024.days.day05;

import krimeano.aoc2024.days.my_lib.SolveDay;

import java.util.HashMap;

public class Day5x1 extends SolveDay {

    protected HashMap<String, HashMap<String, Integer>> rules = new HashMap<>();

    public Day5x1(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        int result = 0;
        ReadingMode readingMode = ReadingMode.RULES;

        for (String line : getLines(textInput, true)) {
            if (readingMode == ReadingMode.RULES) {
                if (line.isEmpty()) {
                    readingMode = ReadingMode.DATA;
                    continue;
                }
                this.readRule(line);
            } else {
                if (line.isEmpty()) {
                    continue;
                }
                result += processData(line.split(","));
            }
        }
        return result;
    }

    protected void readRule(String line) {
        String[] parts = line.split("\\|");
        {
            HashMap<String, Integer> rule = rules.getOrDefault(parts[0], new HashMap<>());
            rule.put(parts[1], -1);
            rule.put(parts[0], 0);
            rules.put(parts[0], rule);
        }
        {
            HashMap<String, Integer> oppositeRule = rules.getOrDefault(parts[1], new HashMap<>());
            oppositeRule.put(parts[0], 1);
            oppositeRule.put(parts[1], 0);
            rules.put(parts[1], oppositeRule);
        }
    }

    protected int processData(String[] items) {
        if (isSorted(items)) {
            String item = items[items.length / 2];
            return Integer.parseInt(item);
        }
        return 0;
    }

    protected boolean isSorted(String[] items) {
        for (int i = 0; i < items.length - 1; i++) {
            if (rules.get(items[i]).get(items[i + 1]) > 0) { /* XXX */
                return false;
            }
        }
        return true;
    }

}
