package krimeano.aoc2024;

import krimeano.aoc2024.days.SolutionFactory;
import krimeano.aoc2024.days.my_lib.NoDayException;
import krimeano.aoc2024.days.my_lib.SolveDay;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
class Main {
    private static final boolean DEBUG = true;

    public static void main(String[] args) throws IOException, URISyntaxException, NoDayException {
        int currentDay = 2;

        String textInput = loadFile(currentDay);
        for (SolveDay solution : SolutionFactory.getDay(currentDay, DEBUG)) {
            System.out.println(solution.solve(textInput));
        }
    }

    /**
     * @param day int
     * @return String
     * @throws IOException        XXX
     * @throws URISyntaxException XXX
     */
    private static String loadFile(int day) throws IOException, URISyntaxException {
        String dayPrefix = day < 10 ? "0" : "";

        return Files.readString(
                Paths.get(
                        Main.class
                                .getResource("../../" + dayPrefix + day + ".txt")
                                .toURI()
                )
        );

    }
}
