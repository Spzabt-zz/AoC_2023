import java.io.File;
import java.util.*;

public class Day02 {
    public static void main(String[] args) {
        List<String> games = Utils.readFile("Day02");

        Map<Integer, Map<String, Integer>> trackGames = new HashMap<>();

        StringBuilder sb = new StringBuilder();

        for (String string : games) {
            String[] sets = string.split("; ");
            String[] sets01 = sets[0].split(": ");
            sets[0] = sets01[1];

            String[] gameNameId = sets01[0].split(":");
            String[] gameId = gameNameId[0].split(" ");

            Arrays.stream(sets).forEach(System.out::println);
            System.out.println(gameId[1]);

            List<String[]> list = Arrays.stream(sets).map(set -> set.split(", ")).toList();
            System.out.println();

            HashMap<String, Integer> value = new HashMap<>();
            for (String[] strings : list) {
                for (String s : strings) {
                    System.out.println(s);
                    String[] s1 = s.split(" ");

                    if (!value.containsKey(s1[1]))
                        value.put(s1[1], value.getOrDefault(s1[1], Integer.valueOf(s1[0])));
                    else
                        value.put(s1[1], value.get(s1[1]) + Integer.parseInt(s1[0]));
                }
            }

            trackGames.put(
                    Integer.valueOf(gameId[1]),
                    value);
        }

        System.out.println(trackGames);

        int sumOfValidIds = 0;
        int counter = 0;

        for (Map.Entry<Integer, Map<String, Integer>> integerMapEntry : trackGames.entrySet()) {
            for (Map.Entry<String, Integer> stringIntegerEntry : integerMapEntry.getValue().entrySet()) {
                if (Objects.equals(Cube.RED.lowerCase, stringIntegerEntry.getKey()))
                    if (stringIntegerEntry.getValue() < Cube.RED.value)
                        counter++;
                if (Objects.equals(Cube.GREEN.lowerCase, stringIntegerEntry.getKey()))
                    if (stringIntegerEntry.getValue() < Cube.GREEN.value)
                        counter++;
                if (Objects.equals(Cube.BLUE.lowerCase, stringIntegerEntry.getKey()))
                    if (stringIntegerEntry.getValue() < Cube.BLUE.value)
                        counter++;
            }

            if (counter == 3)
                sumOfValidIds += integerMapEntry.getKey();

            counter = 0;
        }

        System.out.println(sumOfValidIds);
    }

    private enum Cube {
        RED("red", 12), GREEN("green", 13), BLUE("blue", 14);

        private final String lowerCase;
        private final Integer value;

        Cube(String lowerCase, Integer value) {
            this.lowerCase = lowerCase;
            this.value = value;
        }

        public String getLowerCase() {
            return lowerCase;
        }

        public Integer getValue() {
            return value;
        }
    }
}
