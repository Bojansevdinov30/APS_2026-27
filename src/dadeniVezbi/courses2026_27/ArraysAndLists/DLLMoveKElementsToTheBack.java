package dadeniVezbi.courses2026_27.ArraysAndLists;

import dataStructures.DLL;
import dataStructures.DLLNode;

import java.util.Scanner;

/*Дадена е двострано поврзана листа од цели броеви. Дополнително, даден е и уште еден природен број k. Потребно е првите k елементи од листата да се преместат на крај, во истиот редослед.

Влез: Во првиот ред од влезот е даден бројот на елементи во листата - N, па во следните следниот ред самите елементи одделени со празно место. На крај, во последниот ред даден е и природниот број k. (k<=N)

Излез: На излез треба да се испечати листата пред и после промената.
/

You are given a doubly-linked list of integers. Additionally, there is one more natural number k. You need to replace the first k elements of the list to the end, in the same order.

Input: The first number in the input is the number of integers in the list - N, then in the next line the elements are given, separated by spaces. Then, in the last line, the natural number k is given. (k<=N)

Output: The list before and after the transformation

For example:

Input	Result
5
1 2 3 4 5
2
1<->2<->3<->4<->5
3<->4<->5<->1<->2
4
1 3 5 7
4
1<->3<->5<->7
1<->3<->5<->7
*/
public class DLLMoveKElementsToTheBack {

    static void solve(DLL<Integer> dll, int k){
        if(k==0){
            return;
        }
        DLLNode<Integer> temp = dll.getFirst();
        for(int i = 1; i < k; i++){
            temp = temp.getSucc();
        }
        // at this moment temp is the kth element from the start
        // now we need to set it as the last element, and set temp's successor as null
        DLLNode<Integer> last = dll.getLast();
        DLLNode<Integer> first = dll.getFirst();
        dll.setFirst(temp.getSucc());
        temp.getSucc().setPred(null);
        temp.setSucc(null);
        last.setSucc(first);
        dll.setLast(temp);
        last.setPred(null);

        first.setPred(last);

    }

    public static void main(String[] args) {
        int n;
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        DLL<Integer> dll = new DLL<>();
        for (int i = 0; i < n; i++) {
            dll.insertLast(scan.nextInt());
        }
        int k = scan.nextInt();
        k= k%n;

        System.out.println(dll.toString());
        solve(dll, k);
        System.out.println(dll.toString());
    }
}
