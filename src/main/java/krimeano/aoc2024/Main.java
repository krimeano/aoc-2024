package krimeano.aoc2024;

import krimeano.aoc2024.days.SolutionFactory;
import krimeano.aoc2024.days.my_lib.NoDayException;
import krimeano.aoc2024.days.my_lib.SolveDay;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

class Main {
    private static final boolean DEBUG = false;

    public static void main(String[] args) {
        int currentDay = 11;

        try {
            String textInput = loadFile(currentDay);
            for (SolveDay solution : SolutionFactory.getDay(currentDay, DEBUG)) {
                System.out.println(solution.solve(textInput));
            }
        } catch (IOException | URISyntaxException error) {
            System.err.println("Can't load file: " + error.getMessage());
        } catch (NoDayException error) {
            System.err.println("Solution not found: " + error.getMessage());
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
                                .getResource("../../input/" + dayPrefix + day + ".txt")
                                .toURI()
                )
        );

    }
}
