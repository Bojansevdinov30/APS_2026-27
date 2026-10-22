package moiVezbi;

import dataStructures.SLL;
import dataStructures.SLLNode;

import java.util.Scanner;

// 1 ostavi 1 brisi, 1 ostavi 2 brisi, 1 ostavi 3 brisi = Pattern
public class SLL_DeletePatern2 {

    static void deletePattern(SLL<Integer> list) {
        int toDelete = 1;
        SLLNode<Integer> temp = list.getHead().getSucc();
        while (temp != null) {
            for (int i = 0; i < toDelete && temp != null; i++) {
                SLLNode<Integer> node = temp;
                temp = temp.getSucc();
                list.delete(node);
            }
            toDelete++;
            if(temp != null) {
                temp = temp.getSucc();
            }
        }
    }

    static void main() {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        SLL<Integer> list = new SLL<>();

        for (int i = 0; i < n; i++) {
            list.insertLast(sc.nextInt());
        }

        deletePattern(list);

        if (list.getHead() == null) {
            System.out.println("Prazna lista");
        } else {
            System.out.println(list);
        }
    }
}

