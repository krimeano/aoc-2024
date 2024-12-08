package krimeano.aoc2024.days.day07;

import krimeano.aoc2024.days.my_lib.SolveDay;

public class Day7x1 extends SolveDay {
    public Day7x1(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        return (int) solveLong(textInput, false);
    }

    public long solveLong(String textInput, boolean allowConcatenation) {
        long result = 0;
        try {
            for (String line : getLines(textInput)) {
                Equation equation = new Equation(verbose, line, allowConcatenation);
                if (equation.calibrates()) {
                    result += equation.testValue;
                }
            }
        } catch (ParseEquationException e) {
            System.err.println("Parsing error: " + e.getMessage());
        }
        System.out.println(result);
        return result;
    }
}
