import java.util.*;

class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String specifications = sc.nextLine();
        int numRequests = Integer.parseInt(specifications.split(" ")[0]);
        int maxReqPerServer = Integer.parseInt(specifications.split(" ")[1]);

        PriorityQueue<int[]> pq = new PriorityQueue<>(numRequests,
                Comparator.comparingInt(l -> l[0]));

        for (int i = 0; i < numRequests; i++) {
            int ts = sc.nextInt();
            int[] reqIn = new int[2];
            reqIn[0] = ts;
            reqIn[1] = 1;
            int[] reqOut = new int[2];
            reqOut[0] = ts + 1000;
            reqOut[1] = -1;
            pq.add(reqIn);
            pq.add(reqOut);
        }

        int topSimultaneousRequests = 1;
        int currentSimultaneousRequests = 0;

        while (!pq.isEmpty()) {
            int[] val = pq.poll();
            currentSimultaneousRequests += val[1];
            topSimultaneousRequests = Math.max(currentSimultaneousRequests, topSimultaneousRequests);
        }
        System.out.println((int)Math.ceil((double)topSimultaneousRequests/(double)maxReqPerServer));
    }
}
