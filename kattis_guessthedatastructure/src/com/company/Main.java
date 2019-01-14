package com.company;

import java.util.*;

class LargestNumFirstComparator implements Comparator<Integer> {
    // Here we want to create a custom comparator that
    // Priority Queue uses to compare its priority
    // By default, java's priority queue uses natural order,
    // which is smaller number has higher priority.
    // This is different than the question on Kattis where larger
    // number has priority.
    // This comparator is for large number priority.
    public int compare(Integer a1, Integer a2) {
        if (a1 < a2) {
            return 1;
        } else if (a1 > a2) {
            return -1;
        } else {
            return 0;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // we initialize a bunch of data structures to validate against
        // the input
        Stack<Integer> virtualStack;
        LinkedList<Integer> virtualQueue;
        PriorityQueue<Integer> virtualPQ;

        // we have these flags to know whether a datastructure has been
        // violated, meaning the case 2 operation output is different from
        // what the datastructure outputs
        int stackViolation;
        int queueViolation;
        int pqViolation;

        Scanner sc = new Scanner(System.in);

        int i;
        int j;
        while(sc.hasNextLine()) {
            i = Integer.parseInt(sc.nextLine());
            // for each of the new example runs, we clear the datastructures
            // and the flags
            virtualStack = new Stack<Integer>();
            virtualQueue = new LinkedList<Integer>();
            virtualPQ = new PriorityQueue<Integer>(10, new LargestNumFirstComparator());
            stackViolation = 0;
            queueViolation = 0;
            pqViolation = 0;
            boolean exceptionCaught = false;
            for (j = 0; j < i; j++) {
                // we read the command, which is two number sperated by a white space
                String command = sc.nextLine();
                String operand = command.split(" ")[0];
                Integer val = Integer.parseInt(command.split(" ")[1]);

                switch (operand) {
                    case "1":
                        // this is a type 1 operation push put x into the bag
                        virtualStack.add(val);
                        virtualQueue.add(val);
                        virtualPQ.add(val);
                        break;
                    case "2":
                        // this is type 2 that get from the bag
                        // if we take things while the bag is empty, we record an exception,
                        // this exception is used to print "impossible"
                        if (virtualStack.size() < 1) {
                            exceptionCaught = true;
                            break;
                        }
                        // we track datastructure violation for each of the possible
                        // data structures
                        if (!val.equals(virtualStack.pop()) && stackViolation == 0) {
                            stackViolation = 1;
                        }
                        if (!val.equals(virtualQueue.removeFirst()) && queueViolation == 0) {
                            queueViolation = 1;
                        }
                        if (!val.equals(virtualPQ.poll()) && pqViolation == 0) {
                            pqViolation = 1;
                        }
                        break;
                }
            }

            if (exceptionCaught) {
                System.out.println("impossible");
            } else if (pqViolation == 0 && stackViolation == 1 && queueViolation == 1) {
                System.out.println("priority queue");
            } else if (pqViolation == 1 && stackViolation == 0 && queueViolation == 1) {
                System.out.println("stack");
            } else if (pqViolation == 1 && stackViolation == 1 && queueViolation == 0) {
                System.out.println("queue");
            } else if (pqViolation + stackViolation + queueViolation < 2) {
                // this is there is less than 2 violations, means the datastructure
                // can be any of the three.
                System.out.println("not sure");
            } else {
                System.out.println("impossible");
            }

        }
    }
}
