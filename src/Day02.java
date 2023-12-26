import java.io.File;
import java.util.*;

public class Day02 {
    public static void main(String[] args) {
        List<String> games = Utils.readFile("Day02");

        Map<Integer, List<Map<String, Integer>>> trackGames = new HashMap<>();

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

            List<Map<String, Integer>> mapList = new ArrayList<>();
            for (String[] strings : list) {

                HashMap<String, Integer> value = new HashMap<>();
                for (String s : strings) {
                    System.out.println(s);
                    String[] s1 = s.split(" ");

                    value.put(s1[1], Integer.valueOf(s1[0]));
                }

                mapList.add(value);
            }

            trackGames.put(
                    Integer.valueOf(gameId[1]),
                    mapList);
        }

        System.out.println(trackGames);

        int sumOfValidIds = getSumOfValidIds(trackGames);

        System.out.println(sumOfValidIds);

        int powerOfGameSets = getPowerOfGameSets(trackGames);

        System.out.println(powerOfGameSets);
    }

    private static int getPowerOfGameSets(Map<Integer, List<Map<String, Integer>>> trackGames) {
        Map<Integer, Map<String, Integer>> minSetOfCubes = new HashMap<>();

        for (Map.Entry<Integer, List<Map<String, Integer>>> integerMapEntry : trackGames.entrySet()) {
            Map<String, Integer> setsOfCubes = new HashMap<>();
            for (Map<String, Integer> stringIntegerMap : integerMapEntry.getValue()) {
                for (Map.Entry<String, Integer> stringIntegerEntry : stringIntegerMap.entrySet()) {
                    if (!setsOfCubes.containsKey(stringIntegerEntry.getKey()))
                        setsOfCubes.put(stringIntegerEntry.getKey(), stringIntegerEntry.getValue());
                    else if (setsOfCubes.get(stringIntegerEntry.getKey()) < stringIntegerEntry.getValue())
                        setsOfCubes.put(stringIntegerEntry.getKey(), stringIntegerEntry.getValue());
                }
            }
            minSetOfCubes.put(integerMapEntry.getKey(), setsOfCubes);
        }

        minSetOfCubes.values().forEach(val -> System.out.println(val.values()));

        return minSetOfCubes.values()
                .stream()
                .mapToInt(stringIntegerMap -> stringIntegerMap.values()
                        .stream()
                        .reduce(1, (multiAcc, gameSetValue) -> multiAcc * gameSetValue)
                ).sum();
    }

    private static int getSumOfValidIds(Map<Integer, List<Map<String, Integer>>> trackGames) {
        int sumOfValidIds = 0;
        int counter = 0;

        for (Map.Entry<Integer, List<Map<String, Integer>>> integerMapEntry : trackGames.entrySet()) {
            for (Map<String, Integer> stringIntegerMap : integerMapEntry.getValue()) {
                for (Map.Entry<String, Integer> stringIntegerEntry : stringIntegerMap.entrySet()) {
                    if (Objects.equals(Cube.RED.lowerCase, stringIntegerEntry.getKey()))
                        if (stringIntegerEntry.getValue() > Cube.RED.value)
                            counter++;
                    if (Objects.equals(Cube.GREEN.lowerCase, stringIntegerEntry.getKey()))
                        if (stringIntegerEntry.getValue() > Cube.GREEN.value)
                            counter++;
                    if (Objects.equals(Cube.BLUE.lowerCase, stringIntegerEntry.getKey()))
                        if (stringIntegerEntry.getValue() > Cube.BLUE.value)
                            counter++;
                }
            }

            if (counter == 0)
                sumOfValidIds += integerMapEntry.getKey();

            counter = 0;
        }
        return sumOfValidIds;
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
