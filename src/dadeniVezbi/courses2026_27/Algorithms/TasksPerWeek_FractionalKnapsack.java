package dadeniVezbi.courses2026_27.Algorithms;

import java.util.Scanner;

/*Дадени се N задачи кои треба да се изработат. За секоја од задачите го знаеме времето за изработка (во часови) и заработката која
ја носи. Да се најде максималната заработка во рок од една 40 часовна работна недела. Напомена дека и делумно сработени задачи носат
делумна заработка, во зависност од процентот на завршеност.
Влез: Првиот број од влезот е бројот на задачи N, а потоа во следниот ред времетраењето на задачата во часови и заработката која ја носи.
3
10 60
20 100
30 120

Излез: Максимална заработка која можеме да ја направиме за 40 часа.*/
public class TasksPerWeek_FractionalKnapsack {

    public static void sortArrays(int[] time, double[] revenue){
        for (int i = 0; i < time.length; i++) {
            for (int j = 0; j < time.length - i -1; j++) {
                if(revenue[j]/time[j] < revenue[j+1]/time[j+1]){
                    double temp = revenue[j];
                    revenue[j] = revenue[j+1];
                    revenue[j+1] = temp;

                    int temp1 = time[j];
                    time[j] = time[j+1];
                    time[j+1] = temp1;
                }
            }
        }
    }

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] time = new int[n];
        double[] revenue = new double[n];
        for (int i = 0; i < n; i++) {
            time[i] = input.nextInt();
            revenue[i] = input.nextDouble();
        }
        sortArrays(time, revenue);
        double rev = 0;
        int cap = 40;
        for(int i =0; i < n; i++){
            if(cap == 0){
                break;
            }
            if(cap - time[i] > 0){
                rev += revenue[i];
                cap -= time[i];
            }else{
                rev += revenue[i]/time[i] * cap;
                cap = 0;
            }
        }

        System.out.println(rev);
    }
}
