package krimeano.aoc2024.days.day24;

import java.util.ArrayList;
import java.util.List;

public class Day24x2 extends Day24x1 {
    List<OpAmp> chips;

    public Day24x2(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        readData(textInput);
//        swapGates("z12", "kth");
//        swapGates("z26", "gsd");

        chips = new ArrayList<>();
        OpAmp prevChip = null;
        for (int i = 0; i < inputs.size() / 2; i++) {
            OpAmp chip = new OpAmp(i, wireToGate, wireFromGate);
            chips.add(chip);
//            if (prevChip != null) {
//                if (!chip.u.equals(prevChip.w)) {
//                    System.out.println("WRONG INPUT OUTPUT!");
//                }
//            }
            System.out.println(chip);
            prevChip = chip;
        }
        return -1;
    }

//    protected void swapGates(String a, String b) {
//        Set<String> tmpParents = parentsMap.get(a);
//        parentsMap.put(a, parentsMap.get(b));
//        parentsMap.put(b, tmpParents);
//
//        for (String parent : parentsMap.get(a)) {
//            childrenMap.get(parent).remove(b);
//            childrenMap.get(parent).add(a);
//        }
//
//        for (String parent : parentsMap.get(b)) {
//            childrenMap.get(parent).remove(a);
//            childrenMap.get(parent).add(b);
//        }
//    }

}
