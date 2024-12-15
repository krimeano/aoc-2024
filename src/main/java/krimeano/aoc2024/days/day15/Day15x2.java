package krimeano.aoc2024.days.day15;

import krimeano.aoc2024.days.my_lib.CantMoveException;

import java.util.*;

public class Day15x2 extends Day15x1 {

    protected static final char BOX_LEFT = '[';
    protected static final char BOX_RIGHT = ']';

    public Day15x2(boolean verbose) {
        super(verbose);
    }

    @Override
    protected void addWarehouseCell(ArrayList<Character> row, int rowIx, int colIx, char item) {
        if (item == ROBOT) {
            row.add(ROBOT);
            row.add(SPACE);
            robot = new int[]{rowIx, colIx * 2};
        } else if (item == BOX) {
            row.add(BOX_LEFT);
            row.add(BOX_RIGHT);
        } else {
            row.add(item);
            row.add(item);
        }
    }

    @Override
    protected void move(List<Integer> direction) throws CantMoveException, NotARobotException {
        int fromX = robot[0];
        int fromY = robot[1];

        if (warehouse.get(fromX).get(fromY) != ROBOT) {
            throw new NotARobotException();
        }
        HashSet<List<Integer>> itemsToMove = detectMovingItems(fromX, fromY, direction);

        HashMap<List<Integer>, Character> detached = new HashMap<>();

        for (List<Integer> xy : itemsToMove) {
            int currentX = xy.get(0);
            int currentY = xy.get(1);

            int newX = currentX + direction.get(0);
            int newY = currentY + direction.get(1);

            char nextChar = warehouse.get(newX).get(newY);
            detached.put(Arrays.asList(newX, newY), nextChar);

            char currentChar;
            if (detached.containsKey(xy)) {
                currentChar = detached.get(xy);
            } else {
                currentChar = warehouse.get(currentX).get(currentY);
                warehouse.get(currentX).set(currentY, '.');
            }

            warehouse.get(newX).set(newY, currentChar);
        }

        robot[0] += direction.get(0);
        robot[1] += direction.get(1);

        if (verbose)  {
            System.out.println(itemsToMove);
        }
    }

    protected HashSet<List<Integer>> detectMovingItems(int fromX, int fromY, List<Integer> direction) throws CantMoveException {
        if (direction == null) {
            throw new CantMoveException();
        }
        HashSet<List<Integer>> output = new HashSet<>();

        HashSet<List<Integer>> movingRightnow = new HashSet<>();
        movingRightnow.add(Arrays.asList(fromX, fromY));

        while (!movingRightnow.isEmpty()) {
            List<Integer> xy = movingRightnow.iterator().next();
            movingRightnow.remove(xy);
            if (output.contains(xy)) {
                continue;
            }
            int x = xy.get(0);
            int y = xy.get(1);
            char currentItem = warehouse.get(x).get(y);
            if (currentItem == WALL) {
                throw new CantMoveException();
            }
            if (currentItem == SPACE) {
                /* nothing to move */
                continue;
            }
            output.add(xy);
            if (direction.getLast() == 0) {
                int newY = y;
                if (currentItem == BOX_LEFT) {
                    newY += 1;
                } else if (currentItem == BOX_RIGHT) {
                    newY -= 1;
                }
                movingRightnow.add(Arrays.asList(x, newY));
            }

            movingRightnow.add(Arrays.asList(x + direction.get(0), y + direction.get(1)));
        }
        return output;
    }

    @Override
    protected int calculateGPS() {
        return super.calculateGPS(BOX_LEFT);
    }
}
