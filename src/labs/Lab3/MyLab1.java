package labs.Lab3;

import dataStructures.LinkedQueue;

import java.util.Scanner;

/*Дадени се N процеси, каде секој процес е претставен со име и време потребно за негово извршување.
Процесите се обработуваат според принципот на редица, по редоследот на нивното внесување. Процесорот во секој круг обработува еден процес најмногу
 T временски единици. Доколку процесот не е завршен, преостанатото време се намалува за T, а процесот се додава на крајот од редицата.
  Доколку процесот е завршен, неговото име се печати.

Ваша задача е да го определите редоследот по кој завршуваат процесите.

Влез: Прво се внесува бројотна процеси N и временски единици Т кои се обработуваат во една итерација.

3 2
Process-A 3
Process-B 1
Process-C 4
Излез: Редниот број на целосно завршен процес и името на процесот

1. Process-B
2. Process-A
3. Process-C*/
public class MyLab1 {
    static class Process{
        String name;
        int time;
        Process(String name, int time){
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

        public void execute(int t){
            this.time = this.time - t;
            if(this.time < 0){
                this.time  = 0;
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int t = sc.nextInt();
        LinkedQueue<Process> queue = new LinkedQueue<>();
        for(int i = 0; i < n; i++){
            Process p = new Process(sc.next(), sc.nextInt());
            queue.enqueue(p);
        }

        int i = 1;
        while(queue.size() > 0){
            Process p = queue.dequeue();
            p.execute(t);
            if(p.getTime() == 0){
                System.out.println(i + ". " + p.getName());
                i++;
            }else{
                queue.enqueue(p);
            }
        }

    }
}
