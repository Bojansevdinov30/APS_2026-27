package dadeniVezbi.courses2026_27.Algorithms;

import java.util.Scanner;

/*Даден е бинарен стринг S. Бинарен стринг е стринг којшто во себе соджи само ‘1’ и ‘0’. Подстринг на бинарен стринг се нарекува позитивен ако бројот на '1' во подстрингот е строго поголем од бројот на '0'. Да се испечати бројот на позитивни подстрингови за внесениот стринг.

Влез: Во првиот ред на влезот е должината на стрингот N. Во вториот ред е бинарниот стринг S

5
10011

Излез: На излез треба да се испечати бројот на позитивни подстрингови во S

6

Објаснување: За дадениот стринг 10011, позитивни подстрингови се:

1(позиција 0)

1(позиција 3)

1 (позиција 4)

11 (позиции 3 и 4)

011 (позиции 2 до 4)

10011 (целиот стринг)*/
public class BinaryString {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] niza = new int[n];
        for (int i = 0; i < n; i++) {
            niza[i] = input.nextInt();
        }
        int totalCounter = 0;
        for (int i = 0; i < n; i++) {
            int counterZero = 0, counterOne = 0;
            for (int j = i; j < n; j++) {
                if (niza[j] == 1) {
                    counterOne++;
                } else counterZero++;
                if (counterOne > counterZero) {
                    totalCounter++;
                }
            }
        }
        System.out.println(totalCounter);
    }
}
