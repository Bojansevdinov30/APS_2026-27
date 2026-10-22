package dadeniVezbi.courses2026_27.Algorithms;
import java.util.Scanner;
/*We are given an array of N numbers. Using the method "divide and conquer" write a binary search algorithm that searches for a given number M. If the number is found, print the position of the number in the array. If the number is not in the array, print "Ne postoi".

Input: The first number in the input is the size of the array N, and the number we are searching M. Then in the next line are the array elements.

Output: The position of the number we are searching, or "Ne postoi" if no such number exists in the array.*/
public class BinarySearch {

    public static int binarySearch(int[] arr, int left, int right, int M) {
        if (left > right) {
            return -1;
        }

        int mid = left + (right - left) / 2;

        if (arr[mid] == M) {
            return mid;
        }

        if (arr[mid] > M) {
            return binarySearch(arr, left, mid - 1, M);
        }

        return binarySearch(arr, mid + 1, right, M);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int N = input.nextInt();
        int M = input.nextInt();

        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = input.nextInt();
        }

        int position = binarySearch(arr, 0, N - 1, M);

        if (position == -1) {
            System.out.println("Ne postoi");
        } else {
            System.out.println(position + 1);
        }
    }
}
