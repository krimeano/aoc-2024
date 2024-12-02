package krimeano.aoc2024.days.day00;

import krimeano.aoc2024.days.my_lib.SolveDay;

import java.util.ArrayList;
import java.util.Vector;

public class Day0Part1 extends SolveDay {
    public Day0Part1(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        ArrayList<String> lines = getLines(textInput);
        Vector<Vector<Integer>> data = new Vector<>();
        Vector<Integer> row;

        for (String line : lines) {
            row = new Vector<>();
            for (String word : line.split(" +")) {
                row.add(Integer.parseInt(word));
            }
            data.add(row);
        }

        if (verbose) {
            System.out.println(data);
        }

        return det(data);
    }

    protected int det(Vector<Vector<Integer>> matrix) {
        if (matrix.size() == 1 && matrix.getFirst().size() == 1) {
            return matrix.getFirst().getFirst();
        }

        int multiplier = 1;
        int result = 0;
        Vector<Integer> firstRow = matrix.getFirst();

        for (int ix = 0; ix < firstRow.size(); ix++) {

            Vector<Vector<Integer>> minor = new Vector<>();
            for (int jy = 1; jy < matrix.size(); jy++) {
                Vector<Integer> row = (Vector<Integer>) matrix.get(jy).clone();
                row.remove(ix);
                minor.add(row);
            }
            if (verbose) {
                System.out.println(minor);
            }
            result += multiplier * firstRow.get(ix) * det(minor);
            multiplier *= -1;
        }

        return result;
    }
}
