package krimeano.aoc2024.days.day15;

import krimeano.aoc2024.days.my_lib.CantMoveException;
import krimeano.aoc2024.days.my_lib.SolveDay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Day15x1 extends SolveDay {
    protected static final char ROBOT = '@';
    protected static final char BOX = 'O';
    protected static final char WALL = '#';
    protected static final char SPACE = '.';
    protected static final Map<Character, List<Integer>> DIRECTIONS = Map.of(
            '^', Arrays.asList(-1, 0),
            '<', Arrays.asList(0, -1),
            'v', Arrays.asList(1, 0),
            '>', Arrays.asList(0, 1)
    );
    protected ArrayList<ArrayList<Character>> warehouse;
    protected int[] robot;

    public Day15x1(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        String[] parts = textInput.split("\n\n");

        readMap(parts[0]);
        travel(parts[1]);
        return calculateGPS();
    }

    public void readMap(String strMap) {
        ArrayList<String> lines = getLines(strMap);
        warehouse = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            ArrayList<Character> row = new ArrayList<>();
            String line = lines.get(i);

            for (int j = 0; j < line.length(); j++) {
                char c = line.charAt(j);
                row.add(c);

                if (c == ROBOT) {
                    robot = new int[]{i, j};
                }
            }

            warehouse.add(row);
        }
    }

    public void travel(String instructions) {
        if (verbose) {
            System.out.println("FISH AT: " + robot[0] + "," + robot[1]);
            printWarehouse();
        }
        for (String line : getLines(instructions)) {
            for (char c : line.toCharArray()) {
                try {
                    move(c);
                    if (verbose) {
                        System.out.println("FISH MOVED TO: " + robot[0] + "," + robot[1]);
                        printWarehouse();
                    }
                } catch (CantMoveException e) {
                    if (verbose) {
                        System.out.println(e.getMessage());
                    }
                    continue;
                } catch (NotAFishException e) {
                    System.err.println(e.getMessage());
                    return;
                }
            }
        }
    }

    public void move(char directionName) throws CantMoveException, NotAFishException {
        List<Integer> direction = DIRECTIONS.get(directionName);
        if (verbose) {
            System.out.println("TRY MOVE " + directionName + ": " + direction);
        }
        if (direction == null) {
            throw new CantMoveException();
        }

        int beginX = robot[0];
        int beginY = robot[1];

        if (warehouse.get(beginX).get(beginY) != ROBOT) {
            throw new NotAFishException();
        }

        int nextX = beginX + direction.get(0);
        int nextY = beginY + direction.get(1);
        char next = warehouse.get(nextX).get(nextY);

        int endX = nextX;
        int endY = nextY;
        char end = next;

        while (end != SPACE) {
            if (end == WALL) {
                throw new CantMoveException();
            }
            endX += direction.get(0);
            endY += direction.get(1);
            end = warehouse.get(endX).get(endY);
        }


        warehouse.get(beginX).set(beginY, end);
        if (endX != nextX || endY != nextY) {
            warehouse.get(endX).set(endY, next);
        }
        warehouse.get(nextX).set(nextY, ROBOT);

        robot[0] = nextX;
        robot[1] = nextY;
    }

    protected void printWarehouse() {
        if (!verbose) {
            return;
        }
        for (ArrayList<Character> row : warehouse) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    protected int calculateGPS() {
        int result = 0;
        for (int i = 0; i < warehouse.size(); i++) {
            ArrayList<Character> row = warehouse.get(i);
            for (int j = 0; j < row.size(); j++) {
                char c = row.get(j);
                if (c == BOX) {
                    result += i * 100 + j;
                }
            }
        }
        return result;
    }
}
