import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(sc.hasNextLine()) {
            String[] line = sc.nextLine().split(" ");
            double r = Double.parseDouble(line[0]);
            float x = Float.parseFloat(line[1]);
            float y = Float.parseFloat(line[2]);

            double d = Math.pow(Math.pow(x, 2) + Math.pow(y, 2), 0.5);

            if (d > r) {
                System.out.println("miss");
                continue;
            }

            double circleArea = Math.PI * Math.pow(r, 2);
            double theta = 2 * Math.acos(d/r);
            double segmentArea = Math.pow(r, 2) * (theta - Math.sin(theta)) / 2;
            System.out.println(circleArea - segmentArea + " " +segmentArea);

        }
    }
}
