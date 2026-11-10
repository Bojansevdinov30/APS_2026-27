package labs.Lab4;

import java.util.ArrayList;
import java.util.Scanner;

/*Problem

There are N tasks to run one after another, starting at time 0. Task i takes Ti time units and has weight Wi.
Each task's cost is its finish time multiplied by its weight.

Find an execution order that minimizes the total cost (the sum of all task costs).

If several orders give the same minimum, choose the one where tasks with equal Ti/Wi keep their input order.

Input
Line 1: N, the number of tasks.
Next N lines: Ti and Wi for each task.
Output
Line 1: the minimum total cost.
Line 2: the task numbers (1 to N, by input position) in execution order.
Example

Input:

Plain Text
3
2 3
1 2
4 1

Output:

Plain Text
18
2 1 3
 

Order 2, 1, 3 gives finish times 1, 3, 7, so the cost is:

Plain Text
1×2 + 3×3 + 7×1 = 18*/
public class MyLab2 {
    static class Task {
        private int index;
        private double time;
        private double weight;

        public Task(int index, double time, double weight) {
            this.index = index;
            this.time = time;
            this.weight = weight;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public double getTime() {
            return time;
        }

        public void setTime(double time) {
            this.time = time;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Task[] tasks = new Task[n];
        for (int i = 0; i < n; i++) {
            tasks[i] = new Task(i + 1, sc.nextInt(), sc.nextInt());
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
        double totalCost = 0;
        double finishTime = 0;
        for (int i = 0; i < n; i++) {
            // System.out.print(tasks.get(i).getIndex() + " ");
            finishTime += tasks[i].getTime();
            totalCost += finishTime * tasks[i].getWeight();
        }
        System.out.println(totalCost);
        for (int i = 0; i < n; i++) {
            System.out.print(tasks[i].getIndex() + " ");
        }

    }
}
