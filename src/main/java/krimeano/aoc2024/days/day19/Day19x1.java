package krimeano.aoc2024.days.day19;

import krimeano.aoc2024.days.my_lib.SolveDay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day19x1 extends SolveDay {
    List<String> patterns;
    List<String> designs;
    Map<String, Boolean> cachedResults;

    public Day19x1(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        parseInput(getLines(textInput, true));
        if (verbose) {
            System.out.println(textInput);
        }
        int result = 0;
        for (String design : designs) {
            if (canKnit(design)) {
                result++;
            }
        }
        return result;
    }

    protected void parseInput(ArrayList<String> lines) {
        cachedResults = new HashMap<>();
        patterns = new ArrayList<>();
        designs = new ArrayList<>();
        boolean isSecondPart = false;

        for (String line : lines) {
            if (line.isEmpty()) {
                isSecondPart = true;
                continue;
            }

            if (!isSecondPart) {
                for (String pattern : line.split(", ")) {
                    patterns.add(pattern);
                    cachedResults.put(pattern, true);
                }
            } else {
                designs.add(line);
            }
        }
    }

    protected boolean canKnit(String design) {
        return canKnit(design, "");
    }

    protected boolean canKnit(String design, String verbosePrefix) {
        if (cachedResults.containsKey(design)) {
            boolean canBeKnitted = cachedResults.get(design);
            if (verbose) {
                System.out.print(verbosePrefix);
                System.out.println("CACHED " + design + " > " + canBeKnitted);
            }
            return canBeKnitted;
        }
        for (String pattern : patterns) {
            if (design.startsWith(pattern)) {
                String remainder = design.substring(pattern.length());

                if (verbose) {
                    System.out.print(verbosePrefix);
                    System.out.println(pattern + " + " + remainder + " = " + design);
                }

                if (canKnit(remainder, verbosePrefix + "    ")) {
                    cachedResults.put(design, true);
                    return true;
                }
            }
        }
        if (verbose) {
            System.out.print(verbosePrefix);
            System.out.println("\"" + design + "\" CAN'T BE KNITTED");
        }
        cachedResults.put(design, false);
        return false;
    }
}
