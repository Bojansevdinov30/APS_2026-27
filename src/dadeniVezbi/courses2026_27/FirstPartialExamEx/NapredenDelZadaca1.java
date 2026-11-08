package dadeniVezbi.courses2026_27.FirstPartialExamEx;

import java.util.Scanner;
import java.util.Stack;

/*Даден е процесор кој извршува задачи. Процесорот може да извршува само една задача во еден момент.

Задачите може да бидат прекинати од други задачи, при што моменталната задача се паузира и процесорот ја извршува задача што го предизвикала прекинот. Потоа паузираната задачата продолжува со извршување.

Дадени се логови за работата на процесорот во формат

job_name command timestamp

Притоа се можни следните команди:

start - почнува да се извршува задача
interrupt - задачата е прекината од друга, почнува да се извршува другата задача
finish - завршува задача
Ваша задача е да се одреди колку време се извршувала секоја задача. Времето на извршување се пресметува како разлика од времето на завршување и времето на почнување. Задачите и нивното време испечатете ги во обратен редослед од времето на завршување (таа што завршила последна се печати прва). Една задача се извршува само еднаш, нема да почне или да заврши повеќе пати.


Влез:

На влез прво е бројот на логови. Потоа во секој ред е даден по еден лог во формат

job_name command timestamp

Излез:

На излез треба да се испечати името на задачата и времето на извршување. Времето на извршување се добива како разлика од времето на завршување и времето на почнување. Задачите и нивното време испечатете ги во обратен редослед од времето на завршување (таа што завршила последна се печати прва).

Пример:

Влез:


6
job0 start 0
job1 interrupt 2
job1 finish 5
job2 interrupt 6
job2 finish 7
job0 finish 10

Излез:


job0 6
job2 1
job1 3
Објаснување:

job0 се извршува од 0-2, 5-6 и 7-10
job1 се извршува 2-5
job2 се извршува 6-7*/
public class NapredenDelZadaca1 {
    static class Task {
        private String name;
        private int time;

        public Task(String name, int time) {
            this.name = name;
            this.time = time;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        Stack<Task> stack = new Stack<>();
        Stack<Task> finished = new Stack<>();
        int time =0;
        for (int i = 0; i < n; i++) {
            String name = input.next();
            String command = input.next();
            int timestamp = input.nextInt();
            if(command.equals("start")){
                stack.push(new Task(name,0));
                time = timestamp;
            }else if(command.equals("interrupt")){
                stack.peek().time += timestamp - time;
                time = timestamp;
                stack.push(new Task(name,0));
            }else if(command.equals("finish")){
                stack.peek().time += timestamp - time;
                time = timestamp;
                finished.push(stack.pop());
            }
        }
        while(!finished.isEmpty()){
            System.out.println(finished.peek().getName() + " " +  finished.pop().getTime());
        }
    }
}
