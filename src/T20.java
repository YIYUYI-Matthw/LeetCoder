import java.util.Stack;

public class T20 {
    public static void main(String[] args) {
        System.out.println(new Solution_self_good_T20().isValid(")"));
        // 40 41 91 93 123 125
    }
}

class Solution_self_T20 {
    public boolean isValid(String s) {
        Stack<Character> symbols = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (symbols.empty())
                symbols.push(s.charAt(i));
            else {
                switch (symbols.peek()) {
                    case '(':
                        if (s.charAt(i) == ')')
                            symbols.pop();
                        else
                            symbols.push(s.charAt(i));
                        break;
                    case '[':
                        if (s.charAt(i) == ']')
                            symbols.pop();
                        else
                            symbols.push(s.charAt(i));
                        break;
                    case '{':
                        if (s.charAt(i) == '}')
                            symbols.pop();
                        else
                            symbols.push(s.charAt(i));
                        break;
                }
            }
        }
        return symbols.empty();
    }
}

class Solution_self_no_T20 {
    /*
    左括号一律进栈，右括号判断栈顶是否相等
     */
    public boolean isValid(String s) {
        Stack<Character> symbols = new Stack<>();
        char[] lefts = new char[]{'(', '[', '}'};
        for (char ch : s.toCharArray()) {
            if (symbols.empty())
                if (ch == '(' | ch == '[' | ch == '{')
                    symbols.push(ch);
                else
                    return false;
            else {
                if (ch == symbols.peek() + 1 || ch == symbols.peek() + 2)
                    symbols.pop();
                else if (ch == '(' | ch == '[' | ch == '{')
                    symbols.push(ch);
                else
                    return false;
            }
        }
        return symbols.empty();
    }
}

class Solution_self_good_T20 {
    public boolean isValid(String s) {
        Stack<Character> symbols = new Stack<>();
        for (char ch : s.toCharArray()) {
            switch (ch) {
                case '(':
                    symbols.push(')');
                    continue;
                case '[':
                    symbols.push(']');
                    continue;
                case '{':
                    symbols.push('}');
                    continue;
                default:
                    if (symbols.isEmpty())
                        return false;
                    break;
            }
            if (symbols.pop() != ch)
                return false;
        }
        return symbols.isEmpty();
    }
}