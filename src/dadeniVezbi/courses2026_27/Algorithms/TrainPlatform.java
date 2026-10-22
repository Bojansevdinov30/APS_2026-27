package dadeniVezbi.courses2026_27.Algorithms;

import java.util.*;

/*Дадени се N возови со време на пристигање и поаѓање. Да се најде минималниот број на платформи потребен за да се сместат тие возови.


Влез: Првиот број од влезот е бројот на возови N, а потоа во следниот ред минутата на пристигнување и минутата на поаѓање на возот.



Излез: Најмалиот потребен број на платформи за да се сместат сите возови

5
1 2
1 2
5 10
11 14
15 20

2

5
1 2
2 3
3 4
4 5
5 6

2
*/
public class TrainPlatform {

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
/* Priority queue solution
public class TrainPlatform {

    static class Train {
        private int arrivalTime;
        private int departureTime;

        public Train(int arrivalTime, int departureTime) {
            this.arrivalTime = arrivalTime;
            this.departureTime = departureTime;
        }

        public int getArrivalTime() {
            return arrivalTime;
        }

        public int getDepartureTime() {
            return departureTime;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        ArrayList<Train> trains = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int arrivalTime = sc.nextInt();
            int departureTime = sc.nextInt();

            trains.add(new Train(arrivalTime, departureTime));
        }

        // Sort trains by arrival time
        trains.sort(Comparator.comparingInt(Train::getArrivalTime));

        // Min-heap of departure times
        PriorityQueue<Integer> departures = new PriorityQueue<>();

        int maxPlatforms = 0;

        for (Train train : trains) {

            // Remove all trains that have already departed
            while (!departures.isEmpty()
                    && departures.peek() < train.getArrivalTime()) {
                departures.poll();
            }

            // This train needs a platform
            departures.offer(train.getDepartureTime());

            // Number of currently occupied platforms
            maxPlatforms = Math.max(maxPlatforms, departures.size());
        }

        System.out.println(maxPlatforms);
    }
}*/