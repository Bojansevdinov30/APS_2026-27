package dadeniVezbi.courses2026_27.FirstPartialExamEx;
/*Да се најде првото појавување на непарен елемент и последното појавување на парен елемент во двојно поврзана листа со цели позитивни броеви. Потребно е  да се испечатат бараните елементи и нивните позиции во листата, а потоа на нив да им се заменат местата.

На влез прв е даден број N, големината на листата, па потоа се дадени елементите во листата.
На излез прво се печатат непарниот елемент, неговата позиција, потоа парниот елемент и неговата позиција, и на крај се печати целата листа по промената.


Забелешка: Не смеат да се користат други низи или листи освен дадената листа!

Пример:
Влез:
7
3 4 5 6 7 8 9



Излез:
3 0
8 5
8<->4<->5<->6<->7<->3<->9*/

import java.util.Scanner;

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

    public boolean palindrom() {

        DLLNode<E> levo = this.getFirst();
        DLLNode<E> desno = this.getLast();

        while (levo != desno && desno.succ != levo) {

            if (!levo.element.equals(desno.element)) {  //ako se razlicni
                return false;  //vrati false, ne e palindrom
            }

            levo = levo.succ;
            desno = desno.pred;

        }

        return true;

    }

    public void mirror1() {

        DLLNode<E> previous = null;
        DLLNode<E> current = this.first;
        DLLNode<E> next = current.succ;
        last = first;

        while (current != null) {
            current.succ = previous; //prevrtuvanje na node
            current.pred = next;

            //tri cekori za pridvizuvanje kon napred
            previous = current;
            current = next;

            if (next != null) {
                next = next.succ;
            } else {
                first = previous;

            }

        }
    }
}

public class ZadacaZa6 {
    public static void main(String args[]) {

        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        DLL<Integer> lista = new DLL<Integer>();

        for (int i = 0; i < n; i++) {

            int br = in.nextInt();

            lista.insertLast(br);
        }

// Vasheto reshenie treba da bide implementirano tuka.
// Ne go menuvaj ostanatiod kod!
        DLLNode<Integer> head = lista.getFirst();
        DLLNode<Integer> tail = lista.getLast();
        int oddIndex = 0;
        int evenIndex = lista.getSize()-1;
        while (head != null) {
            if(head.element %2 != 0){
                break;
            }
            head = head.succ;
            oddIndex++;
        }
        while (tail != null) {
            if(tail.element %2 == 0){
                break;
            }
            tail = tail.pred;
            evenIndex--;
        }
        System.out.println(head.element + " " + oddIndex);
        System.out.println(tail.element + " " + evenIndex);
        int temp = head.element;
        head.element = tail.element;
        tail.element = temp;

// Your solution should be implemented here
// Do not change the rest of the code!

        System.out.println(lista.toString());


    }

}

