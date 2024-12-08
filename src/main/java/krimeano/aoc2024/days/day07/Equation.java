package krimeano.aoc2024.days.day07;

import java.util.ArrayList;
import java.util.Iterator;

public class Equation {
    public boolean verbose = false;
    public int testValue;
    public int prefixValue = 0;
    public int currentValue = 0;
    public ArrayList<Integer> suffixItems;
    public int suffixSum = 0;
    public String stringRepresentation = "";

    public Equation(boolean verbose, String input) throws ParseEquationException {
        this.verbose = verbose;
        if (verbose) {
            System.out.println();
        }
        String[] parts = input.split(": ");
        if (parts.length != 2) {
            throw new ParseEquationException();
        }
        try {
            testValue = Integer.parseInt(parts[0]);
            prefixValue = 0;
            currentValue = 0;
            suffixItems = new ArrayList<>();
            suffixSum = 0;

            for (String strItem : parts[1].split(" ")) {
                int item = Integer.parseInt(strItem);
                if (currentValue == 0) {
                    stringRepresentation += item;
                    currentValue = item;
                } else {
                    suffixItems.add(item);
                    suffixSum += item;
                }
            }
        } catch (NumberFormatException e) {
            throw new ParseEquationException(e.getMessage());
        }

        System.out.println("testValue: " + testValue);
        System.out.println("currentValue: " + currentValue);
        System.out.println("suffixItems: " + suffixItems);
        System.out.println("suffixSum: " + suffixSum);
    }

    public Equation(boolean verbose, int testValue, int prefixValue, int currentValue, ArrayList<Integer> suffixItems, int suffixSum, String stringRepresentation) {
        this.verbose = verbose;
        this.testValue = testValue;
        this.prefixValue = prefixValue;
        this.currentValue = currentValue;
        this.suffixItems = suffixItems;
        this.suffixSum = suffixSum;
        this.stringRepresentation = stringRepresentation;
    }

    public boolean calibrates() {
        if (suffixItems.isEmpty()) {
            boolean result = prefixValue + currentValue == testValue;
            if (verbose) {
                String eq = result ? " == " : " != ";
                System.out.println(stringRepresentation + eq + testValue);
                if (result) {
                    System.out.println("CALIBRATES!");
                }
            }
            return result;
        }
        if (prefixValue + currentValue + suffixSum > testValue) {
            if (verbose) {
                System.out.println(stringRepresentation + " + " + suffixItems + " > " + testValue);
            }

            return false;
        }
        Iterator<Integer> iterator;
        int firstItem;
        ArrayList<Integer> remainderItems;

        /* add */
        iterator = suffixItems.iterator();
        firstItem = iterator.next();
        remainderItems = new ArrayList<>();
        while (iterator.hasNext()) {
            remainderItems.add(iterator.next());
        }

        Equation a = new Equation(
                verbose,
                testValue,
                prefixValue + currentValue,
                firstItem,
                remainderItems,
                suffixSum - firstItem,
                stringRepresentation + " + " + firstItem
        );

        /* mul */
        iterator = suffixItems.iterator();
        firstItem = iterator.next();
        remainderItems = new ArrayList<>();
        while (iterator.hasNext()) {
            remainderItems.add(iterator.next());
        }
        Equation b = new Equation(
                verbose,
                testValue,
                prefixValue,
                currentValue * firstItem,
                remainderItems,
                suffixSum - firstItem,
                stringRepresentation + " * " + firstItem
        );

        return a.calibrates() || b.calibrates();
    }

}
