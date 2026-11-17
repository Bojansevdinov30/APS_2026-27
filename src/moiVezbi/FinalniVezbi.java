package moiVezbi;

import dataStructures.SLL;
import dataStructures.SLLNode;

import java.util.*;

public class FinalniVezbi {

    static void removeDuplicates() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        SLL<Integer> sll = new SLL<>();
        for (int i = 1; i <= n; i++) {
            sll.insertLast(sc.nextInt());
        }

        SLLNode<Integer> temp = sll.getHead();
        while (temp != null) {
            SLLNode<Integer> temp2 = temp.getSucc();
            while (temp2 != null) {
                if (temp2.getElement().equals(temp.getElement())) {
                    sll.delete(temp2);
                }
                temp2 = temp2.getSucc();
            }
            temp = temp.getSucc();
        }
        System.out.println(sll);
    }

    static void grupiNaizmenicnoTeska() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        SLL<Integer> list = new SLL<>();
        for (int i = 0; i < n; i++) list.insertLast(sc.nextInt());
        int k = sc.nextInt();

        SLLNode<Integer> dummy = new SLLNode<>(0, list.getHead());
        SLLNode<Integer> prevTail = dummy;
        SLLNode<Integer> cur = dummy.getSucc();
        int idx = 0;
        while (cur != null) {
            SLLNode<Integer> groupStart = cur, last = null, t = cur;
            int cnt = 0;
            while (t != null && cnt < k) {
                last = t;
                t = t.getSucc();
                cnt++;
            }
            if (idx % 2 == 0) {
                SLLNode<Integer> prev = t, p = groupStart;
                while (p != t) {
                    SLLNode<Integer> nx = p.getSucc();
                    p.setSucc(prev);
                    prev = p;
                    p = nx;
                }
                prevTail.setSucc(prev);
                prevTail = groupStart;
            } else {
                prevTail = last;
            }
            cur = t;
            idx++;
        }
        list.setHead(dummy.getSucc());

        SLLNode<Integer> node = list.getHead();
        while (node != null && node.getSucc() != null) {
            if (node.getElement().equals(node.getSucc().getElement())) node.setSucc(node.getSucc().getSucc());
            else node = node.getSucc();
        }
        System.out.println(list);
    }

    static void grupiNaizmenicnoLesna() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        SLL<Integer> list = new SLL<>();
        for (int i = 0; i < n; i++) list.insertLast(sc.nextInt());
        int k = sc.nextInt();

        SLLNode<Integer> curr = list.getHead();
        SLL<Integer> result = new SLL<>();
        int counter = 0;
        int index = 0;
        while (curr != null) {
            SLL<Integer> temp = new SLL<>();
            counter = 0;
            while (curr != null && counter < k) {
                if (index % 2 == 0) {
                    temp.insertFirst(curr.element);
                } else {
                    temp.insertLast(curr.element);
                }
                curr = curr.getSucc();
                counter++;
            }
            SLLNode<Integer> temp2 = temp.getHead();
            for (int i = 0; i < k && temp2 != null; i++) {

                result.insertLast(temp2.element);
                temp2 = temp2.getSucc();
            }
            index++;
        }
        SLLNode<Integer> node = result.getHead();
        while (node != null) {
            SLLNode<Integer> node2 = node.getSucc();
            while (node2 != null && node.getElement().equals(node2.getElement())) {
                node2 = node2.getSucc();
                node.setSucc(node2);
            }
            node = node.getSucc();
        }
        System.out.println(result);
    }

    static void minRewards(int[] scores) {
        int[] dp = new int[scores.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] > scores[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            }
        }

        for (int i = scores.length - 2; i >= 0; i--) {
            if (scores[i] > scores[i + 1]) {
                dp[i] = Math.max(dp[i], dp[i + 1] + 1);
            }
        }

        int total = 0;
        for (int i = 0; i < dp.length; i++) {
            total += dp[i];
        }
        System.out.println(total);
    }

    static void xmlStack() {
        /*
TRUE:
        18
        /begin{section}
/begin{text}
more text
/end{text}
/begin{subsection}
/begin{text}
text text
text text
/end{text}
/begin{subsubsection}
/begin{text}
text text
/end{text}
/end{subsubsection}
/begin{text}
text text
/end{text}
/end{subsection}
/end{section}

FALSE:
        4
        /begin{text}
text text
/end{text}
/end{section}
----------------
        7
        /begin{section}
/begin{subsubsection}
/begin{text}
text text
/end{text}
/end{subsubsection}
/end{section}*/
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();
        boolean flag = true;
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            String input = sc.nextLine();
            if (input.startsWith("/begin")) {
                String type = input.substring(7, input.length() - 1);
                if (type.equals("subsubsection")) {
                    if (stack.isEmpty() || !stack.peek().equals("subsection")) {
                        flag = false;
                        break;
                    } else {
                        stack.push(type);
                    }
                } else if (type.equals("text")) {
                    if (stack.isEmpty()) {
                        flag = false;
                        break;
                    } else {
                        stack.push(type);
                    }
                } else {
                    stack.push(type);
                }
            } else if (input.startsWith("/end")) {
                String type = input.substring(5, input.length() - 1);
                if (stack.isEmpty() || !stack.peek().equals(type)) {
                    flag = false;
                    break;
                } else {
                    stack.pop();
                }
            }
        }

        if (!stack.isEmpty()) {
            flag = false;
        }
        if (flag) {
            System.out.println("Correct");
        } else {
            System.out.println("Wrong");
        }

    }

    static void coins(int p, int v) {
        int target = p - v;
        if (target < 0) {
            return;
        }
        int[] coins = {1, 2, 5};
        int n = coins.length;

        int[] dp = new int[target + 1];

        Arrays.fill(dp, 1000);
        for (int i = 0; i < n; i++) {
            dp[coins[i]] = 1;
        }

        for (int i = 0; i <= target; i++) {
            for (int j = 0; j < n; j++) {
                if (i + coins[j] <= target) {
                    dp[i + coins[j]] = Math.min(dp[i + coins[j]], dp[i] + 1);
                }
            }

        }
        System.out.println(dp[target]);


    }

    static void printer() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Queue<String> waiting = new ArrayDeque<>();
        Queue<String> printed = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            String status = sc.next();
            if (status.equals("ADD")) {
                String item = sc.next();
                int times = sc.nextInt();
                for (int j = 0; j < times; j++) {
                    waiting.offer(item);
                }
            } else if (status.equals("PRINT")) {
                int times = sc.nextInt();
                for (int j = 0; j < times; j++) {
                    printed.offer(waiting.poll());
                }
            } else if (status.equals("STATUS")) {
                System.out.println("Current status:");
                for (String s : printed) {
                    System.out.println(s + " printed");
                }
                for (String s : waiting) {
                    System.out.println(s + " waiting");
                }
            } else {
                continue;
            }

        }

    }

    static void zigzag() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        SLL<Integer> list = new SLL<>();
        for (int i = 0; i < n; i++) {
            list.insertLast(sc.nextInt());
        }

        SLLNode<Integer> curr = list.getHead();
        SLLNode<Integer> next = curr.getSucc();
        while (curr != null && next != null) {
            if (curr.getElement() > 0 && next.getElement() > 0) {
                list.delete(next);
                curr = next.getSucc();
                next = curr.getSucc();
            } else if (curr.getElement() < 0 && next.getElement() < 0) {
                list.insertAfter(Math.abs(curr.getElement()), curr);
                curr = next;
                next = curr.getSucc();
            } else if (curr.getElement() == 0) {
                list.delete(curr);
                curr = next;
                next = curr.getSucc();
            } else {
                curr = next;
                next = next.getSucc();
            }
        }
        System.out.println(list.toString());
    }

    static String longestPalindromic(String s) {
        int n = s.length();

        boolean[][] dp = new boolean[n][n];

        int start = 0;
        int maxLength = 1;

        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j)) {

                    if (len == 2 || dp[i + 1][j - 1]) {
                        dp[i][j] = true;

                        if (len > maxLength) {
                            maxLength = len;
                            start = i;
                        }
                    }
                }
            }
        }
        return s.substring(start, start + maxLength);
    }

    public static void main(String[] args) {
        // grupiNaizmenicnoLesna();
        // minRewards(new int[] {1,2,3,4,5,6,7,8,9,10});
        // xmlStack();
        // coins(20, 14);
        // printer();
        // zigzag();
        System.out.println(longestPalindromic("kayak"));
    }

}
