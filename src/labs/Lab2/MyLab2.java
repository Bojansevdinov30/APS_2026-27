package labs.Lab2;
/*You are given a nonempty doubly linked list of integers. Then N commands of the form element fromWhere howManyPositions are given, where fromWhere is either left or right, and howManyPositions is a positive integer.
For each command, select the node at position howManyPositions using circular counting from the first node for left or from the last node for right. Then insert a new node with value element after the selected node. Count using the current list, including nodes inserted by earlier commands.
After all insertions, a final command is given in the form everyWhich. Starting at the first node and moving right, skip everyWhich nodes and remove the next node. Continue in the same way from the node immediately to the right of the removed node until the end of the list, without wrapping. The remaining nodes retain their order. The value of everyWhich is a positive integer.
Print the list after all N insertion commands, then print it again after applying the final command.
Circular counting explained: For each command, counting starts at 1: at the first node if left is given, or at the last node if right is given. When moving from left to right, continue from the first node after the last; when moving from right to left, continue from the last node after the first. “Before” and “after” refer to the left-to-right order in the printed list, regardless of the counting direction.
Circular counting example: For the list 1<->2<->3 and command 9 left 4, the node after which 9 should be inserted is the one with value 1, giving 1<->9<->2<->3. If 9 right 4 is applied to the same initial list, the node after which 9 should be inserted is the one with value 3, giving 1<->2<->3<->9. After each insertion, the next command uses the updated list length.
Final command example: For the list 1<->2<->3<->4<->5<->6<->7 and command 2, the result is 1<->2<->4<->5<->7.
Final command explained: Starting at the first node, count 2 nodes (the nodes with values 1 and 2), then remove the node with value 3. Continue from the node with value 4: count the nodes with values 4 and 5, then remove the node with value 6. The remaining node with value 7 stays in the list.
Input: The first line contains M ≥ 1, and the next M lines contain the initial list elements. Then N ≥ 0 is given, followed by N lines containing the insertion commands. The last line contains the final command everyWhich.
Output: Print the list after all insertions on the first line and the list after the final command on the second line. Use the supplied .toString() method of the DLL class for both outputs.
For example:
Input	Result
1
4
1
9 left 1
1
4<->9
4
*/
import java.util.*;


class DLLNode<E> {
    protected E element;
    protected DLLNode<E> pred, succ;

    public DLLNode(E elem, DLLNode<E> pred, DLLNode<E> succ) {
        this.element = elem;
        this.pred = pred;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

class DLL<E> {
    private DLLNode<E> first, last;

    public DLL() {
        // Construct an empty SLL
        this.first = null;
        this.last = null;
    }

    public void insertFirst(E o) {
        DLLNode<E> ins = new DLLNode<E>(o, null, first);
        if (first == null)
            last = ins;
        else
            first.pred = ins;
        first = ins;
    }

    public void insertLast(E o) {
        if (first == null)
            insertFirst(o);
        else {
            DLLNode<E> ins = new DLLNode<E>(o, last, null);
            last.succ = ins;
            last = ins;
        }
    }

    public void insertAfter(E o, DLLNode<E> after) {
        if (after == last) {
            insertLast(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, after, after.succ);
        after.succ.pred = ins;
        after.succ = ins;
    }

    public void insertBefore(E o, DLLNode<E> before) {
        if (before == first) {
            insertFirst(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, before.pred, before);
        before.pred.succ = ins;
        before.pred = ins;
    }

    public E deleteFirst() {
        if (first != null) {
            DLLNode<E> tmp = first;
            first = first.succ;
            if (first != null) first.pred = null;
            if (first == null)
                last = null;
            return tmp.element;
        } else
            return null;
    }

    public E deleteLast() {
        if (first != null) {
            if (first.succ == null)
                return deleteFirst();
            else {
                DLLNode<E> tmp = last;
                last = last.pred;
                last.succ = null;
                return tmp.element;
            }
        } else
            return null;
    }

    public E delete(DLLNode<E> node) {
        if (node == first) {
            return deleteFirst();
        }
        if (node == last) {
            return deleteLast();
        }
        node.pred.succ = node.succ;
        node.succ.pred = node.pred;
        return node.element;

    }

    public DLLNode<E> find(E o) {
        if (first != null) {
            DLLNode<E> tmp = first;
            while (!tmp.element.equals(o) && tmp.succ != null)
                tmp = tmp.succ;
            if (tmp.element.equals(o)) {
                return tmp;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
        return null;
    }

    public void deleteList() {
        first = null;
        last = null;
    }

    public int getSize() {
        int listSize = 0;
        DLLNode<E> tmp = first;
        while (tmp != null) {
            listSize++;
            tmp = tmp.succ;
        }
        return listSize;
    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            DLLNode<E> tmp = first;
            ret += tmp.toString();
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += "<->" + tmp.toString();
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public String toStringR() {
        String ret = new String();
        if (last != null) {
            DLLNode<E> tmp = last;
            ret += tmp.toString();
            while (tmp.pred != null) {
                tmp = tmp.pred;
                ret += "<->" + tmp.toString();
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public DLLNode<E> getFirst() {
        return first;
    }

    public DLLNode<E> getLast() {

        return last;
    }

    public void mirror() {

        DLLNode<E> tmp = null;
        DLLNode<E> current = first;
        last = first;
        while (current != null) {
            tmp = current.pred;
            current.pred = current.succ;
            current.succ = tmp;
            current = current.pred;
        }

        if (tmp != null && tmp.pred != null) {
            first = tmp.pred;
        }
    }
}

public class MyLab2 {

    static void modify(DLL<Integer> list, int element, String fromWhere, long howManyPositions) {
        if (fromWhere.equals("left")) {
            DLLNode<Integer> temp = list.getFirst();
            for (int i = 1; i < howManyPositions; i++) {
                temp = temp.succ;
            }
            list.insertAfter(element, temp);
        } else if (fromWhere.equals("right")) {
            DLLNode<Integer> temp = list.getLast();
            for (int i = 1; i < howManyPositions; i++) {
                temp = temp.pred;
            }
            list.insertAfter(element, temp);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DLL<Integer> list = new DLL<Integer>();
        int m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            list.insertLast(sc.nextInt());
        }
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int element = sc.nextInt();
            String fromWhere = sc.next();
            long howManyPositions = sc.nextLong();
            howManyPositions = howManyPositions % list.getSize();
            if(howManyPositions == 0 && fromWhere.equals("left")){
                howManyPositions = list.getSize();
            }else if(howManyPositions == 0 && fromWhere.equals("right")){
                howManyPositions = list.getSize();
            }
            modify(list, element, fromWhere, howManyPositions);
        }
        System.out.println(list.toString());
        long everyWhich = sc.nextLong();
        int counter = 0;
        DLLNode<Integer> temp = list.getFirst();
        while(temp != null){
            if(counter == everyWhich){
                list.delete(temp);
                counter=0;
                temp = temp.succ;
            }else{
                temp = temp.succ;
                counter++;
            }
        }
        System.out.println(list.toString());
    }
}