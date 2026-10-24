package dadeniVezbi.courses2026_27.FirstPartialExamExercises;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*На автобуската станица во ФинТаун имало N возрасни и M деца кои што сакале да патуваат заедно за соседниот град МинТаун. Цената на еден билет е 100 денари. Ако некој возрасен сака да патува со K деца, треба да плати еден билет за него и K-1 билети за децата (за едно дете не мора да плаќа билет). Исто така, возрасен може да се вози и сам, во тој случај ќе си плати еден билет за него. Дополнително знаеме дека децата не можат да се возат без да се придружени од некој возрасен.

Во првиот ред од влезот е даден бројот N. Во вториот ред е даден бројот M. Потребно е да пресметате колкав е минималниот и максималниот број на денари што може да ги платат патниците за билети и да ги испечатите во две линии. Во автобусот ќе има најмалку еден возрасен.*/
public class Greedy_FinTaunMinTaun {
    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // vozrasni
        int M = Integer.parseInt(br.readLine()); // deca

        br.close();
        int minimum;
        if (N >= M) { // ako ima poveke vozrasni
            minimum = N * 100; // minimum ke se plati sekoj vozrasen sekako, a decata ke gi dademe da vlezat kaj sekoj roditel po edno dodeka ima deca
        } else { // a ako ima poveke deca
            minimum = (N * 100) + ((M - N) * 100); // site roditeli ke si platat, sekoj roditel ke ima poveke deca i od tie deca za N nema da se plati
        }
        int maximum = (N + M - 1) * 100; // maksimum ke se plati ako na eden vozrasen mu gi dademe site deca - taka ke si platat site vozrasni i site deca osven edno


        System.out.println(minimum);
        System.out.println(maximum);


    }
}
