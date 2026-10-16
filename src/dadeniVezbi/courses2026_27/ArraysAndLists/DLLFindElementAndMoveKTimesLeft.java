package dadeniVezbi.courses2026_27.ArraysAndLists;

import dataStructures.DLL;
import dataStructures.DLLNode;

import java.util.Scanner;

/*Дадена е двострано поврзана листа од цели броеви. Дополнително, дадени се и уште еден цел број M и еден природен број k. Треба да се најде првото појавување на M во листата и тој број да се помести k места на лево.

Влез: Во првиот ред од влезот е даден бројот на елементи во листата - N, па во следните следниот ред самите елементи одделени со празно место. На крај, во последните два реда дадени се целиот број M и природниот број k.

Излез: На излез треба да се испечати листата пред и после промената.
/

You are given a doubly-linked list of integers. Additionally, there is one more integer M and a natural number k. You need to find the first occurence of M in the list and move that node k times to the left.

Input: The first number in the input is the number of integers in the list - N, then in the next line the elements are given, separated by spaces. Then, in the last two lines, the integer M and the natural number k are given.

Output: The list before and after the transformation

For example:

Input	Result
5
1 2 3 4 5
3
2
1<->2<->3<->4<->5
3<->1<->2<->4<->5
9
5 3 8 6 2 4 1 9 7
4
7
5<->3<->8<->6<->2<->4<->1<->9<->7
5<->3<->8<->6<->2<->1<->9<->4<->7
10
1 2 3 4 5 6 7 8 9 10
11
7
1<->2<->3<->4<->5<->6<->7<->8<->9<->10
Elementot ne postoi vo listata
1<->2<->3<->4<->5<->6<->7<->8<->9<->10
*/
public class DLLFindElementAndMoveKTimesLeft {

    static void solve(DLL<Integer> dll, int m, int k){
        DLLNode<Integer> temp = dll.find(m);
        for(int i=0; i<k; i++){
            DLLNode<Integer> temp2 = temp.getPred();
            dll.delete(temp);
            if(temp2 == null){
                // temp2 = dll.getLast().getPred();
                dll.insertLast(temp.getElement());
                temp.setPred(dll.getLast().getPred());
                temp.setSucc(null);
                dll.setLast(temp);

                continue;
            }
            temp.setPred(temp2.getPred());
            temp.setSucc(temp2);
            dll.insertBefore(temp.getElement(), temp2);
            if(temp.getPred() == null){
                dll.setFirst(temp);
            }
        }
    }

    public static void main(String[] args) {
        int n;
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        DLL<Integer> dll = new DLL<>();
        for (int i = 0; i < n; i++) {
            dll.insertLast(scan.nextInt());
        }
        int m = scan.nextInt();
        int k = scan.nextInt();

        System.out.println(dll.toString());
        solve(dll, m, k);
        System.out.println(dll.toString());
    }
}
