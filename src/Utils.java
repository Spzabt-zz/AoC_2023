import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils {
    public static List<String> readFile(String fileName) {
        File file = new File("puzzle-input/" + fileName + ".txt");

        List<String> input = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                input.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return input;
    }

    public static void writeToFile(String fileName, List<List<Character>> stringToWrite) {
        File file = new File("puzzle-input/" + fileName + ".txt");

        try (PrintWriter pw = new PrintWriter(file)) {
            for (List<Character> characters : stringToWrite) {
                for (Character character : characters) {
                    pw.print(character);
                }
                pw.println('\n');
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
