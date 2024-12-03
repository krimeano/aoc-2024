package krimeano.aoc2024.days.day03;

import java.util.Vector;

public class Day3x2 extends Day3x1 {

    public Day3x2(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("do()");
        for (String line : getLines(textInput)) {
            stringBuilder.append(line);
        }
        String fullText = stringBuilder.toString();
        int result = 0;
        for (String line : removeDonts(fullText)) {
            if (verbose) {
                System.out.println(line);
            }
            for (Vector<Integer> pair : findMul(line)) {
                result += pair.get(0) * pair.get(1);
            }
        }
        return result;
    }

    private String[] removeDonts(String fullText) {
        String[] lines = fullText.split("don't\\(\\)");

        for (int i = 0; i < lines.length; i++) {
            String[] parts = lines[i].split("do\\(\\)");
            StringBuilder stringBuilder = new StringBuilder();
            if (parts.length > 1) {
                for (int j = 1; j < parts.length; j++) {
                    stringBuilder.append(parts[j]);
                }
            }
            lines[i] = stringBuilder.toString();
        }
        return lines;
    }


}
