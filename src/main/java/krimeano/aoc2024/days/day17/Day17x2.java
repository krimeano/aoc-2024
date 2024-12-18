package krimeano.aoc2024.days.day17;

import java.util.ArrayList;

public class Day17x2 extends Day17x1 {
    public Day17x2(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {

        return (int) this.solveLong(textInput);
    }

    public long solveLong(String textInput) {
        String program = getLines(textInput).getLast().split(": ")[1];
        if (verbose) {
            System.out.println(program);
        }
        Computer computer = new Computer(false);
        String output;

        int cursor = program.length() - 1;
        ArrayList<Long> results = new ArrayList<>();
        results.add(0L);
        while (cursor >= 0) {
            ArrayList<Long> newResults = new ArrayList<>();
            if (verbose) {
                System.out.println("SUBSTRING = " + program.substring(cursor));
            }

            for (long oldResult : results) {
                long result = oldResult << 3;
                for (int a = 0; a < 8; a++) {
                    output = computer.set(result + a, 0, 0).run(program);
                    if (verbose) {
                        System.out.println(a + ": " + output);
                    }
                    if (output.equals(program.substring(cursor))) {
                        newResults.add(result + a);
                        if (verbose) {
                            System.out.println("MATCHES!");
                        }
                    }
                }
            }

            results = newResults;

            if (results.isEmpty()) {
                System.err.println("nothing found!");
                break;
            }

            cursor -= 2;
        }
        if (verbose) {
            System.out.println(results);
        }
        long minResult = Long.MAX_VALUE;
        for (long result : results) {
            if (result < minResult) {
                minResult = result;
            }
        }
        System.out.println(minResult);
        return minResult;
    }
}
