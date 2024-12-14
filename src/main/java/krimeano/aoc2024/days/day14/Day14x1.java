package krimeano.aoc2024.days.day14;

import krimeano.aoc2024.days.my_lib.SolveDay;

import java.util.ArrayList;

public class Day14x1 extends SolveDay {
    protected ArrayList<ArrayList<Integer>> robots;

    public Day14x1(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        return solve(textInput, 101, 103);
    }

    public int solve(String textInput, int width, int height) {
        return solve(textInput, width, height, 100);
    }

    public int solve(String textInput, int width, int height, int time) {
        int middleX = width / 2;
        int middleY = height / 2;
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        robots = parseInput(textInput);
        for (ArrayList<Integer> robot : robots) {
            int x = (robot.get(0) + robot.get(2) * time) % width;
            int y = (robot.get(1) + robot.get(3) * time) % height;
            if (x < 0) {
                x += width;
            }
            if (y < 0) {
                y += height;
            }
            if (verbose) {
                System.out.print(x + "," + y);
            }
            if (x == middleX || y == middleY) {
                if (verbose) {
                    System.out.println(" middle " + middleX + ", " + middleY + ", ignore");
                }
            } else if (x < middleX) {
                if (y < middleY) {
                    a += 1;
                    if (verbose) {
                        System.out.println(" left top quadrant, A++");
                    }
                } else {
                    c += 1;
                    if (verbose) {
                        System.out.println(" left bottom quadrant, C++");
                    }
                }
            } else if (y < middleY) {
                b += 1;
                if (verbose) {
                    System.out.println(" right top quadrant, B++");
                }
            } else {
                d += 1;
                if (verbose) {
                    System.out.println(" right bottom quadrant, D++");
                }
            }
        }
        if (verbose) {
            System.out.println("A = " + a + ", B = " + b + ", C = " + c + ", D = " + d);
        }
        return a * b * c * d;
    }

    protected ArrayList<ArrayList<Integer>> parseInput(String textInput) {
        ArrayList<ArrayList<Integer>> input = new ArrayList<>();
        for (String line : getLines(textInput)) {
            ArrayList<Integer> inputRow = new ArrayList<>();
            for (String part : line.split(" ")) {
                for (String value : part.substring(2).split(",")) {
                    inputRow.add(Integer.parseInt(value));
                }
            }
            input.add(inputRow);
        }
        return input;
    }
}
