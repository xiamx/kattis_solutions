import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    private static HashMap<State, Integer> memo;
    private static Scanner sc;

    public static class State {
        public int needed;
        public int spot;
        public boolean prevl;
        public boolean prevr;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return needed == state.needed &&
                    spot == state.spot &&
                    prevl == state.prevl &&
                    prevr == state.prevr;
        }

        public int hashCode() {
            return Objects.hashCode("n" + String.valueOf(needed) + "s" + String.valueOf(spot) + String.valueOf(prevl) + String.valueOf(prevr));
        }

        State() {
        }
    }


    private static void evaluateScenario(int n, int m) {
        ArrayList<ArrayList<Integer>> v = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            int x = Integer.parseInt(line.split(" ")[0]);
            int y = Integer.parseInt(line.split(" ")[1]);
            ArrayList<Integer> pair = new ArrayList<>(2);
            pair.add(x);
            pair.add(y);
            v.add(pair);
        }
        memo.clear();
        State s = new State();
        s.needed = m;
        s.spot = 0;
        s.prevl = true;
        s.prevr = true;
        int answer = solve(v, n, s);
        System.out.println(answer);
    }

    private static int solve(ArrayList<ArrayList<Integer>> v, int n, State s) {
        if (memo.containsKey(s)){
            return memo.get(s);
        }
        int needed = s.needed;
        int spot = s.spot;
        boolean prevl = s.prevl;
        boolean prevr = s.prevr;
        if (spot >= n) {
            if (needed <= 0) {
                return 0;
            } else {
                return Integer.MIN_VALUE;
            }
        }

        int lval = v.get(spot).get(0);
        int rval = v.get(spot).get(1);

        int ans = Integer.MIN_VALUE;

        if (needed <= 0) {
            State next = new State();
            next.needed = needed;
            next.spot = spot + 1;
            next.prevl = true;
            next.prevr = true;
            ans = lval + rval + solve(v, n, next);
            memo.put(s, ans);
            return ans;
        }

        if (prevl) {
            State next = new State();
            next.needed = needed - 1;
            next.spot = spot + 1;
            next.prevl = true;
            next.prevr = false;
            ans = Math.max(ans, lval + solve(v, n, next));
        }

        if (prevr) {
            State next = new State();
            next.needed = needed - 1;
            next.spot = spot + 1;
            next.prevl = false;
            next.prevr = true;
            ans = Math.max(ans, rval + solve(v, n, next));
        }

        State next = new State();
        next.needed = needed;
        next.spot = spot + 1;
        next.prevl = true;
        next.prevr = true;

        ans = Math.max(ans, lval + rval + solve(v, n, next));
        memo.put(s, ans);
        return ans;
    }

    public static void main(String[] args) {
        memo = new HashMap<>();
        sc = new Scanner(System.in);
        int n;
        int m;

        while (sc.hasNext()) {
            String line = sc.nextLine();
            n = Integer.parseInt(line.split(" ")[0]);
            m = Integer.parseInt(line.split(" ")[1]);
            if (n == 0 && m == 0) {
                break;
            } else {
                evaluateScenario(n, m);
            }
        }
    }
}
