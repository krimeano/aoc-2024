package krimeano.aoc2024.days.day05;

import java.util.Comparator;
import java.util.HashMap;

public class ItemsComparator implements Comparator<String> {
    HashMap<String, HashMap<String, Integer>> rules;

    public ItemsComparator(HashMap<String, HashMap<String, Integer>> rules) {
        super();
        this.rules = rules;
    }

    @Override
    public int compare(String o1, String o2) {
        return this.rules.get(o1).get(o2);
    }
}
