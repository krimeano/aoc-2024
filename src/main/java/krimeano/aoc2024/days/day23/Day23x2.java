package krimeano.aoc2024.days.day23;

import java.util.*;

public class Day23x2 extends Day23x1 {
    public Day23x2(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        System.out.println(solveSting(textInput));
        return 0;
    }

    public String solveSting(String textInput) {
        findConnections(getLines(textInput));
        Set<Set<String>> knownNetworks = new HashSet<>();
        while (!connections.isEmpty()) {
            String currentComputer = connections.keySet().iterator().next();
            Set<String> computers = connections.get(currentComputer);
            if (verbose) {
                System.out.println(currentComputer + ": " + computers);
            }
            Set<Set<String>> currentComputerNetworks = new HashSet<>();

            {
                Set<String> network = new HashSet<>();
                network.add(currentComputer);
                currentComputerNetworks.add(network);
            }

            if (verbose) {
                System.out.println(currentComputerNetworks);
            }

            while (!computers.isEmpty()) {
                String nextComputer = computers.iterator().next();
                Set<Set<String>> newCurrentComputerNetworks = new HashSet<>(currentComputerNetworks);

                for (Set<String> network : currentComputerNetworks) {

                    boolean connectedToAll = true;

                    for (String computerInNetwork : network) {
                        if (computerInNetwork.equals(currentComputer)) {
                            continue;
                        }
                        if (!connections.get(computerInNetwork).contains(nextComputer)) {
                            connectedToAll = false;
                            break;
                        }
                    }

                    if (connectedToAll) {
                        Set<String> nextNetwork = new HashSet<>(network);
                        nextNetwork.add(nextComputer);
                        newCurrentComputerNetworks.add(nextNetwork);
                        knownNetworks.add(nextNetwork);
                    }
                }

                currentComputerNetworks = newCurrentComputerNetworks;
                computers.remove(nextComputer);
                connections.get(nextComputer).remove(currentComputer);
            }
            if (verbose) {
                System.out.println(currentComputerNetworks);
            }
            // do stuff
            connections.remove(currentComputer);
        }
        int largestNetworkSize = 0;
        List<String> largestNetwork = new ArrayList<>();
        for (Set<String> network : knownNetworks) {
            if (network.size() > largestNetworkSize) {
                largestNetworkSize = network.size();
                largestNetwork = new ArrayList<>(network);
                if (verbose) {
                    System.out.println(network);
                }
            }
        }
        largestNetwork.sort(Comparator.naturalOrder());
        StringBuilder stringBuilder = new StringBuilder();
        boolean isFirst = true;
        for (String computer : largestNetwork) {
            if (isFirst) {
                isFirst = false;
            } else {
                stringBuilder.append(',');
            }
            stringBuilder.append(computer);
        }
        if (verbose) {
            System.out.println("Largest network: " + largestNetwork);
        }
        return stringBuilder.toString();
    }
}
