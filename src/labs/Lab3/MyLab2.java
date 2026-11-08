package labs.Lab3;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/*Системот користи редица (Queue) за активни процеси и еден заеднички стек (Stack) за временски суспендирани процеси. Во системот пристигнуваат N процеси,
секој со време потребно за извршување. Процесорот во еден круг обработува еден процес најмногу T временски единици (квантум).

Правила:
Се зема процесот од почетокот на редицата и се извршува најмногу T временски единици. Тоа се брои како еден круг.
Ако процесот заврши, неговото име се печати со реден број.
Ако квантумот истече, а процесот не е завршен, тој се поставува на врвот на стекот на суспендирани процеси (не се враќа во редицата).
По секои M кругови (по круг чиј реден број е делив со M), целиот стек се празни во редицата: процесите се земаат од врвот на стекот и еден по еден се додаваат
на крајот од редицата (pop → enqueue). Со тоа се менува редоследот на суспендираните процеси.
Ако редицата е празна, а стекот не е, стекот веднаш се празни во редицата (без да се чека M-тиот круг).
Ваша задача е да го определите редоследот по кој процесите завршуваат.

Влез
Првиот ред содржи три броја: N (број на процеси), T (времетраење на квантумот) и M (на колку круга се празни стекот).
Во следните N редови се внесува името на процесот и времето потребно за негово извршување.
Пример
4 2 3
A 4
B 1
C 6
D 3

Излез
Се печати редоследот по кој процесите завршуваат, секој во посебен ред, со неговиот реден број и име.
Пример
1. B
2. A
3. C
4. D*/
public class MyLab2 {
    static class Process {
        private String name;
        private int time;

        public Process(String name, int time) {
            this.name = name;
            this.time = time;
        }

        public void run(int t) {
            this.time -= t;
            if (this.time < 0) {
                this.time = 0;
            }
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
        Scanner sc = new Scanner(System.in);
        Stack<Process> stack = new Stack<>();
        Queue<Process> queue = new ArrayDeque<>();
        int n = sc.nextInt();
        int t = sc.nextInt();
        int m = sc.nextInt();
        for (int i = 0; i < n; i++) {
            queue.offer(new Process(sc.next(), sc.nextInt()));
        }
        int i = 1;
        int processCounter = 1;
        while (!queue.isEmpty() || !stack.isEmpty()) {
            if (!queue.isEmpty()) {
                Process p = queue.poll();
                p.run(t);
                if (p.getTime() == 0) {
                    System.out.println(processCounter++ + ". " + p.getName());
                } else {
                    stack.push(p);
                }
            }
            if ((i % m == 0 || queue.isEmpty()) && !stack.isEmpty()) {
                while (!stack.isEmpty()) {
                    queue.offer(stack.pop());
                }
            }
            i++;

        }
    }
}
