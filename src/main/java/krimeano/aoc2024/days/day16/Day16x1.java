package krimeano.aoc2024.days.day16;

import krimeano.aoc2024.days.my_lib.SolveDay;

import java.util.*;

public class Day16x1 extends SolveDay {
    static int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // >, v, <, ^
    ArrayList<String> maze;
    int width;
    int height;
    List<Integer> startingPoint;
    List<Integer> endPoint;

    HashMap<List<Integer>, Integer> vectorScores = new HashMap<>();
    HashMap<List<Integer>, Integer> scalarScores = new HashMap<>();

    public Day16x1(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        initMaze(textInput);
        initScores();
        run();
        return scalarScores.get(endPoint);
    }

    protected void initMaze(String textInput) {
        maze = getLines(textInput);
        height = maze.size();
        width = height > 0 ? maze.getFirst().length() : 0;

        startingPoint = Arrays.asList(height - 2, 1, 0);
        assert maze.get(startingPoint.get(0)).charAt(startingPoint.get(1)) == 'S';

        endPoint = Arrays.asList(1, width - 2);
        assert maze.get(endPoint.get(0)).charAt(endPoint.get(1)) == 'E';

    }

    protected void initScores() {
        int maxScore = width * height * 1001;
        vectorScores = new HashMap<>();
        scalarScores = new HashMap<>();

        for (int i = 1; i < height - 1; i++) {
            for (int j = 1; j < width - 1; j++) {

                char cell = maze.get(i).charAt(j);

                if (cell == '#') {
                    continue;
                }

                int score = cell == 'S' ? 0 : maxScore;

                scalarScores.put(Arrays.asList(i, j), score);

                for (int k = 0; k < DIRECTIONS.length; k++) {
                    vectorScores.put(Arrays.asList(i, j, k), score);
                }
            }
        }
    }

    protected void run() {
        HashSet<List<Integer>> justChanged = new HashSet<>();
        justChanged.add(startingPoint);

        while (!justChanged.isEmpty()) {
            List<Integer> current = justChanged.iterator().next();
            justChanged.remove(current);

            int currentScore = vectorScores.get(current);

            int x0 = current.get(0);
            int y0 = current.get(1);
            int currentDirectionIndex = current.get(2);
            int[] reindeerDirection = DIRECTIONS[currentDirectionIndex];

            for (int z = 0; z < DIRECTIONS.length; z++) {
                if (Math.abs(z - currentDirectionIndex) == 2) { /* no turn back */
                    continue;
                }

                int[] direction = DIRECTIONS[z];
                int x = x0 + direction[0];
                int y = y0 + direction[1];

                if (maze.get(x).charAt(y) == '#') {
                    continue;
                }

                List<Integer> xyz = Arrays.asList(x, y, z);

                int price = 1000 * (1 - reindeerDirection[0] * direction[0] - reindeerDirection[1] * direction[1]) + 1;
                int score = currentScore + price;

                if (score >= scalarScores.get(endPoint)) {
                    continue; /* it's already worse than final point */
                }

                if (vectorScores.get(xyz) > score) {
                    vectorScores.put(xyz, score);
                    justChanged.add(xyz);
                }

                this.compareScalarScore(x0, y0, x, y, score);
            }
        }
    }

    protected void compareScalarScore(int x0, int y0, int x, int y, Integer score) {
        List<Integer> xy = Arrays.asList(x, y);
        if (scalarScores.get(xy) > score) {
            scalarScores.put(xy, score);
        }
    }
}
