package dadeniVezbi.courses2026_27.IntroductionToJava;
/*За даден збор кој се внесува од стандарден влез, да се испечати истиот превртен. На влез во првиот ред се дава број на зборови кои ќе се внесуваат. Во наредните линии се внесуваат самите зборови.

/

For a given word entered from standard input, print it reversed. On input in the first line, the number of words that will be entered is given. In the following lines, the words are entered.

For example:

Input
3
one
two
three
Result
eno
owt
eerht*/
import java.util.Scanner;

public class ReverseWord {

    public static void printReversed(String word) {
        for (int i = word.length()-1; i >=0 ; i--) {
            System.out.print(word.charAt(i));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        for (int i = 0; i < n; i++) printReversed(input.next());
    }
}
