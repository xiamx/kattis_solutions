import java.security.KeyStore;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<String, Integer> d = new HashMap<>();

        while (sc.hasNextLine()) {
            int total = 0;
            boolean a = true;
            String[] line = sc.nextLine().split(" ");
            if ("clear".equals(line[0])) {
                d.clear();
            } else if ("def".equals(line[0])) {
                d.put(line[1], Integer.parseInt(line[2]));
            } else if ("calc".equals(line[0])) {
                String output = "";
                if (d.get(line[1]) != null) {
                    total += d.get(line[1]);
                } else {
                    a = false;
                }
                int i = 2;
                while (i < line.length - 2) {
                    if (line[i].equals("+") && d.get(line[i + 1]) != null) {
                        total += d.get(line[i + 1]);
                    } else if (line[i].equals("-") && d.get(line[i + 1]) != null) {
                        total -= d.get(line[i + 1]);
                    } else {
                        a = false;
                    }
                    i += 2;
                }
                for (String k : d.keySet()) {
                    if (d.get(k) == total) {
                        output = k;
                    }
                }
                if (a && !output.isEmpty()) {
                    for (int j = 1; j < line.length; j++) {
                        System.out.print(line[j] + " ");
                    }
                    System.out.println(output);
                } else {
                    for (int j = 1; j < line.length; j++) {
                        System.out.print(line[j] + " ");
                    }
                    System.out.println("unknown");
                }
            }
        }
    }
}
