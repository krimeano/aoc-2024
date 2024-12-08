package krimeano.aoc2024.days.day08;

public class Day8x2 extends Day8x1 {
    public Day8x2(boolean verbose) {
        super(verbose);
    }

    @Override
    protected void findAntinodes(int x0, int y0, int x1, int y1, char antenna) {
        int dx = x1 - x0;
        int dy = y1 - y0;
        int fromCoefficient;
        int toCoefficient;
        if (dx == 0) {
            if (dy < 0) {
                dy = -dy;
            }
            fromCoefficient = -(y0 / dy);
            toCoefficient = (width - y0) / dy;
        } else {
            if (dx < 0) {
                dx = -dx;
                dy = -dy;
            }
            fromCoefficient = -(x0 / dx);
            toCoefficient = (height - x0) / dx;
        }

        if (verbose) {
            System.out.println(" from " + fromCoefficient + " to " + toCoefficient+ "; x0=" + x0 + ", y0=" + y0 + "; dx=" + dx + ", dy=" + dy);
        }

        for (int k = fromCoefficient; k <= toCoefficient; k++) {
            addAntinode(x0 + k * dx, y0 + k * dy, antenna);
        }
    }
}
