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
        swapGates("z12", "kth");
        swapGates("z26", "gsd");
        swapGates("z32", "tbt");
        swapGates("qnf", "vpm");
        /* gsd,kth,qnf,tbt,vpm,z12,z26,z32 */
        chips = new ArrayList<>();
        OpAmp prevChip = null;
        for (int i = 0; i < inputs.size() / 2; i++) {
            OpAmp chip = new OpAmp(i, wireToGate, wireFromGate);
            chips.add(chip);
            if (prevChip != null) {
                if (!chip.u.equals(prevChip.w)) {
                    System.out.println("WRONG INPUT OUTPUT!");
                }
            }
            System.out.println(chip);
            if (i == 26 || i == 32 || i == 36) {
                System.out.println(chip.toVerboseString());
            }
            prevChip = chip;
        }

        return -1;
    }


    protected void swapGates(String a, String b) {
        /* swap outputs in gates */
        wireFromGate.get(a).output = b;
        wireFromGate.get(b).output = a;

        /* then swap gates in outputs map */
        Gate tmpGate = wireFromGate.get(a);
        wireFromGate.put(a, wireFromGate.get(b));
        wireFromGate.put(b, tmpGate);
    }

}
