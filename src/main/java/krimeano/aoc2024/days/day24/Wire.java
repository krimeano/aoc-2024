package krimeano.aoc2024.days.day24;

import java.util.HashMap;
import java.util.Map;

public class Wire {

    public class UndefinedException extends RuntimeException {
        public UndefinedException(String message) {
            super(message);
        }
    }

    public static class Factory {
        private final Map<String, Wire> wires = new HashMap<>();

        public Wire getWire(String initString) {
            String[] parts = initString.split(": ");
            String key = parts[0];
            Wire wire = wires.getOrDefault(key, new Wire(key));
            if (parts.length == 2) {
                wire.setValue(Integer.parseInt(parts[1]));
            }
            wires.put(key, wire);
            return wire;
        }
    }

    public final String key;
    private int value;
    private boolean defined = false;

    private Wire(String key) {
        this.key = key;
    }

    public void setValue(int value) {
        this.value = value;
        defined = true;
    }

    public int getValue() throws UndefinedException {
        if (defined) {
            return value;
        } else {
            throw new UndefinedException("Wire " + key + " value has not been defined");
        }
    }

    public boolean isDefined() {
        return defined;
    }

    @Override
    public String toString() {
        return String.format("%s:%s", this.key, (defined ? this.value : "?"));
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    public long getBitValue() throws UndefinedException {
        int shift;
        long longValue = this.getValue();
        try {
            shift = Integer.parseInt(key.substring(1));
            return longValue << shift;
        } catch (NumberFormatException e) {
            return longValue;
        }
    }
}
