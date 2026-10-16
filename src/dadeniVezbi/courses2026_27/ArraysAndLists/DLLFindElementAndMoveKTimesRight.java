import java.util.Scanner;
class DLLNode<E extends Comparable<E>> {
    public E element;
    public DLLNode<E> pred;
    public DLLNode<E> succ;
    // staveno e zaradi zadaca 5 od Auds1 - brisenje na duplikati
    protected int numAppearances;

    public DLLNode(E element, DLLNode<E> pred, DLLNode<E> succ) {
        this.element = element;
        this.pred = pred;
        this.succ = succ;
        this.numAppearances = 1;
    }

    @Override
    public String toString() {
        return element.toString();
    }

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public DLLNode<E> getPred() {
        return pred;
    }

    public void setPred(DLLNode<E> pred) {
        this.pred = pred;
    }

    public DLLNode<E> getSucc() {
        return succ;
    }

    public void setSucc(DLLNode<E> succ) {
        this.succ = succ;
    }

    public int getNumAppearances() {
        return numAppearances;
    }

    public void setNumAppearances(int numAppearances) {
        this.numAppearances = numAppearances;
    }
}

class DLL<E extends Comparable<E>> implements Comparable<DLL<E>> {
    private DLLNode<E> first, last;

    public DLL() {
        this.first = null;
        this.last = null;
    }

    public void deleteList() {
        // O(1) complexity
        this.first = null;
        this.last = null;
    }

    public int length() {
        // O(n) complexity
        int ret;
        if (first != null) {
            DLLNode<E> temp = first;
            ret = 1;
            while (temp.succ != null) {
                temp = temp.succ;
                ret++;
            }
            return ret;
        } else {
            return 0;
        }
    }

    public DLLNode<E> find(E o) {
        // O(n) complexity
        if (first != null) {
            DLLNode<E> temp = first;
            while (!(temp.element.equals(o)) && temp.succ != null) {
                temp = temp.succ;
            }
            if (temp.element.equals(o)) {
                return temp;
            } else {
                System.out.println("Elementot ne postoi vo listata");
                return null;
            }

        } else {
            System.out.println("Empty DLL list");
        }
        //tuka mislam treba return null;
        return first;
    }


    public DLLNode<E> insertFirst(E o) {
        // O(1) complexity
        DLLNode<E> ins = new DLLNode<E>(o, null, first);
        if (first == null) {
            last = ins;
        } else {
            first.pred = ins;
        }
        first = ins;
        return ins;
    }

    public DLLNode<E> insertLast(E o) {
        // O(1) complexity
        if (last == null) {
            return insertFirst(o);
        } else {
            DLLNode<E> ins = new DLLNode<E>(o, last, null);
            last.succ = ins;
            last = ins;
            return ins;
        }
    }

    public DLLNode<E> insertAfter(E o, DLLNode<E> after) {
        // O(1) complexity
        if (after == last) {
            return insertLast(o);
        }
        DLLNode<E> ins = new DLLNode<E>(o, after, after.succ);
        after.succ.pred = ins;
        after.succ = ins;
        return ins;
    }

    public void insertBefore(E o, DLLNode<E> before) {
        // O(1) complexity
        if (before == first) {
            insertFirst(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, before.pred, before);
        before.pred.succ = ins;
        before.pred = ins;
    }

    public E deleteFirst() {
        // O(1) complexity
        if (first != null) {
            DLLNode<E> temp = first;
            first = first.succ;
            if (first != null) {
                first.pred = null;
            } else {
                last = null;
            }
            return temp.element;
        } else {
            return null;
        }
    }

    public E deleteLast() {
        // O(1) complexity
        if (first != null) {
            if (first.succ == null) {
                return deleteFirst();
            }
            DLLNode<E> temp = last;
            last = last.pred;
            last.succ = null;
            return temp.element;
        } else {
            return null;
        }
    }

    public E delete(DLLNode<E> node) {
        // O(1) complexity
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

    @Override
    public String toString() {
        // O(n) complexity
        StringBuilder ret = new StringBuilder();
        if (first != null) {
            DLLNode<E> temp = first;
            ret.append(temp).append("<->");
            while (temp.succ != null) {
                temp = temp.succ;
                if (temp.succ != null) {
                    ret.append(temp).append("<->");
                } else {
                    ret.append(temp);
                }
            }
        } else {
            ret.append("Empty DLL list");
        }
        return ret.toString();
    }

    public String toStringR() {
        // O(n) complexity
        StringBuilder ret = new StringBuilder();
        if (last != null) {
            DLLNode<E> temp = last;
            ret.append(temp).append(" <-> ");
            while (temp.pred != null) {
                temp = temp.pred;
                if (temp.pred != null) {
                    ret.append(temp).append(" <-> ");
                } else {
                    ret.append(temp);
                }
            }
        } else {
            ret.append("Empty DLL list");
        }
        return ret.toString();
    }

    public DLLNode<E> getFirst() {
        return first;
    }

    public void setFirst(DLLNode<E> first) {
        this.first = first;
    }

    public DLLNode<E> getLast() {
        return last;
    }

    public void setLast(DLLNode<E> last) {
        this.last = last;
    }

    public void removeDuplicates() {
        if (first != null) {
            DLLNode<E> temp = first;
            DLLNode<E> temp2 = temp.succ;

            while (temp.succ != null) {
                while (temp2 != null) {
                    if (temp.element.compareTo(temp2.element) == 0) {
                        temp.setNumAppearances(temp.getNumAppearances() + 1);
                        if (temp2.succ != null) {
                            temp2 = temp2.succ;
                            this.delete(temp2.pred);
                        } else {
                            this.delete(temp2);
                            temp2 = null;
                        }
                    } else {
                        temp2 = temp2.succ;
                    }
                }
                temp = temp.succ;
                if (temp == null) {
                    break;
                }
                temp2 = temp.succ;
            }
        }
    }

    public void mirror() {
        DLLNode<E> temp = null;
        DLLNode<E> current = first;
        last = first;
        while (current != null) {
            temp = current.pred;
            current.pred = current.succ;
            current.succ = temp;
            current = current.pred;
        }
        // ova e za na kraj da go postavime first, toa e preku temp sto e vtoriot element vaka, pa go stavame first da e prviot
        if (temp != null && temp.pred != null) {
            first = temp.pred;
        }
    }

    @Override
    public int compareTo(DLL<E> o) {
        if(this.length() == o.length()) {
            return 0;
        }else if(this.length() < o.length()) {
            return -1;
        }else{
            return 1;
        }
    }
}
public class DLLFindElementAndMoveKTimesRight {

    static void solve(DLL<Integer> dll, int m, int k){
        DLLNode<Integer> temp = dll.find(m);
        if(temp == null){
            return;
        }
        for(int i = 0; i < k; i++){
            DLLNode<Integer> temp2 = temp.getSucc();
            if(temp2 == null){
                dll.deleteLast();
                temp.setSucc(dll.getFirst());
                temp.setPred(null);
                temp = dll.insertFirst(temp.getElement());
                continue;
            }
            dll.delete(temp);
            temp.setSucc(temp2.getSucc());
            temp.setPred(temp2);
            temp = dll.insertAfter(temp.getElement(), temp2);
            if(temp.getSucc() == null){
                dll.setLast(temp);
            }
        }
    }

    public static void main(String[] args) {
        int n;
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        DLL<Integer> dll = new DLL<>();
        for (int i = 0; i < n; i++) {
            dll.insertLast(scan.nextInt());
        }
        int m = scan.nextInt();
        int k = scan.nextInt();

        System.out.println(dll.toString());
        solve(dll, m, k);
        System.out.println(dll.toString());
    }
}
    