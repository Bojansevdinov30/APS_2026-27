package dadeniVezbi.courses2026_27.FirstPartialExamExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*Write an algorithm that will calculate (evaluate) a mathematical expression that consists of numbers and operations for adding (*) and multiplying (*).

Note: The operation of multiplying has precedence before the operation of adding.

For example:

Input	Result
2+2*2*2*2*2*2+2*2
70

*/
import java.util.Stack;

public class Stack_ExpressionEvaluator {

    public static int evaluateExpression(String expression) {
        Stack<Integer> stack = new Stack<>();

        int number = 0;
        char operator = '+';

        for (int i = 0; i <= expression.length(); i++) {
            char c = (i == expression.length())
                    ? '+'
                    : expression.charAt(i);

            if (c == ' ') {
                continue;
            }

            if (Character.isDigit(c)) {
                number = number * 10 + (c - '0');
            } else {
                if (operator == '+') {
                    stack.push(number);
                } else if (operator == '*') {
                    int previous = stack.pop();
                    stack.push(previous * number);
                }

                operator = c;
                number = 0;
            }
        }

        int result = 0;

        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input =
                new BufferedReader(new InputStreamReader(System.in));

        System.out.println(evaluateExpression(input.readLine()));
    }
}

