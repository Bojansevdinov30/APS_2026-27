package labs.Lab4;
/*Дадени се N задачи, а на располагање има вкупно време L. За секоја задача i се познати нејзиното време на извршување (Ti) и нејзината тежина на приоритет (Wi). Времињата Ti на сите задачи се различни.

Задачите се извршуваат една по една (секвенцијално), првата започнува во време t = 0, а секоја следна веднаш по завршувањето на претходната. Сите избрани задачи мора да завршат најдоцна во време L, па не мора да се извршат сите задачи.

Прво треба да се избере што поголем број задачи. Ако има повеќе такви избори, се зема оној со најмал вкупен збир на времиња (поради различните Ti тој избор е единствен).

Цената на извршување за поединечна задача се пресметува како: Цена = (време на почеток на задачата) × (тежина на приоритет)

Избраните задачи се извршуваат во оптимален редослед, така што вкупната цена е минимална. Напишете програма која ги определува бројот на избрани задачи и најмалата можна вкупна цена за нив.

Влез

Во првата линија се дадени два цела броја: бројот на задачи N и вкупното време L. Во следните N редови се дадени по два цели броја одделени со празно место: времето на извршување Ti и тежината на приоритетот Wi за соодветната задача.

Пример:

4 10
6 2
3 5
2 1
4 3
Излез

Во првиот ред се печати бројот на избрани задачи. Во вториот ред се печати најмалата можна вкупна цена за избраните задачи.

Пример:

3
16
Објаснување

Најкраткото време имаат задачите со Ti = 2, 3, 4 (вкупно 9 ≤ 10). Додавање на задачата со Ti = 6 би дало 15 > 10, па се избираат 3 задачи.
Оптимален редослед: (T=3, W=5), (T=4, W=3), (T=2, W=1).
- (T=3, W=5) почнува во време 0. Цена = 0 × 5 = 0.
- (T=4, W=3) почнува во време 3. Цена = 3 × 3 = 9.
- (T=2, W=1) почнува во време 7. Цена = 7 × 1 = 7.
Вкупна цена = 0 + 9 + 7 = 16.*/


import java.util.*;



public class MyLab4 {

    static class Task {
        int t;
        int w;

        Task(int t, int w) {
            this.t = t;
            this.w = w;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int L = sc.nextInt();

        Task[] tasks = new Task[n];

        for (int i = 0; i < n; i++) {
            tasks[i] = new Task(sc.nextInt(), sc.nextInt());
        }

        // STEP 1: Bubble sort by ascending T
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (tasks[j].t > tasks[j + 1].t) {
                    Task temp = tasks[j];
                    tasks[j] = tasks[j + 1];
                    tasks[j + 1] = temp;
                }
            }
        }

        // STEP 2: Select as many tasks as possible
        Task[] selected = new Task[n];
        int k = 0;
        long totalTime = 0;

        for (int i = 0; i < n; i++) {
            if (totalTime + tasks[i].t <= L) {
                selected[k++] = tasks[i];
                totalTime += tasks[i].t;
            } else {
                break;
            }
        }

        // STEP 3: Bubble sort selected tasks by ascending T/W
        for (int i = 0; i < k - 1; i++) {
            for (int j = 0; j < k - i - 1; j++) {

                if ((long) selected[j].t * selected[j + 1].w >
                        (long) selected[j + 1].t * selected[j].w) {

                    Task temp = selected[j];
                    selected[j] = selected[j + 1];
                    selected[j + 1] = temp;
                }
            }
        }

        // STEP 4: Calculate minimum cost
        long currentTime = 0;
        long totalCost = 0;

        for (int i = 0; i < k; i++) {
            totalCost += currentTime * selected[i].w;
            currentTime += selected[i].t;
        }

        System.out.println(k);
        System.out.println(totalCost);
    }
}


