package GreedyA;

import java.util.LinkedList;

/*
给定一个整数 n ，返回 小于或等于 n 的最大数字，且数字呈 单调递增 。
示例
输入: n = 332
输出: 299
 */
public class T738 {
    public static void main(String[] args) {
        System.out.println(new Solution_T738_org().monotoneIncreasingDigits(10));
    }
}

class Solution_T738_org {
    /*
    思路上是一个往复的过程
    首先前向遍历判断是否有递减趋势，若有则后续变更为9，并开始后向遍历判断当前位减一会不会打乱“队形”
     */
    public int monotoneIncreasingDigits(int n) {
        if (n < 10)
            return n;
        LinkedList<Integer> num_list = new LinkedList<>();
        while (n != 0) {
            num_list.push(n % 10); // push是栈操作
            n /= 10;
        }
        int i = 1, j = 0;
        for (; i < num_list.size(); i++) {
            if (num_list.get(i) < num_list.get(i - 1)) {
                // 找到序列中第一个下降的数：后面的变成9
                j = i;
                while (j < num_list.size())
                    num_list.set(j++, 9);
                break;
            }
        }
        if (j != 0) {
            // 若后续更改过：判断前面数值的变更
            for (i -= 1; i >= 0; i--) {
                if (i == 0)
                    num_list.set(i, num_list.get(i) - 1);
                else {
                    if (num_list.get(i) - 1 < num_list.get(i - 1))
                        num_list.set(i, 9); // 扰乱队形就同样变成9
                    else {
                        num_list.set(i, num_list.get(i) - 1); // 不扰乱队形就减一
                        break;
                    }
                }
            }
        }
        int res = 0;
        for (i = num_list.size() - 1; i >= 0; i--) {
            res += num_list.get(i) * Math.pow(10, num_list.size() - 1 - i);
        }
        return res;
    }
}
