import java.util.*;
import java.util.function.IntFunction;

public class Day04 {
    public static void main(String[] args) {
        // List<String> puzzle = Utils.readFile("Day04_test");
        List<String> puzzle = Utils.readFile("Day04_2");

        List<Card> cards = parseCards(puzzle);

        getTotalPoints(cards);

        getTotalScratchcards(cards);
    }

    private static void getTotalScratchcards(List<Card> cards) {
        Map<Integer, Integer> scratchcards = new HashMap<>();

        for (int i = 0; i < cards.size(); i++) {
            List<Integer> cardPoints = new ArrayList<>();
            final int finalI = i;

            cards.get(i).getNumsYouHave().forEach(yourCard -> {
                Integer point = cards.get(finalI).getWinningNums().stream()
                        .filter(winCard -> winCard.equals(yourCard))
                        .findFirst().orElseGet(() -> 0);

                cardPoints.add(point);
            });

            Integer[] matchingNumbers = cardPoints.stream()
                    .filter(point -> point != 0)
                    .toArray(Integer[]::new);

            int counter = 1;
            int id = cards.get(i).getId();
            for (int j = id; j <= id + matchingNumbers.length; j++) {
                if (!scratchcards.containsKey(id))
                    scratchcards.put(id, counter);
                else if (!scratchcards.containsKey(j))
                    scratchcards.put(j, counter);
                else scratchcards.put(j, scratchcards.get(j) + counter);
            }

            if (scratchcards.get(id) > 1) {
                int copyCount = scratchcards.get(id) - 1;

                for (int j = id + 1; j <= id + matchingNumbers.length; j++) {
                    scratchcards.put(j, scratchcards.get(j) + copyCount);
                }
            }
        }

        int totalScratchcards = scratchcards.values().stream()
                .mapToInt(i -> i)
                .sum();

        System.out.println(totalScratchcards);
    }

    private static void getTotalPoints(List<Card> cards) {
        int[] pointsTotal = new int[cards.size()];

        for (int i = 0; i < cards.size(); i++) {

            List<Integer> cardPoints = new ArrayList<>();
            final int finalI = i;

            cards.get(i).getNumsYouHave().forEach(yourCard -> {
                Integer point = cards.get(finalI).getWinningNums().stream()
                        .filter(winCard -> winCard.equals(yourCard))
                        .findFirst().orElseGet(() -> 0);

                cardPoints.add(point);
            });

            Integer gamePoint = cardPoints.stream()
                    .filter(point -> point != 0)
                    .reduce(1, (acc, ignored) -> acc + acc);

            pointsTotal[i] = gamePoint / 2;
        }

        int total = Arrays.stream(pointsTotal).sum();
        System.out.println(total);
    }

    public static List<Card> parseCards(List<String> puzzle) {
        List<Card> cards = new ArrayList<>();

        for (String p : puzzle) {
            String[] puzzleGameId = p.split(":");
            String[] puzzleId = puzzleGameId[0].split(" ");

            int id = Integer.parseInt(puzzleId[puzzleId.length - 1]);

            String[] nums = puzzleGameId[1].split(" ");

            List<Integer> wn = new ArrayList<>();
            List<Integer> nyh = new ArrayList<>();

            int i = 0;
            while(!Objects.equals(nums[i], "|")) {
                if (!nums[i].isEmpty()) {
                    wn.add(Integer.parseInt(nums[i]));
                }

                i++;
            }

            i++;

            while(i < nums.length) {
                if (!nums[i].isEmpty()) {
                    nyh.add(Integer.parseInt(nums[i]));
                }

                i++;
            }

            Card card = new Card(id, wn, nyh);
            cards.add(card);
        }

        return cards;
    }

    public static class Card {
        private int id;
        private List<Integer> winningNums;
        private List<Integer> numsYouHave;

        private Card(int id, List<Integer> winningNums, List<Integer> numsYouHave) {
            this.id = id;
            this.winningNums = winningNums;
            this.numsYouHave = numsYouHave;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<Integer> getWinningNums() {
            return winningNums;
        }

        public void setWinningNums(List<Integer> winningNums) {
            this.winningNums = winningNums;
        }

        public List<Integer> getNumsYouHave() {
            return numsYouHave;
        }

        public void setNumsYouHave(List<Integer> numsYouHave) {
            this.numsYouHave = numsYouHave;
        }

        @Override
        public String toString() {
            return "Card{" +
                    "id=" + id +
                    ", winningNums=" + winningNums +
                    ", numsYouHave=" + numsYouHave +
                    '}';
        }
    }
}
