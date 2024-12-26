package krimeano.aoc2024.days.day24;

import krimeano.aoc2024.days.my_lib.SolveDay;

import java.util.*;

public class Day24x1 extends SolveDay {
    Wire.Factory wireFactory;

    Set<Wire> inputs;
    Set<Wire> outputs;

    HashMap<Wire, Set<Gate>> wireToGate;
    HashMap<Wire, Gate> wireFromGate;

    public Day24x1(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        inputs = new HashSet<>();
        outputs = new HashSet<>();
        wireFactory = new Wire.Factory();
        wireToGate = new HashMap<>();
        wireFromGate = new HashMap<>();
        readData(textInput);
        connectWires();

        if (verbose) {
            System.out.println(outputs);
        }


        try {
            long result = 0;
            for (Wire wire : outputs) {
                result |= wire.getBitValue();
            }
            System.out.println("Day24x1: " + result);
            return (int) result;
        } catch (Wire.UndefinedException e) {
            System.err.println(e.getMessage());
            return -1;
        }
    }

    protected void readData(String textInput) {
        boolean isInitWires = true;
        for (String line : getLines(textInput, true)) {
            if (line.isEmpty()) {
                isInitWires = false;
                continue;
            }
            if (isInitWires) {
                Wire wire = wireFactory.getWire(line);
                inputs.add(wire);
                if (verbose) {
                    System.out.println(wire);
                }
            } else {
                Gate gate = new Gate(line, wireFactory);
                for (Wire wire : gate.inputs) {
                    wireToGate.putIfAbsent(wire, new HashSet<>());
                    wireToGate.get(wire).add(gate);
                }
                wireFromGate.put(gate.output, gate);
                if (gate.output.key.startsWith("z")) {
                    ;
                    outputs.add(gate.output);
                }
                if (verbose) {
                    System.out.println(gate);
                }
            }
        }
    }

    protected void connectWires() {
        List<Wire> wave = new ArrayList<>(inputs);
        while (!wave.isEmpty()) {
            List<Wire> newWave = new ArrayList<>();
            for (Wire wire : wave) {
                if (wireToGate.containsKey(wire)) {
                    for (Gate gate : wireToGate.get(wire)) {
                        gate.connect();
                        if (gate.output.isDefined() && !newWave.contains(gate.output)) {
                            newWave.add(gate.output);
                        }
                    }
                }
            }
            wave = newWave;
        }
    }
}
