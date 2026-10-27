package labs.Lab2;

import dataStructures.DLL;
import dataStructures.DLLNode;

import java.util.Scanner;

/*Labs 2 Vezba(Ne se na site isti, ova e mojata)

Se koristi DLL<Integer>
Vnesi M elementi vo listata
Dobivas N komandi vo forma na "element fromWhere Positions"
element = nekoj broj(int)
fromWhere = "left"/"right", ako e left pocnuvas od prv node vo lista, pa odis desno(i obratno za "right")
Positions = pozicija na node-ot(long)
Desno od dadeniot node treba da go vneses elementot (insertAfter)

Primer:
Lista: 5, 3, 6
Komanda: 7 left 2
Lista posle komanda: 5, 3, 7, 6

Primer2:
Lista: 3, 2, 8, 9
Komanda: 7 right 2
Lista posle komanda: 3, 2, 8, 7, 9

Posle sve ova se dobiva krajna komanda/input, everyWhich(long), kade sto od desno kon levo vo listata, go brises sekoj x-ti node

Primer
everyWhich = 1
Lista: 5, 8, 3, 6, 9, 0
Lista posle everyWhich komanda: 8, 6, 0

NOTES:
Ako kaj Positions dobies 0, samo vrati se 1 node
Ako dobies Positions pogolem od lista, moras modul, ako ne koristis modul ke izgubis poeni
"Positions" vo vezbata se vikase "afterWhich" msm, ne pamtam ime za promenlivata pa koristev "Positions"
Ako dobies everyWhich koj e ednakov(?) ili pogolem od lista, ne mora da brises nodes
Ako ne e resena labs 1(od doma), ke moras tamu da ja resis pred labs 2
 */
public class MyLab2 {

    static void modify(DLL<Integer> list, int element, String fromWhere, int position){
        if(fromWhere.equals("left")){
            DLLNode<Integer> temp = list.getFirst();
            for (int i = 1; i < position; i++){
                temp = temp.getSucc();
            }
            if(temp != null){
                list.insertAfter(element, temp);
            }
        }else{
            DLLNode<Integer> temp = list.getLast();
            for (int i = 1; i < position; i++){
                temp = temp.getPred();
            }
            if(temp != null){
                list.insertAfter(element, temp);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        DLL<Integer> list = new DLL<Integer>();
        for (int i = 0; i < m; i++) {
            list.insertLast(sc.nextInt());
        }
        int n = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            int element = sc.nextInt();
            String fromWhere = sc.next();
            int position = sc.nextInt();
            position = position % list.length(); // ne moze so m oti dinamicki se menuva dolzinata na listata
            if (position == 0) { // ova e spored teksot na zadacata od prilika = ne e bas vaka ama nema vrska
                position = 1;
            }
            modify(list, element, fromWhere, position);
            System.out.println(list.toString());
        }
        int deletePattern = sc.nextInt();
        int counter = 0;
        DLLNode<Integer> temp = list.getLast();
        for (int i = list.length(); i >0; i--) { // pazi tuka treba list.length a ne m, toa e vazno
            if(counter == deletePattern){
                list.delete(temp);
                counter = 0;
            }else{
                counter++;
            }
            temp = temp.getPred();
        }
        if(counter == deletePattern){
            list.delete(temp);
        }
        // Moze i vaka ovoj del
        /*
        int deletePattern = sc.nextInt();

        int counter = 0;
        DLLNode<Integer> temp = list.getLast();

        while (temp != null) {
            counter++;

            if (counter == deletePattern) {
                DLLNode<Integer> previous = temp.getPred();

                list.delete(temp);

                counter = 0;
                temp = previous;
            } else {
                temp = temp.getPred();
            }
        }*/
        System.out.println(list.toString());

    }
}
