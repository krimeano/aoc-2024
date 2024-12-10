package krimeano.aoc2024.days.day10;

import java.util.ArrayList;

public class Day10x2 extends Day10x1 {
    public Day10x2(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        int result = 0;

        initSolution(textInput);
        for (int[] zero : zeroes) {
            if (verbose) {
                System.out.println("Looking for trail from " + zero[0] + "," + zero[1]);
            }

            ArrayList<int[]> current = new ArrayList<>();
            current.add(zero);

            int height = 0;

            while (!current.isEmpty() && height < 9) {
                height++;
                ArrayList<int[]> next = new ArrayList<>();

                for (int[] position : current) {
                    for (int[] neighbour : getNeighbours(position)) {
                        int x = neighbour[0];
                        int y = neighbour[1];
                        if (matrix.get(x).get(y) == height) {
                            if (verbose) {
                                System.out.println("Found " + height + " at " + x + "," + y);
                            }
                            next.add(neighbour);
                        }
                    }
                }
                current = next;
            }
            if (verbose) {
                System.out.println(height + " at " + current.size() + " positions ");
            }
            result += current.size();
        }
        return result;
    }
}
