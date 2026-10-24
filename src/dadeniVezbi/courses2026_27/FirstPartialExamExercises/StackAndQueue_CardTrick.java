package dadeniVezbi.courses2026_27.FirstPartialExamExercises;
/*Петар прави трик со карти. Тој има шпил од 51-на карта (некој некогаш не му вратил една) од којшто ви дозволува да влечете карта. Тој, за трикот да биде веродостоен, не ја знае картата, но знае на која позиција се наоѓа. Мааната на Петар е тоа што тој не знае регуларно да измеша карти, туку ги зема првите седум карти, им им го превртува редоследот (пр. од 1 2 3 4 5 6 7 ги реди во 7 6 5 4 3 2 1), потоа зема една карта од превртените и една од врвот од шпилот и го става на крајот од шпилот, така се додека не ги потроши сите седум карти. Со тоа остварува едно мешање на шпил.

Ваша задача е, да изработите симулцаија на ваквото мешање, такашто за дадена N-та карта т.ш. 1<=N<=51, вие ќе му изброите колку вакви мешања треба тој да направи за на врв на шпилот да дојде извлечената карта.*/
import dataStructures.ArrayQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class StackAndQueue_CardTrick {
    public static int count(int N){
        ArrayQueue<Integer> q = new ArrayQueue<>(51);
        for (int i = 1; i <= 51; i++) {
            q.enqueue(i);
        }
        int shuffles = 0;
        while(!q.peek().equals(N)){
            Stack<Integer> stack = new Stack<>();
            for(int i = 1; i <= 7; i++){
                stack.push(q.dequeue());
            }
            while(!stack.isEmpty()){
                q.enqueue(stack.pop());
                q.enqueue(q.dequeue());
            }
            shuffles++;
        }
        return shuffles;

    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in) );
        System.out.println(count(Integer.parseInt(br.readLine())));
    }

}
