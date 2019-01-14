import java.util.*;

public class Main {

    static class Record {
        Integer v;
        Integer t0;
        Integer P;
        Integer d;
        Record(int v, int t0, int P, int d) {
            this.v = v;
            this.t0 = t0;
            this.P = P;
            this.d = d;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int n, m, q, s;
            String[] input = sc.nextLine().split(" ");
            n = Integer.valueOf(input[0]);
            m = Integer.valueOf(input[1]);
            q = Integer.valueOf(input[2]);
            s = Integer.valueOf(input[3]);
            if (n == 0) {
                break;
            }
            ArrayList<ArrayList<Record>> e = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                e.add(new ArrayList<>());
            }
            for (int i = 0; i < m; ++i) {
                int u, v, t0, P, d;
                String[] input2 = sc.nextLine().split(" ");
                u = Integer.valueOf(input2[0]);
                v = Integer.valueOf(input2[1]);
                t0 = Integer.valueOf(input2[2]);
                P = Integer.valueOf(input2[3]);
                d = Integer.valueOf(input2[4]);
                e.get(u).add(new Record(v, t0, P, d));
            }
            ArrayList<Integer> dist = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                dist.add(Integer.MAX_VALUE);
            }
            dist.set(s, 0);
            PriorityQueue<int[]> pq = new PriorityQueue<>(100, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return Integer.compare(o2[0], o1[0]);
                }
            });
            pq.add(new int[]{0, s});
            while (!pq.isEmpty()){
                int[] top = pq.poll();
                int w = top[0];
                int u = top[1];
                if (w == dist.get(u)) {
                    for (Record ed : e.get(u)){
                        int dst = Integer.MAX_VALUE;
                        if (ed.t0 >= w && ed.P == 0) {
                            dst = ed.t0 + ed.d;
                        } else if (ed.P > 0) {
                            dst = ed.t0 + Math.max(0, w - ed.t0) / ed.P * ed.P;
                            if (dst < w) {
                                dst += ed.P;
                            }
                            dst += ed.d;
                        } if (dst < dist.get(ed.v)) {
                            dist.set(ed.v, dst);
                            pq.add(new int[]{dst, ed.v});
                        }
                    }
                }
            }

            for (int i = 0; i < q; ++i) {
                int a = Integer.valueOf(sc.nextLine());
                if (dist.get(a).equals(Integer.MAX_VALUE)) {
                    System.out.println("Impossible");
                } else {
                    System.out.println(dist.get(a));
                }
            }
            System.out.println();

        }
    }
}
