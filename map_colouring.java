import java.util.*;

public class map_colouring {
    static Map<String, List<String>> adj = new HashMap<>();
    static Map<String, String> color = new HashMap<>();
    static String[] colors = {"Red", "Green", "Blue"};

    public static void main(String[] args) {
        adj.put("A", Arrays.asList("B", "C"));
        adj.put("B", Arrays.asList("A", "C", "D"));
        adj.put("C", Arrays.asList("A", "B", "D"));
        adj.put("D", Arrays.asList("B", "C"));

        if (solve(new ArrayList<>(adj.keySet()), 0)) System.out.println(color);
        else System.out.println("No valid coloring found.");
    }

    static boolean solve(List<String> regions, int idx) {
        if (idx == regions.size()) return true;
        String region = regions.get(idx);
        for (String c : colors) {
            if (isValid(region, c)) {
                color.put(region, c);
                if (solve(regions, idx + 1)) return true;
                color.remove(region);
            }
        }
        return false;
    }

    static boolean isValid(String region, String c) {
        for (String neighbor : adj.get(region)) {
            if (c.equals(color.get(neighbor))) return false;
        }
        return true;
    }
}
