import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true) {
            String inputL = sc.nextLine();
            int n = Integer.parseInt(inputL.split(" ")[0]);
            int m = Integer.parseInt(inputL.split(" ")[1]);
            if (n == 0 && m == 0){
                break;
            }
            ArrayList<int[]> calls = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                String[] params = sc.nextLine().split(" ");
                int start = Integer.parseInt(params[2]);
                int duration = Integer.parseInt(params[3]);
                calls.add(new int[]{start, start+duration});
            }

            for (int i = 0; i < m; i++) {
                String[] params = sc.nextLine().split(" ");
                int start = Integer.parseInt(params[0]);
                int duration = Integer.parseInt(params[1]);
                int end = start + duration;
                int ans = 0;

                for (int[] call : calls) {
                    if ((call[0] <= start && call[1] > start) || (call[0] < end && call[1] > end) || (call[0] >= start && call[1] <= end)) {
                        ans += 1;
                    }
                }
                System.out.println(ans);
            }
        }
    }
}
