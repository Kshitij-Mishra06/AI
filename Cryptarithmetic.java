import java.util.*;

public class Cryptarithmetic {
    static String[] words = {"SEND", "MORE"};
    static String result = "MONEY";
    static Set<Character> letters = new HashSet<>();
    static Map<Character, Integer> assignment = new HashMap<>();
    static boolean[] used = new boolean[10];

    public static void main(String[] args) {
        for (String word : words) for (char c : word.toCharArray()) letters.add(c);
        for (char c : result.toCharArray()) letters.add(c);
        if (solve(new ArrayList<>(letters), 0)) System.out.println(assignment);
        else System.out.println("No solution.");
    }

    static boolean solve(List<Character> vars, int idx) {
        if (idx == vars.size()) return checkValid();
        for (int d = 0; d <= 9; d++) {
            if (!used[d]) {
                used[d] = true;
                assignment.put(vars.get(idx), d);
                if (solve(vars, idx + 1)) return true;
                assignment.remove(vars.get(idx));
                used[d] = false;
            }
        }
        return false;
    }

    static boolean checkValid() {
        if (assignment.get('S') == 0 || assignment.get('M') == 0) return false; // no leading 0
        int sum = 0;
        for (String word : words) sum += toNumber(word);
        return sum == toNumber(result);
    }

    static int toNumber(String word) {
        int num = 0;
        for (char c : word.toCharArray()) num = num * 10 + assignment.get(c);
        return num;
    }
}
