package labs.Lab4;
/*Дадени се N задачи кои треба да се извршат една по една (секвенцијално). За секоја задача i се познати нејзиното време на извршување (Ti) и нејзиниот краен рок (Di).

Првата задача во редоследот започнува со извршување во време t = 0. Секоја следна задача започнува веднаш по завршувањето на претходната.

Задоцнувањето на една задача се пресметува како: Задоцнување = max(0, време на завршување на задачата − Di)

Напишете програма која ќе го одреди редоследот на извршување на задачите така што најголемото задоцнување меѓу сите задачи ќе биде минимално.

Влез

Во првата линија е даден бројот на задачи N. Во следните N редови се дадени по два цели броја одделени со празно место: времето на извршување Ti и крајниот рок Di за соодветната задача.

Пример:

3
3 5
2 4
4 6
Излез

Цел број кој ја претставува најмалата можна вредност на најголемото задоцнување.

Пример:

3
Објаснување

Оптимален редослед (по растечки краен рок): задача (T=2, D=4), задача (T=3, D=5), задача (T=4, D=6).
- задача (T=2, D=4) завршува во време 2. Задоцнување = max(0, 2 − 4) = 0.
- задача (T=3, D=5) завршува во време 5. Задоцнување = max(0, 5 − 5) = 0.
- задача (T=4, D=6) завршува во време 9. Задоцнување = max(0, 9 − 6) = 3.
Најголемо задоцнување = 3.

You are given N tasks that must be executed sequentially (one after another). For each task i, its execution time (Ti) and its deadline (Di) are given.

The first task in the sequence begins execution at time t = 0. Every subsequent task begins immediately after the preceding task completes.

The lateness of a task is calculated as: Lateness = max(0, finish time of the task − Di)

Write a program to find a sequence for executing all tasks such that the largest lateness among all tasks is minimized.

Input

The first line contains an integer N, the number of tasks. Each of the next N lines contains two space-separated integers: the execution time Ti and the deadline Di of a task.

Example:

3
3 5
2 4
4 6
Output

A single integer representing the smallest possible value of the largest lateness.

Example:

3
Explanation

Optimal sequence (by increasing deadline): task (T=2, D=4), task (T=3, D=5), task (T=4, D=6).
- Task (T=2, D=4) finishes at t = 2. Lateness = max(0, 2 − 4) = 0.
- Task (T=3, D=5) finishes at t = 5. Lateness = max(0, 5 − 5) = 0.
- Task (T=4, D=6) finishes at t = 9. Lateness = max(0, 9 − 6) = 3.
Largest lateness = 3.

For example:

Input	Result
3
3 5
2 4
4 6
3
*/

import java.util.Scanner;


public class MyLab3 {
    static class Task {
        private long executionTime;
        private long end;

        public Task(long executionTime, long end) {
            this.executionTime = executionTime;
            this.end = end;
        }

        public long getExecutionTime() {
            return executionTime;
        }

        public void setExecutionTime(int executionTime) {
            this.executionTime = executionTime;
        }

        public long getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Task[] tasks = new Task[n];
        for (int i = 0; i < n; i++) {
            tasks[i] = new Task(sc.nextLong(), sc.nextLong());
        }
        // delay = max(0, време на завршување на задачата − Di)

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                if ((tasks[j].getExecutionTime() + tasks[j + 1].getExecutionTime() - tasks[j].getEnd()) < (tasks[j + 1].getExecutionTime() + tasks[j].getExecutionTime() - tasks[j + 1].getEnd())) {
                    Task temp = tasks[j];
                    tasks[j] = tasks[j + 1];
                    tasks[j + 1] = temp;
                }
            }
        }
        long maxDelay = 0;
        long time = 0;
        long zero = 0;
        for (int i = 0; i < n; i++) {
            long delay = Math.max(zero, tasks[i].getExecutionTime() + time - tasks[i].getEnd());
            maxDelay = Math.max(maxDelay, delay);
            time += tasks[i].getExecutionTime();
        }
        System.out.println(maxDelay);
//        for (int i=0; i<n; i++){
//            System.out.println(tasks[i].getExecutionTime());
//        }

    }
}
