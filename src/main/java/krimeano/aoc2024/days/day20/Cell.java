package krimeano.aoc2024.days.day20;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cell {
    public final List<Integer> index;
    public Set<List<Integer>> prev;
    public int score;

    public Cell(List<Integer> index) {
        this.index = index;
        initCell(0);
    }

    public Cell(List<Integer> index, int maxScore) {
        this.index = index;
        initCell(maxScore);
    }

    protected void initCell(int maxScore) {
        prev = new HashSet<>();
        score = maxScore;
    }

    public boolean moveFrom(Cell other, int price) {
        int newSteps = other.score + price;
        if (newSteps > score) {
            return false;
        }
        if (newSteps < score) {
            prev = new HashSet<>();
        }
        prev.add(other.index);
        score = newSteps;
        return true;
    }

    @Override
    public String toString() {
        return "(" + prev + ">" + index + "=" + score + ")";
    }
}
