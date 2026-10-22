package dadeniVezbi.courses2026_27.Algorithms;

import java.util.Arrays;
import java.util.Scanner;

/*Дадени се N состаноци со време на почеток и крај. Да се најде минималниот број на сали за состаноци потребен за да се одржат сите состаноци.


Влез: Првиот број од влезот е бројот на состаноци N, а потоа во следниот ред минутата на почнување и минутата на завршување на состанокот.



Излез: Најмалиот потребен број на соби за состаноци за да можат да се одржат сите состаноци*/
public class Meetings {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] arrivals = new int[n];
        int[] departures = new int[n];

        for (int i = 0; i < n; i++) {
            arrivals[i] = sc.nextInt();
            departures[i] = sc.nextInt();
        }

        // Sort both arrays
        Arrays.sort(arrivals);
        Arrays.sort(departures);

        int arrivalIndex = 0;
        int departureIndex = 0;

        int platforms = 0;
        int maxPlatforms = 0;

        while (arrivalIndex < n) {

            if (arrivals[arrivalIndex] <= departures[departureIndex]) {

                // A train arrives before the next train departs
                platforms++;
                arrivalIndex++;

                maxPlatforms = Math.max(maxPlatforms, platforms);

            } else {

                // A train has departed, so its platform becomes free
                platforms--;
                departureIndex++;
            }
        }

        System.out.println(maxPlatforms);
    }
}
