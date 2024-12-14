package krimeano.aoc2024.days.day14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Day14x2 extends Day14x1 {

    protected int time;

    public Day14x2(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput, int width, int height) {
        time = 0;
        robots = parseInput(textInput);
        int maxNeighbours = 0;
        int maxNeighboursTime = 0;
        for (int i = 0; i < width * height; i++) {
            for (ArrayList<Integer> robot : robots) {
                int x = robot.get(0) + robot.get(2);
                if (x < 0) {
                    x += width;
                } else if (x >= width) {
                    x -= width;
                }
                robot.set(0, x);
                int y = robot.get(1) + robot.get(3);
                if (y < 0) {
                    y += height;
                } else if (y >= height) {
                    y -= height;
                }
                robot.set(1, y);
            }
            time++;

            int hasNeighbours = countHasNeighbours();
            if (hasNeighbours > maxNeighbours) {
                maxNeighbours = hasNeighbours;
                maxNeighboursTime = time;
                if (verbose) {
                printRobots(width, height);
                System.out.println("TIME = " + time + " NEIGHBOURS: " + hasNeighbours);
                }
            }
        }
        return maxNeighboursTime;
    }

    protected void printRobots(int width, int height) {
        HashMap<Integer, HashSet<Integer>> positions = getRobotsPositions();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(positions.getOrDefault(j, new HashSet<>()).contains(i) ? 'x' : '.');
            }
            System.out.println();
        }
    }

    protected HashMap<Integer, HashSet<Integer>> getRobotsPositions() {
        HashMap<Integer, HashSet<Integer>> positions = new HashMap<>();
        for (ArrayList<Integer> robot : robots) {
            int x = robot.get(0);
            int y = robot.get(1);
            HashSet<Integer> col = positions.getOrDefault(x, new HashSet<>());
            col.add(y);
            positions.put(x, col);
        }
        return positions;
    }

    protected int countHasNeighbours() {
        HashMap<Integer, HashSet<Integer>> positions = getRobotsPositions();
        int count = 0;
        for (ArrayList<Integer> robot : robots) {
            int x = robot.get(0);
            int y = robot.get(1);
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if (i == 0 && j == 0) {
                        continue;
                    }
                    if (positions.getOrDefault(x + i, new HashSet<>()).contains(y + j)) {
                        count++;
                    }
                }
            }
        }
        return count / 2;
    }
}
