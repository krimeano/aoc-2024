import days.day00.Day001;
import days.day00.Day002;
import days.my_lib.SolveDay;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        int result;
        SolveDay day;

        int currentDay = 0;

        String dayPrefix = currentDay < 10 ? "0" : "";

        String textInput = Files.readString(
                Paths.get(
                        Main.class
                                .getResource(dayPrefix + currentDay + ".txt")
                                .toURI()
                )
        );

        {
            day = new Day001(false);
            result = day.solve(textInput);
            System.out.println(result);
        }

        {
            day = new Day002(false);
            result = day.solve(textInput);

            System.out.println(result);
        }
    }
}
