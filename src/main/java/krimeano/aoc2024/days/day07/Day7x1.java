package krimeano.aoc2024.days.day07;

import krimeano.aoc2024.days.my_lib.SolveDay;

public class Day7x1 extends SolveDay {
    public Day7x1(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        long result = 0;
        try {
            for (String line : getLines(textInput)) {
                Equation equation = new Equation(verbose, line);
                if (equation.calibrates()) {
                    result += equation.testValue;
                }
            }
        } catch (ParseEquationException e) {
            System.err.println("Parsing error: " + e.getMessage());
        }
        System.out.println(result);
        return 0;
    }
}
