import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day03 {
    public static void main(String[] args) {
        List<String> puzzle = Utils.readFile("Day03");

        Set<Character> symbolSet = Set.of('!', '"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '/', ':', ';', '<', '=', '>', '?', '@', '[', '\\', ']', '^', '_', '`', '{', '|', '}', '~');
        char[][] engine = new char[puzzle.size()][puzzle.get(0).length()];

        for (int i = 0; i < puzzle.size(); i++) {
            for (int j = 0; j < puzzle.get(0).length(); j++) {
                engine[i][j] = puzzle.get(i).charAt(j);
            }
        }

        for (int i = 0; i < engine.length; i++) {
            for (int j = 0; j < engine[i].length; j++) {
                System.out.print(engine[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println();


        for (int i = 0; i < engine.length; i++) {
            for (int j = 0; j < engine[i].length; j++) {
                if (!symbolSet.contains(engine[i][j])) {
                    System.out.print(engine[i][j] + "\t");
                } else {
                    System.out.print("." + "\t");
                }
            }
            System.out.println();
        }
    }
}
