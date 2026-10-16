package dadeniVezbi.courses2026_27.ArraysAndLists;

import dataStructures.DLL;
import dataStructures.DLLNode;

import java.util.Scanner;

/*Дадена е двострано поврзана листа од цели броеви. Дополнително, даден е и уште еден природен број k. Елементите во листата треба да се ротираат k пати на лево.

Влез: Во првиот ред од влезот е даден бројот на елементи во листата - N, па во следните следниот ред самите елементи одделени со празно место. На крај, во последниот ред даден е и природниот број k.

Излез: На излез треба да се испечати листата пред и после промената.
/

You are given a doubly-linked list of integers. Additionally, there is one more natural number k. You need to rotate the elements of the list k times to the left.

Input: The first number in the input is the number of integers in the list - N, then in the next line the elements are given, separated by spaces. Then, in the last line, the natural number k is given.

Output: The list before and after the transformation

For example:

Input	Result
5
1 2 3 4 5
2
3<->4<->5<->1<->2
4
1 3 5 7
4
1<->3<->5<->7
*/
public class DLLRotateKTimesLeft {
    static void rotateList(DLL<Integer> dll, int k){
        for(int i = 0; i < k; i++){
            DLLNode<Integer> temp = dll.getFirst();
            dll.setFirst(temp.getSucc());
            dll.insertLast(temp.getElement());
        }
    }

    public static void main(String[] args){
        int n;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        DLL<Integer> dll = new DLL<>();
        for(int i = 0; i < n; i++){
            dll.insertLast(sc.nextInt());
        }
        int k = sc.nextInt();
        k = k%n;
        System.out.println(dll);
        rotateList(dll, k);
        System.out.println(dll);
    }
}
