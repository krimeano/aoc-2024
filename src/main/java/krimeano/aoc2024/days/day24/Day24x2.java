package krimeano.aoc2024.days.day24;

import java.util.*;

public class Day24x2 extends Day24x1 {
    Map<String, Set<String>> parentInputs;

    public Day24x2(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        readData(textInput);
//        swapWires("z26","dfp");
//        swappedWires = new HashMap<>();
//        swappedWires.put("z26", "dfp");
//        swappedWires.put("dfp", "z26");
        sortGates();
        connectWires();
        return -1;
    }

    private void swapWires(String keyX, String keyY) {
        List<String> tmp = gatesMap.get(keyX);
        gatesMap.put(keyX, gatesMap.get(keyY));
        gatesMap.put(keyY, tmp);
    }

    @Override
    protected void connectWires() {

        parentInputs = new HashMap<>();

        for (String key : wires.keySet()) {
            parentInputs.put(key, new HashSet<>());
            parentInputs.get(key).add(key);
        }

        for (String keyZ : sortedGateWires) {
            List<String> gate = getGate(keyZ);

            String keyX = gate.getFirst();
            String keyY = gate.getLast();

            Set<String> parentsX = parentInputs.getOrDefault(keyX, new HashSet<>());
            Set<String> parentsY = parentInputs.getOrDefault(keyY, new HashSet<>());
            Set<String> parentsZ = new HashSet<>(parentsX);
            parentsZ.addAll(parentsY);
            parentInputs.put(keyZ, parentsZ);
            List<String> parentsSorted = new ArrayList<>(parentsZ);
            parentsSorted.sort(Comparator.naturalOrder());
            if (keyZ.startsWith("z")) {
                System.out.println(keyZ + " parents: " + parentsZ.size() + " " + parentsSorted);
            }

        }

        System.out.println(gatesMap.size());
    }
}
