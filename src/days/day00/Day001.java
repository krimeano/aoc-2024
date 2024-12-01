package days.day00;

import days.my_lib.SolveDay;

import java.util.ArrayList;

public class Day001 extends SolveDay {
    public Day001(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        ArrayList<String> lines = getLines(textInput);
        ArrayList<ArrayList<Integer>> data = new ArrayList<>();
        ArrayList<Integer> row;

        for (String line : lines) {
            row = new ArrayList<>();
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

    protected int det(ArrayList<ArrayList<Integer>> matrix) {
        if (matrix.size() == 1 && matrix.getFirst().size() == 1) {
            return matrix.getFirst().getFirst();
        }

        int multiplier = 1;
        int result = 0;
        ArrayList<Integer> firstRow = matrix.getFirst();

        for (int ix = 0; ix < firstRow.size(); ix++) {

            ArrayList<ArrayList<Integer>> minor = new ArrayList<>();
            for (int jy = 1; jy < matrix.size(); jy++) {
                ArrayList<Integer> row = (ArrayList<Integer>) matrix.get(jy).clone();
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
