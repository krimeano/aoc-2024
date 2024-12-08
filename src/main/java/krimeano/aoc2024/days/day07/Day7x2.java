package krimeano.aoc2024.days.day07;

public class Day7x2 extends Day7x1 {
    public Day7x2(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        return (int) solveLong(textInput, true);
    }
}
