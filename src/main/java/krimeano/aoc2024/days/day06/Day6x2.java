package krimeano.aoc2024.days.day06;

import java.util.ArrayList;

public class Day6x2 extends Day6x1 {

    public Day6x2(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        int[] start = initSolution(textInput);
        ArrayList<String> initialLines = lines;
        int result = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (lines.get(i).charAt(j) != '.') {
                    continue;
                }
                copyLines(initialLines, i, j);
                direction[0] = INIT_DIRECTION[0];
                direction[1] = INIT_DIRECTION[1];
                printLines(lines);
                result += walk(start[0], start[1]);
                if (verbose){
                    System.out.println(visited);
                }
            }
        }
        return result;
    }

    protected void copyLines(ArrayList<String> initialLines, int x, int y) {
        lines = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            String line = initialLines.get(i);
            if (i == x) {
                line = line.substring(0, y) + 'O' + line.substring(y + 1);
            }
            lines.add(line);
        }
    }

}
