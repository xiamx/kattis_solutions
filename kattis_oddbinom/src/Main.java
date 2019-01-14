import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    static long pow(long base, long exp) {
        long tmp = 1;
        for (long i = 0; i < exp; i++) {
            tmp *= base;
        }
        return tmp;
    }

    public static void main(String[] args) {
        LinkedList<Boolean> bin = new LinkedList<>();
        long in;
        long res = 0;
        long ant = 0;

        Scanner sc = new Scanner(System.in);
        in = sc.nextLong();
        while (in != 0) {
            if (in % 2 == 1) {
                bin.add(true);
                in--;
            } else {
                bin.add(false);
            }
            in /= 2;
        }

        long i = bin.size() - 1;
        while (!bin.isEmpty()){
            if (bin.removeLast()) {
                res += pow(2, ant) * pow(3, i);
                ant++;
            }
            i--;
        }
        System.out.println(res);
    }
}
