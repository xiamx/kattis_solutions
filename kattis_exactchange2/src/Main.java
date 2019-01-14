import javafx.util.Pair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        int cases;
//        cin >> cases;
        int cases = sc.nextInt();

//        while(cases--) {
        while(cases-- > 0) {
//            int n;
//            cin >> n;
            int n = sc.nextInt();
//            vector<int> v(10001, inf);
            ArrayList<Integer> v = new ArrayList<>();
            for(int i = 0; i < 10001; i++) {
                v.add(2 << 28);
            }
//            v[0] = 0;
            v.set(0, 0);
//            int k;
//            cin >> k;
            int k = sc.nextInt();

//            for(int i = 0; i < k; i++) {
            for(int i = 0; i < k; i++) {
//                int coin;
//                cin >> coin;
                int coin = sc.nextInt();

//                for(int j = 10000 - coin; j >= 0; j--) {
//                    v[j+coin] = min(v[j+coin], v[j]+1);
//                }
                for (int j = 10000 - coin; j >= 0; j--) {
                    v.set(j+coin, Math.min(v.get(j+coin), v.get(j)+1));
                }
//            }
            }
//
//            for(int i = n; i <= 10000; i++) {
//                if(v[i] < inf/2) {
//                    cout << i << " " << v[i] << endl;
//                    break;
//                }
//            }
            for (int i = n; i <= 10000; i++) {
                if(v.get(i) < (2 << 28) / 2) {
                    System.out.println(String.valueOf(i) + " " + v.get(i).toString());
                    break;
                }
            }
//
//        }
        }
    }
}
