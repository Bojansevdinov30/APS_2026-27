package dadeniVezbi.courses2026_27.ArraysAndLists;

import dataStructures.SLL;
import dataStructures.SLLNode;

import java.util.Scanner;

/*Дадена е еднострана поврзана листа чии што јазли содржат по еден String. Дополнително, даден е и уште еден природен број L. Од дадената листа потребно е да се избрише секој јазел којшто содржи String со должина помала од L.

Влез: Во првиот ред од влезот е даден бројот на елементи во листата - N, па во следните N редови се дадени самите елементи (String-ови) од листата. На крај, во последниот ред е даден бројот L.

Излез: На излез треба да се испечати листата пред и после промената.
3
Sky
Blue
Cloud
5
Sky->Blue->Cloud
Cloud
*/
public class SLLDeleteNodeWithLengthLessThenL {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        SLL<String> lst = new SLL<>();
        for (int i = 0; i < n; i++) {
            lst.insertLast(sc.next());
        }
        int k = sc.nextInt();
        System.out.println(lst.toString());
        SLLNode<String> head = lst.getHead();
        while (head != null) {
            if(head.getElement().length() < k){
                lst.delete(head);
            }
            head = head.getSucc();
        }
        System.out.println(lst.toString());
    }
}
