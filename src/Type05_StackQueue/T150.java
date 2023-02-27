package Type05_StackQueue;

import java.util.Stack;

/*
给你一个字符串数组 tokens ，表示一个根据 逆波兰表示法 表示的算术表达式。
请你计算该表达式。返回一个表示表达式值的整数。
 */
public class T150 {
    public static void main(String[] args) {
        String[] tokens = {"4", "13", "5", "/", "+"};
        System.out.println(new Solution_T150_again().evalRPN(tokens));
    }
}

class Solution_T150_again {
    /*
    逆波兰表达式其实不涉及操作符之间的优先级问题
     */
    public int evalRPN(String[] tokens) {
        Stack<String> tokenStack = new Stack<>();
        for (String token : tokens) {
            switch (token) {
                case "+":
                    tokenStack.push("" + (Integer.parseInt(tokenStack.pop()) + Integer.parseInt(tokenStack.pop())));
                    break;
                case "-":
                    tokenStack.push("" + (-Integer.parseInt(tokenStack.pop()) + Integer.parseInt(tokenStack.pop())));
                    break;
                case "*":
                    tokenStack.push("" + (Integer.parseInt(tokenStack.pop()) * Integer.parseInt(tokenStack.pop())));
                    break;
                case "/":
                    int divided = Integer.parseInt(tokenStack.pop());
                    tokenStack.push("" + (Integer.parseInt(tokenStack.pop()) / divided));
                    break;
                default:
                    tokenStack.push(token);
                    break;
            }
        }
        return Integer.parseInt(tokenStack.pop());
    }
}

class Solution_T150_again_M2 {
    /*
    逆波兰表达式其实不涉及操作符之间的优先级问题
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> tokenStack = new Stack<>();
        for (String token : tokens) {
            switch (token) {
                case "+":
                    tokenStack.push(tokenStack.pop() + tokenStack.pop());
                    break;
                case "-":
                    tokenStack.push(-tokenStack.pop() + tokenStack.pop());
                    break;
                case "*":
                    tokenStack.push(tokenStack.pop() * tokenStack.pop());
                    break;
                case "/":
                    int divided = tokenStack.pop();
                    tokenStack.push(tokenStack.pop() / divided);
                    break;
                default:
                    tokenStack.push(Integer.parseInt(token));
                    break;
            }
        }
        return tokenStack.pop();
    }
}