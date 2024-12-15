package krimeano.aoc2024.days.my_lib;

public class CantMoveException extends Exception {

    public CantMoveException() {
        super("Can't move from the given position");
    }
}
