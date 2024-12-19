package krimeano.aoc2024.days.day19;

import krimeano.aoc2024.days.my_lib.SolveDay;

import java.util.*;

public class Day19x1 extends SolveDay {
    protected List<String> patterns;
    protected List<String> designs;
    protected Map<String, Long> cachedResults;

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
            if (waysToKnit(design) > 0) {
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
                patterns.addAll(Arrays.asList(line.split(", ")));
            } else {
                designs.add(line);
            }
        }

        cachedResults.put("", 1L);
    }

    public long waysToKnit(String design) {
        if (verbose) {
            System.out.println();
        }
        return waysToKnit(design, "");
    }

    protected long waysToKnit(String design, String verbosePrefix) {
        if (cachedResults.containsKey(design)) {
            long ways = cachedResults.get(design);

            if (verbose) {
                System.out.print(verbosePrefix);
                System.out.println("CACHED " + design + " > " + ways);
            }
            return ways;
        } else if (verbose) {
            System.out.print(verbosePrefix);
            System.out.println("CHECKING " + design);
        }

        long totalWays = 0;

        for (String pattern : patterns) {
            if (design.startsWith(pattern)) {
                String remainder = design.substring(pattern.length());

                if (verbose) {
                    System.out.print(verbosePrefix);
                    System.out.println(pattern + " + " + remainder + " = " + design);
                }
                totalWays += waysToKnit(remainder, verbosePrefix + "    ");
            }
        }

        if (verbose) {
            System.out.print(verbosePrefix);
            System.out.println("CACHING " + design + " < " + totalWays);
        }

        cachedResults.put(design, totalWays);

        return totalWays;
    }
}
