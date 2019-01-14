package com.company;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Integer num1 = sc.nextInt();
        Integer num2 = sc.nextInt();

        Integer num1DigitCount = String.valueOf(num1).length();
        Integer num2DigitCount = String.valueOf(num2).length();

        Integer maxDigitCount = Integer.max(num1DigitCount, num2DigitCount);

        String leftPaddingFormatter = "%0"+ String.valueOf(maxDigitCount) + "d";
        String num1LeftPaddedStr = String.format(leftPaddingFormatter, num1);
        String num2LeftPaddedStr = String.format(leftPaddingFormatter, num2);

        /*
        121
        21

        121
        021
         */
        LinkedList<Character> num1PostCollisionDigits = new LinkedList<>();
        LinkedList<Character> num2PostCollisionDigits = new LinkedList<>();

        int i;
        for (i = 0; i < maxDigitCount; i++) {
            if (num1LeftPaddedStr.charAt(i) > num2LeftPaddedStr.charAt(i)){
                num1PostCollisionDigits.add(num1LeftPaddedStr.charAt(i));
            } else if (num1LeftPaddedStr.charAt(i) < num2LeftPaddedStr.charAt(i)) {
                num2PostCollisionDigits.add(num2LeftPaddedStr.charAt(i));
            } else {
                num1PostCollisionDigits.add(num1LeftPaddedStr.charAt(i));
                num2PostCollisionDigits.add(num2LeftPaddedStr.charAt(i));
            }
        }

        String num1PostCollisoinStr = num1PostCollisionDigits.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(""));

        String num2PostCollisoinStr = num2PostCollisionDigits.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(""));

        if (num1PostCollisionDigits.size() == 0) {
            System.out.println("YODA");
        } else {
            // 00021
            // Integer.parseInt("0021") a 21
            System.out.println(Integer.parseInt(num1PostCollisoinStr));
        }
        if (num2PostCollisionDigits.size() == 0) {
            System.out.println("YODA");
        } else {
            System.out.println(Integer.parseInt(num2PostCollisoinStr));
        }


    }
}
