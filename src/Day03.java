import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day03 {

    final static String INPUT_PATH = "input/day03.txt";

    final static int NUM_LINES = 140; // 140
    final static int NUM_CHARS_PER_LINE = 140;
    final static boolean VERBOSE = false;

    public static void main(String[] args) {
        try {
            Scanner s = new Scanner(new File(INPUT_PATH));
            char[][] chars = new char[NUM_LINES][NUM_CHARS_PER_LINE];

            int i = 0;

            while (s.hasNextLine()) {
                String line = s.nextLine();
                chars[i] = line.toCharArray();
                i++;
            }

            solution(chars);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    private static void solution(char[][] chars) {
        int n = chars.length;
        int m = chars[0].length;

        int sum = 0;

        HashMap<String, LinkedList<Integer>> gears = new HashMap<>();

        for (int row = 0; row < n; row++) {
            StringBuilder sb = new StringBuilder();
            HashSet<String> currentGears = new HashSet<>();
            boolean adj = false;
            if (VERBOSE) System.out.print(row+1 + ": ");
            for (int col = 0; col <= m; col++) {
                if (col < m && Main.isDigit(chars[row][col])) {
                    if (hasAdjSymbol(row, col, chars)) adj = true;
                    addAdjGears(row, col, chars, currentGears);
                    sb.append(chars[row][col]);
                } else {
                    if (adj) {
                        if (VERBOSE) System.out.print(sb + ", ");
                        int val = Integer.parseInt(sb.toString());
                        sum += val;

                        for (String s : currentGears) {
                            if (gears.containsKey(s)) {
                                LinkedList<Integer> linkedList = gears.get(s);
                                linkedList.add(val);
                            } else {
                                LinkedList<Integer> linkedList = new LinkedList<>();
                                linkedList.add(val);
                                gears.put(s, linkedList);
                            }
                        }
                    }

                    currentGears = new HashSet<>();
                    sb = new StringBuilder();
                    adj = false;
                }
            }
            if (VERBOSE) System.out.println();
        }

        int gearSum = 0;
        for (HashMap.Entry<String, LinkedList<Integer>> entry : gears.entrySet()) {
            LinkedList<Integer> linkedList = entry.getValue();
            if (linkedList.size() == 2) {
                int gearRatio = 1;
                for (int i : linkedList) {
                    gearRatio *= i;
                }
                gearSum += gearRatio;
            }
            if (VERBOSE) {
                System.out.print(entry.getKey() + ": ");
                for (int i : linkedList) {
                    System.out.print(i + ", ");
                }
                System.out.println();
            }
        }

        System.out.println("Sum of part numbers: " + sum);
        System.out.println("Sum of gear ratios: " + gearSum);
    }

    private static void addAdjGears(int i, int j, char[][] chars, HashSet<String> gears) {
        //LinkedList<String> gears = new LinkedList<String>();
        for (int row = -1; row <= 1; row++) {
            for (int col = -1; col <= 1; col++) {
                if (row == 0 && col == 0) continue;
                if (i+row < 0 || j+col < 0 || i+row >= chars.length || j+col >= chars[0].length) continue;
                if (chars[i+row][j+col] == '*') {
                    gears.add((i+row) + "," + (j+col));
                }
            }
        }
    }

    private static boolean hasAdjSymbol(int i, int j, char[][] chars) {
        boolean topLeft = i > 0 && j > 0 && isSymbol(chars[i - 1][j - 1]);
        boolean topCenter = i > 0 && isSymbol(chars[i-1][j]);
        boolean topRight = i > 0 && j < chars[0].length-1 && isSymbol(chars[i-1][j+1]);
        boolean midLeft = j > 0 && isSymbol(chars[i][j-1]);
        boolean midRight = j < chars[0].length-1 && isSymbol(chars[i][j+1]);
        boolean bottomLeft = i < chars.length-1 && j > 0 && isSymbol(chars[i+1][j-1]);
        boolean bottomCenter = i < chars.length-1 && isSymbol(chars[i+1][j]);
        boolean bottomRight = i < chars.length-1 && j < chars[0].length-1 && isSymbol(chars[i+1][j+1]);
        return topLeft || topCenter || topRight || midLeft || midRight || bottomLeft || bottomCenter || bottomRight;
    }

    private static boolean isSymbol(char c) {
        return !Main.isDigit(c) && c != '.';
    }

}
