package krimeano.aoc2024.days.day19;

public class Day19x2 extends Day19x1 {
    public Day19x2(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        parseInput(getLines(textInput, true));
        if (verbose) {
            System.out.println(patterns);
            for (String design : designs) {
                System.out.println(design);
            }
        }
        long result = 0L;

        for (String design : designs) {
            result += waysToKnit(design);
        }

        if (verbose) {
            for (String design : designs) {
                System.out.println(design + " -> " + cachedResults.get(design));
            }
        }

        System.out.println(result);
        return (int) result;

    }
}
