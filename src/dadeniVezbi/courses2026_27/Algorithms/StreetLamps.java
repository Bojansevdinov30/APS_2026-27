package dadeniVezbi.courses2026_27.Algorithms;

import java.util.Arrays;
import java.util.Scanner;

/*We are given a street with N possible positions on which we can put a light. A single light will illuminate its own position and 2 more positions to the left and 2 more to the right of it. Our task is to illuminate the street with the minimum possible lights. Not all possible positions must contain light.

Input: The first number in the input is the number of possible positions to put a light bulb N and the length of the street M, then in the next line are the possible positions on which we can put a light.

Output: The minimum lights we need to illuminate the street.



5 5
1 2 3 4 5
1

3 10
3 8 9
2
*/
public class StreetLamps {

    public static int solve(int[] lights, int n, int length) {

        Arrays.sort(lights);

        // Convert positions from 1-based to 0-based
        for (int i = 0; i < n; i++) {
            lights[i]--;
        }

        int nextUncovered = 0;
        int i = 0;
        int numberOfLights = 0;

        while (nextUncovered < length) {

            int bestPosition = -1;

            // Find the furthest lamp that can cover nextUncovered
            while (i < n && lights[i] - 2 <= nextUncovered) {
                bestPosition = lights[i];
                i++;
            }

            // No lamp can cover the next uncovered position
            if (bestPosition == -1) {
                return -1;
            }

            // This lamp covers up to bestPosition + 2
            nextUncovered = bestPosition + 3;

            numberOfLights++;
        }

        return numberOfLights;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] lights = new int[n];

        for (int i = 0; i < n; i++) {
            lights[i] = sc.nextInt();
        }

        System.out.println(solve(lights, n, m));
    }
}
