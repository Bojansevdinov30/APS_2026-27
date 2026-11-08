package dadeniVezbi.courses2026_27.FirstPartialExamEx;

import java.util.Scanner;

/*Во магацин пристигнуваат N кутии на подвижната лента. За секоја кутија се дадени нејзината тежина и вредност. Дел од кутиите треба да се стават на куп така што ќе се максимизира нивната вредност. Притоа за купот да биде стабилен, тежината на секоја кутија мора да биде еднаква или помала од тежината на кутијата под неа. Кутиите се земаат во редоследот по кој пристигнуваат.

Влез:

На влез прво е даден бројот на кутии, а со секој ред е дадена тежината и вредноста на секоја кутија. Форматот е:

weight value

Излез:

На излез треба да се испечати максималната вреднот на стабилен куп на кутии.

Пример:

Влез:


5
10 5
5 10
12 1
6 7
6 8


Излез:


20
Објаснување:

Стабилни купови: со тежини [10, 5] (вредност 10+5=15), [12, 6, 6] (вредност 1 + 7 + 8 = 16), [10, 6, 6] (вредност 5+7+8=20), [12, 6] (вредност 1+7=8),
[12, 6] (вредност 1+8=9), [6, 6] (вредност 7+8=15), [10] (вредност 5), [5] (вредност 10), [12] (вредност 1), [6] (вредност 7), [6] (вредност 8).
Максималната вредност е 20.*/
public class NapredenDelZadaca2 {
    static class Box {
        private int weight;
        private int value;

        public Box(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        Box[] boxes = new Box[n];
        for (int i = 0; i < n; i++) {
            boxes[i] = new Box(input.nextInt(), input.nextInt());
        }
        int max = 0;
        int dp[] = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = boxes[i].value;
            max = Math.max(dp[i], max);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
               if(boxes[i].weight <= boxes[j].weight){
                   dp[i] = Math.max(dp[i], dp[j] + boxes[i].value);
                   max = Math.max(max, dp[i]);
               }
            }
        }
        System.out.println(max);
    }
}
