import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

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

        Map<Integer, Map<String, Integer>> minSetOfCubes = trackGames.entrySet().stream()
                .collect(
                        Collectors.groupingBy(
                                Map.Entry::getKey,
                                Collectors.flatMapping(
                                        entry -> entry.getValue().stream(),
                                        Collectors.flatMapping(
                                                map -> map.entrySet().stream(),
                                                Collectors.toMap(
                                                        Map.Entry::getKey,
                                                        Map.Entry::getValue,
                                                        BinaryOperator.maxBy(Comparator.comparingInt(value -> value))
                                                )
                                        )
                                )));

        return minSetOfCubes.values().stream()
                .mapToInt(stringIntegerMap -> stringIntegerMap.values()
                        .stream()
                        .reduce(1, (multiAcc, gameSetValue) -> multiAcc * gameSetValue)
                ).sum();
    }

    private static int getSumOfValidIds(Map<Integer, List<Map<String, Integer>>> trackGames) {
        final int[] sumOfValidIds = {0};

        trackGames.forEach((key, value) -> {
            boolean isImpossibleGameExists = value.stream()
                    .flatMap(entry -> entry.entrySet().stream())
                    .anyMatch(entry ->
                            (Objects.equals(Cube.RED.lowerCase, entry.getKey()) && entry.getValue() > Cube.RED.value) ||
                                    (Objects.equals(Cube.GREEN.lowerCase, entry.getKey()) && entry.getValue() > Cube.GREEN.value) ||
                                    (Objects.equals(Cube.BLUE.lowerCase, entry.getKey()) && entry.getValue() > Cube.BLUE.value));

            if (!isImpossibleGameExists)
                sumOfValidIds[0] += key;
        });

        return sumOfValidIds[0];
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
