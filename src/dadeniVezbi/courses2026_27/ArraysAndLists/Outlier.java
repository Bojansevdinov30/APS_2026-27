package dadeniVezbi.courses2026_27.ArraysAndLists;

import dataStructures.SLL;
import dataStructures.SLLNode;

import java.util.Scanner;
/*Дадена е еднострана поврзана листа чии што јазли содржат по еден String. Дополнително, даден е и уште еден природен број L. Во дадената листа пред секој јазол којшто содржи String со должина поголема од L да се вметне нов јазол со вредност "Outlier".

Влез: Во првиот ред од влезот е даден бројот на елементи во листата - N, па во следните N редови се дадени самите елементи (String-ови) од листата. На крај, во последниот ред е даден бројот L.

Излез: На излез треба да се испечати листата пред и после промената.*/
public class Outlier {
    public static void change(SLL<String> list, int length) {
        SLLNode<String> curr = list.getHead();
        SLLNode<String> prev = null;
        if(prev == null && curr != null && curr.getElement().length() > length) {
            list.insertFirst("Outlier");
            prev = curr;
            curr = curr.getSucc();
        }
        while (curr != null) {
            if (curr.getElement().length() > length) {
                prev.setSucc(new SLLNode<String>("Outlier", curr));
            }
            prev = curr;
            curr = curr.getSucc();
        }


    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        SLL<String> list = new SLL<>();
        for (int i = 0; i < n; i++) {
            list.insertLast(input.next());
        }
        int k = input.nextInt();

        System.out.println(list.toString());

        change(list, k);

        System.out.println(list.toString());

    }
}
