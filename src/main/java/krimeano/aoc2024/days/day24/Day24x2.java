package krimeano.aoc2024.days.day24;

import java.util.*;

public class Day24x2 extends Day24x1 {
    Map<String, Set<String>> parentInputs;
    Map<String, String> swappedWires = new HashMap<>();

    public Day24x2(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        readData(textInput);
        sortGates();
        connectWires();
        return -1;
    }

    protected void connectWires() {

        parentInputs = new HashMap<>();

        for (String key : wires.keySet()) {
            parentInputs.put(key, new HashSet<>());
            parentInputs.get(key).add(key);
        }

        for (String key : sortedGateWires) {

            String keyZ = swappedWires.getOrDefault(key, key);

            List<String> gate = gatesMap.get(keyZ);

            String keyX = gate.getFirst();
            String keyY = gate.getLast();

            Set<String> parentsX = parentInputs.getOrDefault(keyX, new HashSet<>());
            Set<String> parentsY = parentInputs.getOrDefault(keyY, new HashSet<>());
            Set<String> parentsZ = new HashSet<>(parentsX);
            parentsZ.addAll(parentsY);

            parentInputs.put(keyZ, parentsZ);
            System.out.println(keyZ + " parents: " + parentsZ.size() + " " + parentsZ);
        }

        System.out.println(gatesMap.size());
    }
}
