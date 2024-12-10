package krimeano.aoc2024.days.day10;

import krimeano.aoc2024.days.my_lib.SolveDay;

import java.util.*;

public class Day10x1 extends SolveDay {
    protected static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    int height;
    int width;
    Vector<Vector<Integer>> matrix;
    ArrayList<int[]> zeroes;

    public Day10x1(boolean verbose) {
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

            HashSet<List<Integer>> current = new HashSet<>();
            current.add(Arrays.asList(zero[0], zero[1]));
            int height = 0;

            while (!current.isEmpty() && height < 9) {
                height++;
                HashSet<List<Integer>> next = new HashSet<>();
                HashSet<List<Integer>> neighbours = new HashSet<>();
                for (List<Integer> position : current) {
                    for (int[] neighbour : getNeighbours(new int[]{position.get(0), position.get(1)})) {
                        neighbours.add(Arrays.asList(neighbour[0], neighbour[1]));
                    }
                }
                for (List<Integer> neighbour : neighbours) {
                    int x = neighbour.get(0);
                    int y = neighbour.get(1);
                    if (matrix.get(x).get(y) == height) {
                        if (verbose) {
                            System.out.println("Found " + height + " at " + x + "," + y);
                        }
                        next.add(Arrays.asList(x, y));
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

    protected void initSolution(String textInput) {
        matrix = getMatrix(textInput, "");
        height = matrix.size();
        width = height > 0 ? matrix.getFirst().size() : 0;
        zeroes = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (matrix.get(i).get(j) == 0) {
                    zeroes.add(new int[]{i, j});
                }
            }
        }
    }

    protected ArrayList<int[]> getNeighbours(int[] xy) {
        ArrayList<int[]> neighbours = new ArrayList<>();
        for (int[] direction : DIRECTIONS) {
            try {
                neighbours.add(getNeighbour(xy, direction));
            } catch (OutsideException e) {
                continue;
            }
        }
        return neighbours;
    }

    protected int[] getNeighbour(int[] xy, int[] direction) throws OutsideException {
        int x = xy[0] + direction[0];
        int y = xy[1] + direction[1];
        if (x < 0 || y < 0 || x >= height || y >= width) {
            throw new OutsideException();
        }
        return new int[]{x, y};
    }

}
