package krimeano.aoc2024.days.day22;

import krimeano.aoc2024.days.my_lib.SolveDay;

public class Day22x1 extends SolveDay {
    public Day22x1(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        long result = 0;
        for (int firstNumber : getLinesAsNumbers(textInput)) {
            Generator generator = new SecretNumbersGenerator(firstNumber);
            for (int i = 0; i < 1999; i++) {
                generator.next();
            }
            result += generator.next();
        }
        System.out.println(result);
        return (int) result;
    }
}
