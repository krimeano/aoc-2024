package krimeano.aoc2024.days.my_lib;

import java.util.ArrayList;
import java.util.Vector;

public abstract class SolveDay implements SolveDayInterface {

    public abstract int solve(String textInput);

    protected boolean verbose;

    public SolveDay(boolean verbose) {
        this.verbose = verbose;
    }


    protected Vector<Vector<Integer>> getMatrix(String textInput) {
        Vector<Vector<Integer>> result = new Vector<>();
        for (String line : getLines(textInput)) {
            Vector<Integer> row = new Vector<>();
            for (String word : line.split(" +")) {
                row.add(Integer.parseInt(word));
            }
            result.add(row);
        }
        return result;
    }

    protected ArrayList<String> getLines(String textInput) {
        return this.getLines(textInput, false);
    }

    protected ArrayList<String> getLines(String textInput, boolean keepEmpty) {
        ArrayList<String> lines = new ArrayList<String>();

        for (String line : textInput.split("\n")) {
            String trimmedLine = line.trim();

            if (keepEmpty || !trimmedLine.isEmpty()) {
                lines.add(trimmedLine);
            }
        }
        return lines;
    }
}
