package days.day00;

import days.my_lib.SolveDay;

import java.util.ArrayList;

public class Day001 extends SolveDay {
    public Day001(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        ArrayList<String> lines = this.getLines(textInput, true);
        ArrayList<ArrayList<Integer>> data = new ArrayList<>();
        ArrayList<Integer> row;

        for (String line : lines) {
            row = new ArrayList<>();
            for (String word : line.split(" +")) {
                row.add(Integer.parseInt(word));
            }
            data.add(row);
        }

        if (this.verbose) {
            System.out.println(data);
        }

        return 0;
    }
}
