package dadeniVezbi.courses2026_27.ArraysAndLists;

import dataStructures.SLL;
import dataStructures.SLLNode;

import java.util.Scanner;
/*Дадена е еднострана поврзана листа чии што јазли содржат по еден String. Дополнително, даден е и уште еден природен број L. Во дадената листа потребно е сите јазли коишто содржат String со должина L да се поместат на крај на листата, задржувајќи го и оригиналниот распоред на елементите.

Влез: Во првиот ред од влезот е даден бројот на елементи во листата - N, па во следните N редови се дадени самите елементи (String-ови) од листата. На крај, во последниот ред е даден бројот L.

Излез: На излез треба да се испечати листата пред и после промената.*/
public class MoveElementsWithLengthLToTheEnd {
    public static void solve(SLL<String> list, int l){
        SLLNode<String> tmp = list.getHead();
        SLLNode<String> prev = list.getHead();
        for (int i = 0; i < list.size(); i++) {
            if (tmp.getElement().length() == l){
                prev = tmp;
                list.insertLast(tmp.getElement());
                list.delete(tmp);
                tmp = prev.getSucc();
            }
            else tmp = tmp.getSucc();
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        SLL<String>list = new SLL<>();
        for (int i = 0; i < n; i++) {
            list.insertLast(in.next());
        }
        int l = in.nextInt();
        System.out.println(list.toString());
        solve(list,l);
        System.out.println(list.toString());
    }
}
