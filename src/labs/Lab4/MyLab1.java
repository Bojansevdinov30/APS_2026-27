package labs.Lab4;

import java.util.Arrays;
import java.util.Scanner;

/*Дадени се N задачи кои треба да се извршат една по една (секвенцијално). За секоја задача i се познати нејзиното време на извршување (Ti) и нејзината тежина на приоритет (Wi).

Првата задача во редоследот започнува со извршување во време t = 0. Секоја следна задача започнува веднаш по завршувањето на претходната.

Цената на извршување за поединечна задача се пресметува како: Цена=(време на почеток на задачата)×(тежина на приоритет)

Напишете програма која ќе го одреди оптималниот редослед на извршување на задачите така што вкупната цена (сумата од цените на сите задачи) ќе биде минимална.

Влез: Во првата линија е даден бројот на задачи N. Во следните N редови се дадени по два цели броја одделени со празно место: времето на извршување Ti и тежината на приоритетот Wi за соодветната задача.

Излез: Цел број кој ја претставува најмалата можна вкупна цена за извршување на сите задачи.

Пример:

Влез:

3
2 3
1 2
4 1

Излез:
6


Објаснување:

Оптимален редослед:
- задача 1 (време 1, тежина 2) почнува во време 0. Цена=0×2=0.
- задача 0 (време 2, тежина 3) почнува во време 1. Цена=1×3=3.
- задача 2 (време 4, тежина 1) почнува во време 1 + 2 = 3. Цена=3×1=3.
Вкупна цена = 0 + 3 + 3 = 6*/
public class MyLab1 {
    static class Task{
        private int time;
        private int weight;

        public Task(int time, int weight) {
            this.time = time;
            this.weight = weight;
        }

        public int getTime() {
            return time;
        }

        public int getWeight() {
            return weight;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n  = sc.nextInt();
        Task[] tasks = new Task[n];
        for (int i = 0; i < n; i++) {
            tasks[i] = new Task(sc.nextInt(), sc.nextInt());
        }

        // ab = Ta * Wb
        // ba = Tb * Wa
        for (int i =0; i < n; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if(tasks[j].getTime() * tasks[j+1].getWeight()>tasks[j+1].getTime() *  tasks[j].getWeight()){
                    Task temp =  tasks[j];
                    tasks[j] = tasks[j+1];
                    tasks[j+1] = temp;
                }
            }
        }
        long cost = 0;
        long time = 0;
        for (int i = 0; i < n; i++) {
            cost += time * tasks[i].getWeight();
            time += tasks[i].getTime();
        }
        System.out.println(cost);
    }

}
