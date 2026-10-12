package dadeniVezbi.courses2026_27.ArraysAndLists;

import java.util.Scanner;

/*За дадена низа од N цели броеви, да се избришат елементите со вредност помала од просекот на сите елементи во низата. На пример за
низата 1, 2, 3, 4, 5 просекот е (1 + 2 + 3 + 4 + 5) / 5 = 15 / 5 = 3, што значи треба да се избришат елементите 1 и 2, што значи
низата после оваа трансформација ќе биде 3, 4, 5.

Влез: Првиот број од влезот е бројот на елементи во низата N, а потоа во следниот ред се дадени самите елементи одделени со празно место.

Излез: Низата пред и после направената трансформација.*/
import java.util.Scanner;

public class MeanOfArray {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();
        int[] arr = new int[n];

        int sum = 0;

        for (int i = 0; i < n; i++) {
            arr[i] = input.nextInt();
            sum += arr[i];
        }

        double average = (double) sum / n;

        // Before
        System.out.print("{");
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                System.out.print(",");
            }
            System.out.print(arr[i]);
        }
        System.out.println("}");

        // After
        System.out.print("{");

        boolean first = true;

        for (int i = 0; i < n; i++) {
            if (arr[i] >= average) {

                if (!first) {
                    System.out.print(",");
                }

                System.out.print(arr[i]);
                first = false;
            }
        }

        System.out.println("}");
    }
}
