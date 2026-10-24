package dadeniVezbi.courses2026_27.OnedimentionalDataStructures;

import java.util.*;

/*Даден е код на модифициран програмски јазик каде функциите се претставени со отворен и затворен таг ("imeFunkcija" и "endimeFunkcija").
Ваша задача е да дефинирате дали даден програмски код е валиден.

Пример валиден код:
func1
func2
endfunc2
func3
endfunc3
endfunc1

Пример невалиден код (испуштен е затворен таг за func3):
func1
func2
endfunc2
func3
endfunc1

Влез: Код со модифициран програмски јазик, каде секој таг е напишан во нов ред. Се внесуваат тагови се додека не се внесе "x".

Излез: "Valid" - доколку е валиден кодот, "Invalid" - доколку не е валиден кодот.*/
public class CodeValidityCheck {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Stack<String> funcStack = new Stack<>();

        boolean isValid = true;

        String s  = input.nextLine();
        while(!s.equals("x")){
            if(s.startsWith("end")){
                if(funcStack.isEmpty() || !funcStack.peek().equals(s.substring(3))){
                    isValid = false;
                }else{
                    funcStack.pop();
                }
            }else{
                funcStack.push(s);
            }
            s = input.nextLine();
        }
        if(!funcStack.isEmpty()){
            isValid = false;
        }
        if(isValid){
            System.out.println("Valid");
        }else {
            System.out.println("Invalid");
        }
    }
}
