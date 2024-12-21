package krimeano.aoc2024.days.day20;

public class Day20x2 extends Day20x1 {
    protected static final int CHEAT_LIMIT = 20;

    public Day20x2(boolean verbose) {
        System.out.println("Day20x2");
        super(verbose);
    }

    @Override
    protected int findCheats(int threshold) {
        return super.findCheats(threshold, CHEAT_LIMIT);
    }
}
