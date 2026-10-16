package dadeniVezbi.courses2026_27.ArraysAndLists;

import dataStructures.DLL;
import dataStructures.DLLNode;

import java.util.Scanner;

/*За дадена двојно поврзана листа од N цели броеви, треба да се најде бројот на елементи такви што просекот на елементите од пред него
во листата е поголем од просекот на елементи после него во листата.
Влез: Првиот број од влезот е бројот на елементи во листата N, а потоа во следниот ред се дадени самите елементи одделени со празно место.
Излез: Бројот на елементи што го задоволуваат условот.

/

For a given doubly-linked list with N integers, you need to find the number of elements such that the average of all elements before it is bigger than the average of all elements after it in the list.

Input: The first number in the input is the number of integers in the list N, then in the next line the elements are given, separated by spaces.

Output: The number of elements that satisfy the condition.

For example:

Input	Result
5
1 2 3 4 5
0
4
5 4 3 2
2
*/
public class DLLElementsWhoseLeftMeanIsBigger {
    static void main() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        DLL<Integer> dll = new DLL<>();
        for(int i = 0; i < n; i++){
            dll.insertLast(sc.nextInt());
        }
        int counter =0;
        DLLNode<Integer> temp = dll.getFirst();
        for(int i = 1; i < n; i++){
            temp = temp.getSucc();
            float left = 0;
            int leftElements=0;
            float right = 0;
            int rightElements=0;
            DLLNode<Integer> l = temp.getPred();
            DLLNode<Integer> r = temp.getSucc();
            while(l != null){
                left += l.getElement();
                leftElements++;
                l = l.getPred();
            }
            while(r != null){
                right += r.getElement();
                rightElements++;
                r = r.getSucc();
            }
            left = leftElements != 0 ?  left / leftElements : 0;
            right = rightElements != 0 ?  right / rightElements : 0;
            if(left > right){
                counter++;
            }

        }
        System.out.print(counter);
    }
}
