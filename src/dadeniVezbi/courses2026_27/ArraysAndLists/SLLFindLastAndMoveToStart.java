package dadeniVezbi.courses2026_27.ArraysAndLists;

import dataStructures.SLL;
import dataStructures.SLLNode;

import java.util.Scanner;

/*Дадена е еднострана поврзана листа чии што јазли содржат по еден String. Дополнително, даден е и уште еден природен број L. Од дадената листа потребно е да се најде последниот јазел којшто содржи String со должина L и да се премести на почеток на листата.

Влез: Во првиот ред од влезот е даден бројот на елементи во листата - N, па во следните N редови се дадени самите елементи (String-ови) од листата. На крај, во последниот ред е даден бројот L.

Излез: На излез треба да се испечати листата пред и после промената.

/

You are given a single linked list with String nodes. Additionally, you are given one more integer L. You need to find the last node that contains a String with exactly L characters, and move it to the front of the list.

Input: In the first line from the input the number of elements in the list is given - N, and then in the next N lines the elements themselves. In the last line, the integer L is given.

Output: The list before and after the transformation


For example:

Input	Result
3
Sky
Blue
Cloud
5
Sky->Blue->Cloud
Cloud->Sky->Blue
*/
public class SLLFindLastAndMoveToStart {

    public static void solve(SLL<String> list, int l){
        SLLNode<String> current = list.getHead();
        SLLNode<String> temp = null;
        while (current != null){
            if(current.getElement().length() == l){
                temp = current;
            }
            current = current.getSucc();
        }
        if(temp != null){
            list.delete(temp);

            temp.setSucc(list.getHead());

            list.setHead(temp);
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        n = sc.nextInt();
        SLL<String> list = new SLL<>();
        for (int i = 0; i < n; i++) {
            list.insertLast(sc.next());
        }
        int l =  sc.nextInt();

        System.out.println(list.toString());
        solve(list, l);
        System.out.println(list.toString());


    }
}
