import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day01 {
    final static String INPUT_PATH = "input/day01.txt";
    final static String[] DIGITS = {
            "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
    };

    public static void main(String[] args) {
        part1();
        part2();
    }

    private static void part1() {
        try {
            Scanner s = new Scanner(new File(INPUT_PATH));
            int sum = 0;
            while (s.hasNextLine()) {
                sum += getSumFromLine(s.nextLine());
            }
            System.out.println(sum);
        } catch (FileNotFoundException e) {
            System.out.println("File not found :(");
        }
    }

    private static void part2() {
        try {
            Scanner s = new Scanner(new File(INPUT_PATH));
            int sum = 0;
            int i = 1;
            while (s.hasNextLine()) {
                int lineSum = getCompleteSumFromLine(s.nextLine());
                //System.out.println(i + " Line sum: " + lineSum);
                sum += lineSum;
                i++;
            }
            System.out.println(sum);
        } catch (FileNotFoundException e) {
            System.out.println("File not found :(");
        }
    }

    private static int getSumFromLine(String line) {
        int firstDigit = 0;
        int lastDigit = 0;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (Main.isDigit(c)) {
                if (firstDigit == 0) firstDigit = Character.getNumericValue(c);
                lastDigit = Character.getNumericValue(c);
            }
        }
        return 10 * firstDigit + lastDigit;
    }

    private static int getCompleteSumFromLine(String line) {
        String substring = line;
        String substring2 = line;
        int firstDigit = 0;
        int lastDigit = 0;
        while (substring.length() > 0) {
            boolean found = false;
            for (String s : DIGITS) {
                if (substring.startsWith(s)) {
                    firstDigit = Main.getDigitFromString(s);
                    substring = substring.substring(s.length());
                    found = true;
                    break;
                }
            }
            if (found) break;
            substring = substring.substring(1);
        }
        while (substring2.length() > 0) {
            boolean found = false;
            for (String s : DIGITS) {
                if (substring2.endsWith(s)) {
                    lastDigit = Main.getDigitFromString(s);
                    substring2 = substring2.substring(0, substring2.length() - s.length());
                    found = true;
                    break;
                }
            }
            if (found) break;
            substring2 = substring2.substring(0, substring2.length() - 1);
        }
        return 10 * firstDigit + lastDigit;
    }

}
