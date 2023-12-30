import java.util.*;

public class Day03 {
    public static void main(String[] args) {
        //part1();
        part2();
    }

    private static void part1() {
        List<String> puzzle = Utils.readFile("Day03_1_test");

        Set<Character> symbolSet = Set.of('!', '"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '/', ':', ';', '<', '=', '>', '?', '@', '[', '\\', ']', '^', '_', '`', '{', '|', '}', '~');
        char[][] engine = new char[puzzle.size()][puzzle.get(0).length()];

        for (int i = 0; i < puzzle.size(); i++) {
            for (int j = 0; j < puzzle.get(0).length(); j++) {
                engine[i][j] = puzzle.get(i).charAt(j);
            }
        }

        List<Integer> numbers = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        int counter;
        for (int i = 0; i < engine.length; i++) {
            for (int j = 0; j < engine[i].length; j++) {
                if (symbolSet.contains(engine[i][j])) {
                    if (Character.isDigit(engine[i - 1][j])) {
                        counter = j;
                        while (counter >= 0 && counter < engine[i].length && Character.isDigit(engine[i - 1][counter])) {
                            sb.insert(0, engine[i - 1][counter]);
                            engine[i - 1][counter] = '.';
                            counter--;
                        }

                        counter = j + 1;
                        while (counter < engine[i].length && Character.isDigit(engine[i - 1][counter])) {
                            sb.append(engine[i - 1][counter]);
                            engine[i - 1][counter] = '.';
                            counter++;
                        }

                        numbers.add(Integer.valueOf(sb.toString()));
                        sb.delete(0, sb.length());
                    }
                    if (Character.isDigit(engine[i - 1][j - 1])) {
                        counter = j - 1;
                        while (counter >= 0 && counter < engine[i].length && Character.isDigit(engine[i - 1][counter])) {
                            sb.insert(0, engine[i - 1][counter]);
                            engine[i - 1][counter] = '.';
                            counter--;
                        }

                        counter = j;
                        while (counter < engine[i].length && Character.isDigit(engine[i - 1][counter])) {
                            sb.append(engine[i - 1][counter]);
                            engine[i - 1][counter] = '.';
                            counter++;
                        }

                        numbers.add(Integer.valueOf(sb.toString()));
                        sb.delete(0, sb.length());
                    }
                    if (Character.isDigit(engine[i - 1][j + 1])) {
                        counter = j + 1;
                        while (counter >= 0 && counter < engine[i].length && Character.isDigit(engine[i - 1][counter])) {
                            sb.insert(0, engine[i - 1][counter]);
                            engine[i - 1][counter] = '.';
                            counter--;
                        }

                        counter = j + 2;
                        while (counter < engine[i].length && Character.isDigit(engine[i - 1][counter])) {
                            sb.append(engine[i - 1][counter]);
                            engine[i - 1][counter] = '.';
                            counter++;
                        }

                        numbers.add(Integer.valueOf(sb.toString()));
                        sb.delete(0, sb.length());
                    }
                    if (Character.isDigit(engine[i][j + 1])) {
                        counter = j + 1;
                        while (counter < engine[i].length && Character.isDigit(engine[i][counter])) {
                            sb.append(engine[i][counter]);
                            engine[i][counter] = '.';
                            counter++;
                        }

                        numbers.add(Integer.valueOf(sb.toString()));
                        sb.delete(0, sb.length());
                    }
                    if (Character.isDigit(engine[i + 1][j])) {
                        counter = j;
                        while (counter >= 0 && counter < engine[i].length && Character.isDigit(engine[i + 1][counter])) {
                            sb.insert(0, engine[i + 1][counter]);
                            engine[i + 1][counter] = '.';
                            counter--;
                        }

                        counter = j + 1;
                        while (counter < engine[i].length && Character.isDigit(engine[i + 1][counter])) {
                            sb.append(engine[i + 1][counter]);
                            engine[i + 1][counter] = '.';
                            counter++;
                        }

                        numbers.add(Integer.valueOf(sb.toString()));
                        sb.delete(0, sb.length());
                    }
                    if (Character.isDigit(engine[i + 1][j + 1])) {
                        counter = j + 1;
                        while (counter >= 0 && counter < engine[i].length && Character.isDigit(engine[i + 1][counter])) {
                            sb.insert(0, engine[i + 1][counter]);
                            engine[i + 1][counter] = '.';
                            counter--;
                        }

                        counter = j + 2;
                        while (counter < engine[i].length && Character.isDigit(engine[i + 1][counter])) {
                            sb.append(engine[i + 1][counter]);
                            engine[i + 1][counter] = '.';
                            counter++;
                        }

                        numbers.add(Integer.valueOf(sb.toString()));
                        sb.delete(0, sb.length());
                    }
                    if (Character.isDigit(engine[i + 1][j - 1])) {
                        counter = j - 1;
                        while (counter >= 0 && counter < engine[i].length && Character.isDigit(engine[i + 1][counter])) {
                            sb.insert(0, engine[i + 1][counter]);
                            engine[i + 1][counter] = '.';
                            counter--;
                        }

                        counter = j;
                        while (counter < engine[i].length && Character.isDigit(engine[i + 1][counter])) {
                            sb.append(engine[i + 1][counter]);
                            engine[i + 1][counter] = '.';
                            counter++;
                        }

                        numbers.add(Integer.valueOf(sb.toString()));
                        sb.delete(0, sb.length());
                    }
                    if (Character.isDigit(engine[i][j - 1])) {
                        counter = j - 1;
                        while (counter >= 0 && counter < engine[i].length && Character.isDigit(engine[i][counter])) {
                            sb.insert(0, engine[i][counter]);
                            engine[i][counter] = '.';
                            counter--;
                        }

                        numbers.add(Integer.valueOf(sb.toString()));
                        sb.delete(0, sb.length());
                    }
                }
            }
        }

        System.out.println(numbers);
        int sum = numbers.stream().mapToInt(i -> i).sum();
        System.out.println(sum);
    }

    public static void part2() {
        List<String> puzzle = Utils.readFile("Day03");

        Set<Character> symbolSet = Set.of('*');
        char[][] engine = new char[puzzle.size()][puzzle.get(0).length()];

        for (int i = 0; i < puzzle.size(); i++) {
            for (int j = 0; j < puzzle.get(0).length(); j++) {
                engine[i][j] = puzzle.get(i).charAt(j);
            }
        }

        List<Integer> numbers = new ArrayList<>();
        List<Integer> productsOfTwoGears = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        int counter;
        final int COUNT_OF_FOUND_GEARS = 2;
        int counterOfFoundGears = 0;
        for (int i = 0; i < engine.length; i++) {
            for (int j = 0; j < engine[i].length; j++) {
                if (symbolSet.contains(engine[i][j])) {
                    if (Character.isDigit(engine[i - 1][j])) {
                        counter = j;
                        while (counter >= 0 && counter < engine[i].length && Character.isDigit(engine[i - 1][counter])) {
                            sb.insert(0, engine[i - 1][counter]);
                            engine[i - 1][counter] = '.';
                            counter--;
                        }

                        counter = j + 1;
                        while (counter < engine[i].length && Character.isDigit(engine[i - 1][counter])) {
                            sb.append(engine[i - 1][counter]);
                            engine[i - 1][counter] = '.';
                            counter++;
                        }

                        numbers.add(Integer.valueOf(sb.toString()));
                        sb.delete(0, sb.length());
                        counterOfFoundGears++;
                    }
                    if (Character.isDigit(engine[i - 1][j - 1])) {
                        counter = j - 1;
                        while (counter >= 0 && counter < engine[i].length && Character.isDigit(engine[i - 1][counter])) {
                            sb.insert(0, engine[i - 1][counter]);
                            engine[i - 1][counter] = '.';
                            counter--;
                        }

                        counter = j;
                        while (counter < engine[i].length && Character.isDigit(engine[i - 1][counter])) {
                            sb.append(engine[i - 1][counter]);
                            engine[i - 1][counter] = '.';
                            counter++;
                        }

                        numbers.add(Integer.valueOf(sb.toString()));
                        sb.delete(0, sb.length());
                        counterOfFoundGears++;
                    }
                    if (Character.isDigit(engine[i - 1][j + 1])) {
                        counter = j + 1;
                        while (counter >= 0 && counter < engine[i].length && Character.isDigit(engine[i - 1][counter])) {
                            sb.insert(0, engine[i - 1][counter]);
                            engine[i - 1][counter] = '.';
                            counter--;
                        }

                        counter = j + 2;
                        while (counter < engine[i].length && Character.isDigit(engine[i - 1][counter])) {
                            sb.append(engine[i - 1][counter]);
                            engine[i - 1][counter] = '.';
                            counter++;
                        }

                        numbers.add(Integer.valueOf(sb.toString()));
                        sb.delete(0, sb.length());
                        counterOfFoundGears++;
                    }
                    if (Character.isDigit(engine[i][j + 1])) {
                        counter = j + 1;
                        while (counter < engine[i].length && Character.isDigit(engine[i][counter])) {
                            sb.append(engine[i][counter]);
                            engine[i][counter] = '.';
                            counter++;
                        }

                        numbers.add(Integer.valueOf(sb.toString()));
                        sb.delete(0, sb.length());
                        counterOfFoundGears++;
                    }
                    if (Character.isDigit(engine[i + 1][j])) {
                        counter = j;
                        while (counter >= 0 && counter < engine[i].length && Character.isDigit(engine[i + 1][counter])) {
                            sb.insert(0, engine[i + 1][counter]);
                            engine[i + 1][counter] = '.';
                            counter--;
                        }

                        counter = j + 1;
                        while (counter < engine[i].length && Character.isDigit(engine[i + 1][counter])) {
                            sb.append(engine[i + 1][counter]);
                            engine[i + 1][counter] = '.';
                            counter++;
                        }

                        numbers.add(Integer.valueOf(sb.toString()));
                        sb.delete(0, sb.length());
                        counterOfFoundGears++;
                    }
                    if (Character.isDigit(engine[i + 1][j + 1])) {
                        counter = j + 1;
                        while (counter >= 0 && counter < engine[i].length && Character.isDigit(engine[i + 1][counter])) {
                            sb.insert(0, engine[i + 1][counter]);
                            engine[i + 1][counter] = '.';
                            counter--;
                        }

                        counter = j + 2;
                        while (counter < engine[i].length && Character.isDigit(engine[i + 1][counter])) {
                            sb.append(engine[i + 1][counter]);
                            engine[i + 1][counter] = '.';
                            counter++;
                        }

                        numbers.add(Integer.valueOf(sb.toString()));
                        sb.delete(0, sb.length());
                        counterOfFoundGears++;
                    }
                    if (Character.isDigit(engine[i + 1][j - 1])) {
                        counter = j - 1;
                        while (counter >= 0 && counter < engine[i].length && Character.isDigit(engine[i + 1][counter])) {
                            sb.insert(0, engine[i + 1][counter]);
                            engine[i + 1][counter] = '.';
                            counter--;
                        }

                        counter = j;
                        while (counter < engine[i].length && Character.isDigit(engine[i + 1][counter])) {
                            sb.append(engine[i + 1][counter]);
                            engine[i + 1][counter] = '.';
                            counter++;
                        }

                        numbers.add(Integer.valueOf(sb.toString()));
                        sb.delete(0, sb.length());
                        counterOfFoundGears++;
                    }
                    if (Character.isDigit(engine[i][j - 1])) {
                        counter = j - 1;
                        while (counter >= 0 && counter < engine[i].length && Character.isDigit(engine[i][counter])) {
                            sb.insert(0, engine[i][counter]);
                            engine[i][counter] = '.';
                            counter--;
                        }

                        numbers.add(Integer.valueOf(sb.toString()));
                        sb.delete(0, sb.length());
                        counterOfFoundGears++;
                    }

                    if (COUNT_OF_FOUND_GEARS != counterOfFoundGears) {
                        Iterator<Integer> iterator = numbers.iterator();;

                        for (int k = 0; k < numbers.size() - counterOfFoundGears; k++) {
                            if (iterator.hasNext()) {
                                iterator.next();
                            }
                        }

                        while (iterator.hasNext()) {
                            iterator.next();
                            iterator.remove();
                        }
                    }

                    if (COUNT_OF_FOUND_GEARS == counterOfFoundGears)
                        productsOfTwoGears.add(numbers.get(numbers.size() - 1) * numbers.get(numbers.size() - 2));

                    counterOfFoundGears = 0;
                }
            }
        }

        System.out.println(numbers);
        System.out.println(productsOfTwoGears);
        Integer gearRatiosSum = productsOfTwoGears.stream().mapToInt(i -> i).sum();
        System.out.println(gearRatiosSum);
    }
}
