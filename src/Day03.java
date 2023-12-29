import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Day03 {
    public static void main(String[] args) {
        List<String> puzzle = Utils.readFile("Day03");
        puzzle.forEach(System.out::println);
        System.out.println();

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

        List<Integer> numbers = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        int counter = 0;
        for (int i = 0; i < engine.length; i++) {
            for (int j = 0; j < engine[i].length; j++) {
                if (symbolSet.contains(engine[i][j])) {
                    if (Character.isDigit(engine[i - 1][j])) {
                        counter = j;
                        while (counter >= 0 && counter < engine[i].length && Character.isDigit(engine[i - 1][counter])) {
                            sb.insert(0, engine[i - 1][counter]);
                            counter--;
                        }

                        counter = j + 1;
                        while (counter < engine[i].length && Character.isDigit(engine[i - 1][counter])) {
                            sb.append(engine[i - 1][counter]);
                            counter++;
                        }

                        numbers.add(Integer.valueOf(sb.toString()));
                        sb.delete(0, sb.length());
                    }
                    if (Character.isDigit(engine[i - 1][j + 1])) {
                        counter = j + 1;
                        while (counter >= 0 && counter < engine[i].length && Character.isDigit(engine[i - 1][counter])) {
                            sb.insert(0, engine[i - 1][counter]);
                            counter--;
                        }

                        counter = j + 2;
                        while (counter < engine[i].length && Character.isDigit(engine[i - 1][counter])) {
                            sb.append(engine[i - 1][counter]);
                            counter++;
                        }

                        numbers.add(Integer.valueOf(sb.toString()));
                        sb.delete(0, sb.length());
                    }
                    if (Character.isDigit(engine[i][j + 1])) {
                        counter = j + 1;
                        while (counter < engine[i].length && Character.isDigit(engine[i][counter])) {
                            sb.append(engine[i][counter]);
                            counter++;
                        }

                        numbers.add(Integer.valueOf(sb.toString()));
                        sb.delete(0, sb.length());
                    }
                    if (Character.isDigit(engine[i + 1][j + 1])) {
                        counter = j + 1;
                        while (counter >= 0 && counter < engine[i].length && Character.isDigit(engine[i + 1][counter])) {
                            sb.insert(0, engine[i + 1][counter]);
                            counter--;
                        }

                        counter = j + 2;
                        while (counter < engine[i].length && Character.isDigit(engine[i + 1][counter])) {
                            sb.append(engine[i + 1][counter]);
                            counter++;
                        }

                        numbers.add(Integer.valueOf(sb.toString()));
                        sb.delete(0, sb.length());
                    }
                    if (Character.isDigit(engine[i + 1][j])) {
                        counter = j;
                        while (counter >= 0 && counter < engine[i].length && Character.isDigit(engine[i + 1][counter])) {
                            sb.insert(0, engine[i + 1][counter]);
                            counter--;
                        }

                        counter = j + 1;
                        while (counter < engine[i].length && Character.isDigit(engine[i + 1][counter])) {
                            sb.append(engine[i + 1][counter]);
                            counter++;
                        }

                        numbers.add(Integer.valueOf(sb.toString()));
                        sb.delete(0, sb.length());
                    }
                    if (Character.isDigit(engine[i + 1][j - 1])) {
                        counter = j - 1;
                        while (counter >= 0 && counter < engine[i].length && Character.isDigit(engine[i + 1][counter])) {
                            sb.insert(0, engine[i + 1][counter]);
                            counter--;
                        }

                        counter = j;
                        while (counter < engine[i].length && Character.isDigit(engine[i + 1][counter])) {
                            sb.append(engine[i + 1][counter]);
                            counter++;
                        }

                        numbers.add(Integer.valueOf(sb.toString()));
                        sb.delete(0, sb.length());
                    }
                    if (Character.isDigit(engine[i][j - 1])) {
                        counter = j - 1;
                        while (counter >= 0 && counter < engine[i].length && Character.isDigit(engine[i][counter])) {
                            sb.insert(0, engine[i][counter]);
                            counter--;
                        }

                        numbers.add(Integer.valueOf(sb.toString()));
                        sb.delete(0, sb.length());
                    }
                    if (/*i - 1 >= 0 && j - 1 >= 0 && */Character.isDigit(engine[i - 1][j - 1])) {
                        counter = j - 1;
                        while (counter >= 0 && counter < engine[i].length && Character.isDigit(engine[i - 1][counter])) {
                            sb.insert(0, engine[i - 1][counter]);
                            counter--;
                        }

                        counter = j;
                        while (counter < engine[i].length && Character.isDigit(engine[i - 1][counter])) {
                            sb.append(engine[i - 1][counter]);
                            counter++;
                        }

                        numbers.add(Integer.valueOf(sb.toString()));
                        sb.delete(0, sb.length());
                    }
                }
            }
        }
        System.out.println(numbers);
    }
}
