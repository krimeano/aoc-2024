package days;

import days.day00.Day001;
import days.day00.Day002;
import days.day01.Day011;
import days.day01.Day012;
import days.my_lib.NoDayException;
import days.my_lib.SolveDay;

public class SolutionFactory {

    public static SolveDay[] getDay(int day) throws NoDayException {
        return getDay(day, false);
    }

    public static SolveDay[] getDay(int day, boolean verbose) throws NoDayException {
        switch (day) {
            case 0:
                return new SolveDay[]{new Day001(verbose), new Day002(verbose)};
            case 1:
                return new SolveDay[]{new Day011(verbose), new Day012(verbose)};
            default:
                throw new NoDayException("Day " + day + "not found");
        }

    }
}
