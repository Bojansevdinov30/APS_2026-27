package dadeniVezbi.courses2026_27.IntroductionToJava;

import java.util.Scanner;

/*За дадена низа од случајни броеви кои се внесуваат од стандарден влез, да се направи преместување на сите нули на почеток на низата.
На стандарден излез да се испечати трансформираната низа.

For a given array of random numbers given from standard input, perform a shift of all zeros at the beginning of the sequence.
Print the transformed array to standard output.
For example:
Input
12
1 9 8 4 0 0 2 7 0 6 0 9
Result
Transformiranata niza e:
0 0 0 0 1 9 8 4 2 7 6 9
*/
public class PushZero {
    static void pushZerosToBeginning(int arr[], int n) {
        for (int i = 0; i < n; i++) {
            if (arr[i] == 0) {
                for (int j = i; j > 0; j--) {
                    arr[j] = arr[j - 1];
                }
                arr[0] = 0;
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n =  input.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = input.nextInt();
        }

        pushZerosToBeginning(arr, n);
        System.out.println("Transformiranata niza e: ");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}