package krimeano.aoc2024.days.day13;

import krimeano.aoc2024.days.my_lib.SolveDay;

import java.util.ArrayList;

public class Day13x1 extends SolveDay {
    public Day13x1(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        ArrayList<String> lines = getLines(textInput, true);
        long[][] slau = {{0, 0}, {0, 0}, {0, 0}};
        long result = 0;
        int ix = 0;
        for (String line : lines) {
            if (line.isEmpty()) {
                continue;
            }
            String[] data = line.split(": ")[1].split(", ");
            for (int jy = 0; jy < 2; jy++) {
                slau[ix][jy] = Long.parseLong(data[jy].substring(2));
            }
            ix++;
            if (ix == 3) {
                result += solveSlau(correctSlau(slau));
                ix = 0;
            }
        }
        System.out.println(result);
        return (int) result;
    }

    public long[][] correctSlau(long[][] slau) {
        return slau;
    }

    protected long solveSlau(long[][] slau) {
        if (verbose) {
            System.out.println("x: " + slau[0][0] + " + " + slau[1][0] + " = " + slau[2][0]);
            System.out.println("y: " + slau[0][1] + " + " + slau[1][1] + " = " + slau[2][1]);
        }
        long detA = slau[2][0] * slau[1][1] - slau[1][0] * slau[2][1];
        long detB = slau[0][0] * slau[2][1] - slau[2][0] * slau[0][1];
        long detP = slau[0][0] * slau[1][1] - slau[1][0] * slau[0][1];
        if (detP < 0) {
            detA = -detA;
            detB = -detB;
            detP = -detP;
        }
        if (detA % detP != 0 || detB % detP != 0) {
            if (verbose) {
                System.out.println("detA: " + detA + " % " + detP + " = " + detA % detP);
                System.out.println("detB = " + detB + " % " + detP + " = " + detB % detP);
                System.out.println("No solution");
            }
            return 0;
        }
        long a = detA / detP;
        long b = detB / detP;
        if (verbose) {
            System.out.println("a = " + a + "; b = " + b);
        }
        return 3 * a + b;
    }
}
