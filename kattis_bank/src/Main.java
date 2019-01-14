import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numPpl, closing;
        numPpl = sc.nextInt();
        closing = sc.nextInt();
        int[] amount = new int[48];

        for (int i = closing; i >= 0; --i) {
            amount[i] = 0;
        }

        for (int i = 0; i < numPpl; ++i) {
            int a;
            int t;
            a = sc.nextInt();
            t = sc.nextInt();
            for (int q = t; a > 0 && q >= 0; --q) {
                int b = amount[q];
                amount[q] = Math.max(a, b);
                a = Math.min(a, b);
            }
        }

        int ans = 0;
        for (int i = 0; i < closing; ++i) {
            ans += amount[i];
        }

        System.out.println(ans);
    }

}
