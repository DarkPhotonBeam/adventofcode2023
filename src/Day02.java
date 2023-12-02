import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class Day02 {
    final static int MAX_RED = 12;
    final static int MAX_GREEN = 13;
    final static int MAX_BLUE = 14;
    final static String INPUT_PATH = "input/day02.txt";


    public static void main(String[] args) {
        try {
            Scanner s = new Scanner(new File(INPUT_PATH));
            int sum = 0;
            int powerSum = 0;
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] splitLine = line.split(":");
                int id = Integer.parseInt(splitLine[0].split(" ")[1]);
                String[] sets = splitLine[1].split(";");
                boolean possible = true;
                int redMax = 0;
                int greenMax = 0;
                int blueMax = 0;
                for (String set : sets) {
                    String[] elements = set.split(",");
                    for (String el : elements) {
                        String[] split = el.trim().split(" ");
                        int value = Integer.parseInt(split[0]);
                        if (Objects.equals(split[1], "red")) {
                            redMax = Math.max(redMax, value);
                            if (value > MAX_RED) possible = false;
                        } else if (Objects.equals(split[1], "green")) {
                            greenMax = Math.max(greenMax, value);
                            if (value > MAX_GREEN) possible = false;
                        } else if (Objects.equals(split[1], "blue")) {
                            blueMax = Math.max(blueMax, value);
                            if (value > MAX_BLUE) possible = false;
                        }
                    }
                }
                int power = redMax * greenMax * blueMax;
                if (possible) sum += id;
                powerSum += power;
            }
            System.out.println("Sum of possibles: " + sum);
            System.out.println("Sum of powers: " + powerSum);
        } catch (FileNotFoundException e) {
            System.out.println("File not found :(");
        }
    }

}
