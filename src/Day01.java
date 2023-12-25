import java.util.ArrayList;
import java.util.List;

public class Day01 {
    public static void main(String[] args) {
        List<String> strings = Utils.readFile("Day01");

        List<String> nums = new ArrayList<>();
        StringBuilder num = new StringBuilder();

        for (String string : strings) {
            for (char c : string.toCharArray()) {
                if (Character.isDigit(c)) {
                    num.append(c);
                }
            }

            nums.add(num.toString());
            num.delete(0, num.length());
        }

        int result = 0;
        for (String s : nums) {
            num.append(s.charAt(0)).append(s.charAt(s.length() - 1));
            result += Integer.parseInt(num.toString());
            num.delete(0, num.length());
        }

        // 54630
        System.out.println(result);
    }
}
