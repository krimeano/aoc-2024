package krimeano.aoc2024.days.day09;

import krimeano.aoc2024.days.my_lib.SolveDay;

import java.util.ArrayList;

public class Day9x2 extends SolveDay {
    public Day9x2(boolean verbose) {
        super(verbose);
    }

    @Override
    public int solve(String textInput) {
        String line = textInput.trim();
        int fileId = 0;
        int length;
        boolean isFile = true;
        ArrayList<int[]> disk = new ArrayList<>();

        for (int i = 0; i < line.length(); i++) {
            length = line.charAt(i) - '0';
            disk.add(new int[]{isFile ? fileId : -1, length});
            if (!isFile) {
                fileId++;
            }

            isFile = !isFile;
        }
        disk.add(new int[]{-1, 0});
        printDisk(disk);
        int rightCursor = disk.size() - 2;
        if (verbose)  {
            System.out.println(disk.get(rightCursor)[0]);
        }
        while (rightCursor > 1) {
            int[] file = disk.get(rightCursor);
            length = file[1];
            if (verbose) {
                System.out.println(rightCursor + " > " + file[0] + ":" + file[1]);
            }
            int leftCursor = 1;
            boolean fileWasMoved = false;
            while (leftCursor < rightCursor) {
                int[] space = disk.get(leftCursor);
                if (space[0] != -1) {
                    System.err.println("Should not happen");
                }
                int spaceSize = space[1];

                if (spaceSize >= length) {
                    /* we can move the file */
                    /* decrease found space by file length */
                    space[1] = spaceSize - length;
                    /* increase space in the left of the detached file */
                    disk.get(rightCursor - 1)[1] += length + disk.get(rightCursor + 1)[1];
                    /* remove file and trailing space */
                    disk.remove(rightCursor);
                    disk.remove(rightCursor);
                    /* insert empty space and file */
                    disk.add(leftCursor, file);
                    disk.add(leftCursor, new int[]{-1, 0});
                    fileWasMoved = true;
                    printDisk(disk);
                    break;
                }
                leftCursor += 2;
            }
            if (!fileWasMoved) {
                rightCursor -= 2;
            }
        }
        long result = 0;
        long position = 0;
        long width;
        long volume;
        for (int[] file : disk) {
            fileId = file[0];
            width = file[1];
            if (fileId != -1 && width > 0) {
                volume = fileId * (position * width + width * (width - 1) / 2);
                result += volume;
            }
            position += width;
        }
        System.out.println(result);
        return (int) result;
    }

    protected void printDisk(ArrayList<int[]> disk) {
        if (!verbose) {
            return;
        }
        System.out.print("{ ");
        for (int[] item : disk) {
            System.out.print(item[0] + ":" + item[1] + ", ");
        }
        System.out.println("}");
    }
}
