package krimeano.aoc2024.days.day13;

public class Day13x2 extends Day13x1 {
    public Day13x2(boolean verbose) {
        super(verbose);
    }

    @Override
    public long[][] correctSlau(long[][] slau) {
        slau[2][0] += 10_000_000_000_000L;
        slau[2][1] += 10_000_000_000_000L;
        return slau;
    }
}
