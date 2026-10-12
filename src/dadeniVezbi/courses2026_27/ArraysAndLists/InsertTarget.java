package dadeniVezbi.courses2026_27.ArraysAndLists;

import dataStructures.SLL;
import dataStructures.SLLNode;

import java.util.Scanner;

/*Дадена е еднострана поврзана листа чии што јазли содржат по еден String. Дополнително, даден е и уште еден природен број L. Во дадената листа после секој јазол којшто содржи String со должина L да се вметне нов јазол со вредност "Target".

Влез: Во првиот ред од влезот е даден бројот на елементи во листата - N, па во следните N редови се дадени самите елементи (String-ови) од листата. На крај, во последниот ред е даден бројот L.

Излез: На излез треба да се испечати листата пред и после промената.*/
public class InsertTarget {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        SLL<String> lst = new SLL<>();
        for (int i = 0; i < n; i++) {
            lst.insertLast(sc.next());
        }
        int k =  sc.nextInt();
        System.out.println(lst.toString());
        SLLNode<String> head = lst.getHead();
        while(head != null){
            if(head.getElement().length() == k){
                SLLNode<String> temp = head.succ;
                head.succ = new SLLNode<>("Target", temp);
                head = temp;
                continue;
            }
            head =  head.succ;
        }
        System.out.println(lst.toString());

    }
}
