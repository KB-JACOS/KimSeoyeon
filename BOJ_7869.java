import java.io.*;
import java.util.*;

public class BOJ_7869 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        double x1 = Double.parseDouble(st.nextToken()), y1 = Double.parseDouble(st.nextToken()), r1 = Double.parseDouble(st.nextToken());
        double x2 = Double.parseDouble(st.nextToken()), y2 = Double.parseDouble(st.nextToken()), r2 = Double.parseDouble(st.nextToken());

        double centerDist = len(x1, y1, x2, y2);

        // 안겹침
        if (centerDist >= r1 + r2) {
            System.out.println("0.000");
            System.exit(0);
        }

        // 포함
        else if(centerDist <= r1 - r2) {
            System.out.printf("%.3f", area(r2));
            System.exit(0);
        }
        else if(centerDist <= r2 - r1) {
            System.out.printf("%.3f", area(r1));
            System.exit(0);
        }

        // 겹침
        else {
            double theta1 = Math.acos(((r1*r1) + (centerDist*centerDist) - (r2*r2))/(2*r1*centerDist));
            double theta2 = Math.acos(((r2*r2) + (centerDist*centerDist) - (r1*r1))/(2*r2*centerDist));

            double sector1 = sectorArea(r1, theta1);
            double triangle1 = triangleArea(r1, theta1);

            double sector2 = sectorArea(r2, theta2);
            double triangle2 = triangleArea(r2, theta2);

            double result = sector1 - triangle1 + sector2 - triangle2;
            System.out.printf("%.3f", result);
            System.exit(0);
        }
    }

    // 두 점의 길이 구하는 함수
    static double len(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    // 반지름으로 원의 넓이 구하는 함수
    static double area(double r) {
        return Math.PI * r * r;
    }

    // 부채꼴 넓이 구하는 함수
    static double sectorArea(double r, double theta) {
        return r * r * theta;
    }

    // 삼각형 넓이 구하는 함수
    static double triangleArea(double r, double theta) {
        return r * r * Math.sin(theta) * Math.cos(theta);
    }
}
