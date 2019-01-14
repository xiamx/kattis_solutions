package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Scan for input N until N == 0 (exit condition)
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        while (n != 0) {
            System.out.println(minimalDigitSumSatisfiable(n));
            n = sc.nextInt();
        }
    }

    public static int minimalDigitSumSatisfiable(int n) {
        int m = 11;
        while(true) {
            if (digitSum(m * n) == digitSum(n)) {
                return m;
            } else {
                m += 1;
            }
        }
    }

    public static int digitSum(int n) {
        int digitSum = 0;
        while (n > 0) {
            digitSum += n % 10;
            n /= 10;
        }
        return digitSum;
    }
}
