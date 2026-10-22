package moiVezbi;

import dataStructures.DLL;
import dataStructures.DLLNode;

import java.util.Scanner;

public class DLL_ArmijaUsteEdnas {

    public static void swapIntervals(DLL<Integer> list, int first1, int last1,  int first2, int last2){
        if(first1 > first2){
            int temp = first1;
            first1 = first2;
            first2 = temp;

            temp  = last1;
            last1 = last2;
            last2 = temp;
        }
        DLLNode<Integer> prv1 = null, prv2 = null, posleden1 = null, posleden2 = null;
        DLLNode<Integer> before1 = null, before2 = null, after1 = null, after2 = null;
        DLLNode<Integer> temp = list.getFirst();
        for(int i =0; i<list.length(); i++){
            if(i == first1){
                prv1 = temp;
                before1 = temp.getPred();
            }else if(i == last1){
                posleden1 = temp;
                after1 = temp.getSucc();
            }else if(i == first2){
                prv2 = temp;
                before2 = temp.getPred();
            }else if(i == last2){
                posleden2 = temp;
                after2 = temp.getSucc();
            }
            temp = temp.getSucc();
        }

        if(before1 != null){
            before1.setSucc(prv2);
        }

        prv2.setPred(before1);

        if(after1 != prv2){
            after1.setPred(posleden2);
            posleden2.setSucc(after1);
        }else{
            posleden2.setSucc(prv1);
        }

        posleden1.setSucc(after2);

        if(before2 != posleden1){
            before2.setSucc(prv1);
            prv1.setPred(before2);
        }else{
            prv1.setPred(posleden2);
        }

        if(after2 != null){
            after2.setPred(posleden1);
        }

        if(before1 == null){
            list.setFirst(prv2);
        }

        if(after2 == null){
            list.setLast(posleden1);
        }


    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        DLL<Integer> list = new DLL<>();

        for (int i = 0; i < n; i++) {
            list.insertLast(sc.nextInt());
        }

        int first1 = sc.nextInt();
        int last1 = sc.nextInt();

        int first2 = sc.nextInt();
        int last2 = sc.nextInt();

        swapIntervals(list, first1, last1, first2, last2);

        System.out.println(list);
    }
}
