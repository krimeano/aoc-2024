package krimeano.aoc2024.days.day21;

import java.util.*;

public class Pad {
    private final Map<List<Integer>, Character> directions = new HashMap<>();

    final String[] buttons;
    final int[] size;
    final Map<Character, List<Integer>> positions;
    final List<Integer> startsAt;

    Map<Character, Map<Character, Set<String>>> shortestPaths;

    public Pad(String[] buttons) {
        this.buttons = buttons;
        directions.put(Arrays.asList(0, 1), '>');
        directions.put(Arrays.asList(1, 0), 'v');
        directions.put(Arrays.asList(0, -1), '<');
        directions.put(Arrays.asList(-1, 0), '^');

        size = new int[]{buttons.length, buttons[0].length()};
        positions = getPositions();
        startsAt = positions.get('A');

        shortestPaths = new HashMap<>();
        for (char c : positions.keySet()) {
            findShortestPaths(c);
        }
//        System.out.println(shortestPaths);

    }

    private Map<Character, List<Integer>> getPositions() {
        Map<Character, List<Integer>> out = new HashMap<>();
        for (int row = 0; row < size[0]; row++) {
            for (int col = 0; col < size[1]; col++) {
                out.put(buttons[row].charAt(col), Arrays.asList(row, col));
            }
        }
        return out;
    }


    private void findShortestPaths(char c) {
        if (c == 'X') {
            return;
        }
        Map<Character, Integer> scores = new HashMap<>();
        Map<Character, Set<String>> paths = new HashMap<>();

        for (char x : positions.keySet()) {
            if (x == 'X' || x == c) {
                continue;
            }
            scores.put(x, Integer.MAX_VALUE);
        }

        scores.put(c, 0);
        paths.put(c, new HashSet<>());
        paths.get(c).add("");

        Set<Character> wave = new HashSet<>();

        wave.add(c);

        while (!wave.isEmpty()) {
            char current = wave.iterator().next();
            List<Integer> curPos = positions.get(current);
            wave.remove(current);

            for (List<Integer> direction : directions.keySet()) {
                int row = curPos.get(0) + direction.get(0);
                int col = curPos.get(1) + direction.get(1);
                if (row < 0 || row >= size[0] || col < 0 || col >= size[1]) {
                    continue;
                }
                char neighbour = buttons[row].charAt(col);
                if (neighbour == 'X' || neighbour == c) {
                    continue;
                }

                for (String path : paths.get(current)) {
                    String newPath = path + directions.get(direction);
                    int score = newPath.length();
                    if (score <= scores.get(neighbour)) {
                        paths.putIfAbsent(neighbour, new HashSet<>());
                        if (score < scores.get(neighbour)) {
                            scores.put(neighbour, score);
                            paths.put(neighbour, new HashSet<>());
                        }
                        paths.get(neighbour).add(newPath);
                        wave.add(neighbour);
                    }
                }
            }
        }
        for (char d : paths.keySet()) {
            Set<String> withSuffix = new HashSet<>();
            for (String path : paths.get(d)) {
                withSuffix.add(path + 'A');
            }
            paths.put(d, withSuffix);
        }
        shortestPaths.put(c, paths);
    }

    public Set<String> getPaths(char c) {
        return getPaths(c, 'A');
    }

    public Set<String> getPaths(char c, char fromB) {
        return shortestPaths.get(fromB).get(c);
    }

}
