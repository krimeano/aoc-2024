package krimeano.aoc2024.days.day21;

import krimeano.aoc2024.days.my_lib.SolveDay;

public class Day21x1 extends SolveDay {
    Pad keyPad = new KeyPad();
    Pad numPad = new NumPad();

    public Day21x1(boolean verbose) {
        super(verbose);
        System.out.println(keyPad.positions);
        System.out.println(numPad.positions);
    }

    @Override
    public int solve(String textInput) {
        for (String line : getLines(textInput)) {
            System.out.println(line);
        }
        return 0;
    }
}
