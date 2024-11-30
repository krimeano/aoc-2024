import days.day00.Day001;
import days.day00.Day002;
import days.my_lib.SolveDay;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public static void main(String[] args) {
    int result;
    SolveDay day;
    {
        day = new Day001(false);
        result = day.solve("1 2\n3 4\n");
        System.out.println(result);
    }

    {
        day = new Day002(false);
        result = day.solve("1 2\n3 4\n");

        System.out.println(result);

    }
}
