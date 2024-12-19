package krimeano.aoc2024.days.day16;

import krimeano.aoc2024.days.my_lib.SolveDay;

import java.util.*;

public class Day16x1 extends SolveDay {
    static final int SCORE_STEP = 1;
    static final int SCORE_ROTATE = 1000;
    static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // >, v, <, ^
    List<String> maze;
    int width;
    int height;
    List<Integer> startingPoint;
    List<List<Integer>> endPoints;

    Map<List<Integer>, Integer> vectorScores;

    public Day16x1(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        initMaze(textInput);
        initScores();
        run();
        return getMinEndScore();
    }

    protected void initMaze(String textInput) {
        maze = getLines(textInput);
        height = maze.size();
        width = height > 0 ? maze.getFirst().length() : 0;

        startingPoint = Arrays.asList(height - 2, 1, 0);
        assert maze.get(startingPoint.get(0)).charAt(startingPoint.get(1)) == 'S';

        endPoints = new ArrayList<>();
        for (int k = 0; k < DIRECTIONS.length; k++) {
            List<Integer> endPoint = Arrays.asList(1, width - 2, k);
            endPoints.add(endPoint);
            assert maze.get(endPoint.get(0)).charAt(endPoint.get(1)) == 'E';
        }

    }

    protected void initScores() {
        vectorScores = new HashMap<>();
        int maxScore = width * height * (SCORE_STEP + SCORE_ROTATE);

        for (int i = 1; i < height - 1; i++) {
            for (int j = 1; j < width - 1; j++) {

                char cell = maze.get(i).charAt(j);

                if (cell == '#') {
                    continue;
                }


                for (int k = 0; k < DIRECTIONS.length; k++) {
                    int score = (cell == 'S' && k == 0) ? 0 : maxScore;
                    vectorScores.put(Arrays.asList(i, j, k), score);
                }
            }
        }
    }

    protected void run() {
        Set<List<Integer>> wave = new HashSet<>();
        wave.add(startingPoint);

        while (!wave.isEmpty()) {
            Set<List<Integer>> newWave = new HashSet<>();
            if (verbose) {
                System.out.println(wave);
            }
            for (List<Integer> current : wave) {
                int currentScore = vectorScores.get(current);
                if (verbose) {
                    System.out.println(current + " : " + currentScore);
                }
                int x0 = current.get(0);
                int y0 = current.get(1);
                int z0 = current.get(2);

                for (int z = 0; z < DIRECTIONS.length; z++) {
                    if (verbose) {
                        System.out.println("    DIRECTION " + z + " = " + DIRECTIONS[z][0] + "," + DIRECTIONS[z][1]);
                    }
                    if (Math.abs(z - z0) == 2) { /* no turn back */
                        continue;
                    }

                    int x = x0;
                    int y = y0;

                    int price = 0;

                    if (z == z0) { /* the same direction, can step forward */
                        price += SCORE_STEP;
                        x += DIRECTIONS[z][0];
                        y += DIRECTIONS[z][1];
                    } else {
                        price += SCORE_ROTATE;
                    }

                    List<Integer> xyz = Arrays.asList(x, y, z);
                    if (!vectorScores.containsKey(Arrays.asList(x, y, z))) { /* can go only to known cells/directions */
                        continue;
                    }

                    int score = currentScore + price;
                    int minScore = getMinEndScore();
                    if (score >= minScore) {
                        if (verbose) {
                            System.out.println("        HALTING! " + score + " >= " + minScore);
                        }
                        continue; /* it's already worse than final point */
                    }

                    if (compareScore(current, xyz, score)) {
                        newWave.add(xyz);
                    }
                }
            }
            wave = newWave;
        }
    }

    protected boolean compareScore(List<Integer> prevXyz, List<Integer> xyz, Integer score) {
        Integer oldScore = vectorScores.get(xyz);
        if (oldScore > score) {
            if (verbose) {
                System.out.println("        SCORE " + score + " <= " + oldScore);
            }
            vectorScores.put(xyz, score);
            return true;
        } else if (verbose) {
            System.out.println("        SCORE " + score + " > " + oldScore);
        }
        return false;
    }

    protected int getMinEndScore() {
        int minimumScore = Integer.MAX_VALUE;
        for (List<Integer> endPoint : endPoints) {
            minimumScore = Math.min(minimumScore, vectorScores.get(endPoint));
        }
        return minimumScore;
    }

}
