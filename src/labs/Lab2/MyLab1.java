package labs.Lab2;

import java.util.Scanner;

/*Дадени се две еднострано поврзани листи чии јазли содржат цели броеви. Дополнително, даден е и ненегативен реален број x.

За секој јазол од првата листа да се спореди неговата вредност a со вредноста b на јазолот на истата позиција во втората листа. Ако a е надвор од интервалот [b − x, b + x], односно ако |a − b| > x, вредноста во првата листа да се замени со текстот Outlier a, а соодветниот јазол да се избрише од втората листа. Во ознаката се задржува првичната вредност на a.

Споредбите се вршат според првичните позиции на јазлите. Бришењето јазол од втората листа не ги менува паровите што ќе се споредуваат понатаму. Ако разликата е точно x, двата јазла остануваат непроменети.

Ако првата листа има повеќе јазли од втората, вредноста на секој непроверен јазол од првата листа да се замени со Unchecked a, каде што a е неговата првична вредност. Ако втората листа е подолга, нејзините преостанати јазли остануваат непроменети.

По промените да се испечатат двете листи, прво првата, а потоа втората.

Пример за првичните позиции: За првата листа 10->20, втората листа 1->20 и x = 2, резултантната прва листа е Outlier 10->20, а резултантната втора листа е 20.

Објаснување на примерот: Прво се споредуваат јазлите со вредности 10 и 1. Бидејќи нивната разлика е поголема од 2, вредноста 10 во првата листа се заменува со Outlier 10, а јазолот со вредност 1 се брише од втората листа. Потоа се споредуваат јазлите со вредност 20 од двете листи, бидејќи тие биле на истата првична позиција. Нивната разлика е 0, па тие остануваат непроменети.

Влез: Во првиот ред е даден бројот на елементи во првата листа: N (N ≥ 0). Во следните N редови се дадени нејзините елементи. Потоа е даден бројот на елементи во втората листа: M (M ≥ 0), па во следните M редови се дадени нејзините елементи. Во последниот ред е даден реалниот број x.

Излез: Во првиот ред да се испечати првата листа по промените, а во вториот ред втората листа по промените. Елементите се одделуваат со ->. За празна листа да се испечати Prazna lista!!!.*/
class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }
}


class SLL<E> {
    private SLLNode<E> first;

    public SLL() {
        // Construct an empty SLL
        this.first = null;
    }

    public void deleteList() {
        first = null;
    }

    public int size() {
        int listSize = 0;
        SLLNode<E> tmp = first;
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
            SLLNode<E> tmp = first;
            ret += tmp.element;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += "->" + tmp.element;
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public void insertFirst(E o) {
        SLLNode<E> ins = new SLLNode<E>(o, null);
        ins.succ = first;
        //SLLNode<E> ins = new SLLNode<E>(o, first);
        first = ins;
    }

    public void insertAfter(E o, SLLNode<E> node) {
        if (node != null) {
            SLLNode<E> ins = new SLLNode<E>(o, node.succ);
            node.succ = ins;
        } else {
            System.out.println("Dadenot jazol e null");
        }
    }

    public void insertBefore(E o, SLLNode<E> before) {

        if (first != null) {
            SLLNode<E> tmp = first;
            if (first == before) {
                this.insertFirst(o);
                return;
            }
            //ako first!=before
            while (tmp.succ != before && tmp.succ != null)
                tmp = tmp.succ;
            if (tmp.succ == before) {
                tmp.succ = new SLLNode<E>(o, before);
                ;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
    }

    public void insertLast(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            tmp.succ = new SLLNode<E>(o, null);
        } else {
            insertFirst(o);
        }
    }

    public E deleteFirst() {
        if (first != null) {
            SLLNode<E> tmp = first;
            first = first.succ;
            return tmp.element;
        } else {
            System.out.println("Listata e prazna");
            return null;
        }
    }

    public E delete(SLLNode<E> node) {
        if (first != null) {
            SLLNode<E> tmp = first;
            if (first == node) {
                return this.deleteFirst();
            }
            while (tmp.succ != node && tmp.succ.succ != null)
                tmp = tmp.succ;
            if (tmp.succ == node) {
                tmp.succ = tmp.succ.succ;
                return node.element;
            } else {
                System.out.println("Elementot ne postoi vo listata");
                return null;
            }
        } else {
            System.out.println("Listata e prazna");
            return null;
        }

    }

    public SLLNode<E> getFirst() {
        return first;
    }

    public SLLNode<E> find(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
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

    public void merge(SLL<E> in) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            tmp.succ = in.getFirst();
        } else {
            first = in.getFirst();
        }
    }

    public void mirror() {
        if (first != null) {
            //m=nextsucc, p=tmp,q=next
            SLLNode<E> tmp = first;
            SLLNode<E> newsucc = null;
            SLLNode<E> next;

            while (tmp != null) {
                next = tmp.succ;
                tmp.succ = newsucc;
                newsucc = tmp;
                tmp = next;
            }
            first = newsucc;
        }
    }
}


public class MyLab1 {

    static void modifyList(SLL<String> list1, SLL<String> list2, double x) {
        SLLNode<String> temp1 = list1.getFirst();
        SLLNode<String> temp2 = list2.getFirst();
        while(temp1 !=null &&  temp2 != null) {
            long number1 = Long.parseLong(temp1.element);
            long number2 = Long.parseLong(temp2.element);
            if(Math.abs(number1 - number2) > x){
                list1.insertBefore("Outlier " + number1, temp1);
                list1.delete(temp1);
                list2.delete(temp2);
            }
            temp1 = temp1.succ;
            temp2 = temp2.succ;
        }
        while(temp1 != null){
            long number1 = Long.parseLong(temp1.element);
            list1.insertBefore("Unchecked " + number1, temp1);
            list1.delete(temp1);
            temp1 = temp1.succ;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine(); // kako sto bese cin.ignore() vo c++
        SLL<String> list1 = new SLL<String>();
        for (int i = 0; i < n; i++) {
            list1.insertLast(sc.nextLine());
        }
        int m = sc.nextInt();
        sc.nextLine();
        SLL<String> list2 = new SLL<String>();
        for (int i = 0; i < m; i++) {
            list2.insertLast(sc.nextLine());
        }
        double x = sc.nextDouble();

        modifyList(list1, list2, x);
        System.out.println(list1.toString());
        System.out.println(list2.toString());
    }
}
