package krimeano.aoc2024.days.day03;

import krimeano.aoc2024.days.my_lib.SolveDay;

import java.util.Vector;

public class Day3x1 extends SolveDay {

    public Day3x1(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        int result = 0;
        for (String line : getLines(textInput)) {
            if (verbose) {
                System.out.println(line);
            }
            for (Vector<Integer> pair : findMul(line)) {
                result += pair.get(0) * pair.get(1);
            }
        }
        return result;
    }

    private Vector<Vector<Integer>> findMul(String line) {
        Vector<Vector<Integer>> result = new Vector<>();
        int ix = 0;
        int search_state = 0; /* 0 - look for next mul(, 1 - look for comma, 2 - look for trailing ) */
        Vector<Integer> row = new Vector<>();
        while (ix < line.length()) {
            if (search_state == 0) {
                int found = line.indexOf("mul(", ix);
                if (found == -1) {
                    break; /* no data until the end */
                }
                ix = found + 4;
                search_state = 1;
            } else if (search_state == 1) {
                int found = line.indexOf(',', ix);
                if (found == -1) {
                    if (verbose) {
                        System.err.println("can't find \",\"");
                    }
                    break;
                }
                String strValue = line.substring(ix, found);
                try {
                    int value = Integer.parseInt(strValue);
                    row.add(value);
                    ix = found + 1;
                    search_state = 2;
                } catch (NumberFormatException e) {
                    if (verbose) {
                        System.err.println("can't parse \"" + strValue + "\"");
                    }
                    search_state = 0;
                }
            } else if (search_state == 2) {
                int found = line.indexOf(')', ix);
                if (found == -1) {
                    if (verbose) {
                        System.err.println("can't find \")\"");
                    }
                    break;
                }
                String strValue = line.substring(ix, found);
                try {
                    int value = Integer.parseInt(strValue);
                    ix = found + 1;
                    row.add(value);
                    if (row.size() == 2) {
                        result.add(row);
                    } else {
                        if (verbose) {
                            System.err.println("unexpected row size != 2: " + row);
                        }
                    }

                } catch (NumberFormatException e) {
                    if (verbose) {
                        System.err.println("can't parse \"" + strValue + "\"");
                    }
                }
                row = new Vector<>();
                search_state = 0;
            } else {
                System.err.println("Should not happen");
                ix++;
            }
        }
        return result;
    }
}
