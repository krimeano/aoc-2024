package days.my_lib;

import java.util.ArrayList;

public abstract class SolveDay implements SolveDayInterface {

    public abstract int solve(String textInput);

    protected boolean verbose;

    public SolveDay(boolean verbose) {
        this.verbose = verbose;
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
