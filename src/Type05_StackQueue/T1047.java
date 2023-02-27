package Type05_StackQueue;

import java.util.Stack;

/*
给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
在 S 上反复执行重复项删除操作，直到无法继续删除。
在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。

输入："abbaca"
输出："ca"
 */
public class T1047 {
    public static void main(String[] args) {
        System.out.println(new Solution_T1047_again().removeDuplicates("abbaca"));
    }
}

class Solution_T1047_again {
    public String removeDuplicates(String s) {
        Stack<Character> sStack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (sStack.isEmpty())
                sStack.push(ch);
            else {
                if (ch == sStack.peek())
                    sStack.pop();
                else
                    sStack.push(ch);
            }
        }
        StringBuilder simStr = new StringBuilder();
        while (!sStack.isEmpty()) {
            simStr = new StringBuilder(sStack.pop().toString()).append(simStr);
        }
        return simStr.toString();
    }
}
