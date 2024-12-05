package krimeano.aoc2024.days.day05;

import krimeano.aoc2024.days.my_lib.SolveDay;

import java.util.HashMap;

public class Day5x1 extends SolveDay {
    public Day5x1(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        int result = 0;

        HashMap<String, HashMap<String, Integer>> rules = new HashMap<>();
        HashMap<String, Integer> simplerRules = new HashMap<>();

        ReadingMode readingMode = ReadingMode.RULES;

        for (String line : getLines(textInput, true)) {
            if (readingMode == ReadingMode.RULES) {
                if (line.isEmpty()) {
                    readingMode = ReadingMode.DATA;
                    continue;
                }
                String[] parts = line.split("\\|");
                {
                    HashMap<String, Integer> rule = rules.getOrDefault(parts[0], new HashMap<>());
                    rule.put(parts[1], -1);
                    rules.put(parts[0], rule);
                }
                {
                    HashMap<String, Integer> oppositeRule = rules.getOrDefault(parts[1], new HashMap<>());
                    oppositeRule.put(parts[0], 1);
                    rules.put(parts[1], oppositeRule);
                }
                simplerRules.put(parts[1], simplerRules.getOrDefault(parts[1], 0) + 1);
                simplerRules.put(parts[0], simplerRules.getOrDefault(parts[0], 0) - 1);
            } else {
                if (line.isEmpty()) {
                    continue;
                }
                String[] items = line.split(",");
                boolean isSorted = true;
                for (int i = 0; i < items.length - 1; i++) {
                    if (rules.get(items[i]).get(items[i + 1]) > 0) {
                        isSorted = false;
                        break;
                    }
                }
                if (isSorted) {
                    String item = items[items.length / 2];
                    result += Integer.parseInt(item);
                }
            }
        }
        return result;
    }
}
