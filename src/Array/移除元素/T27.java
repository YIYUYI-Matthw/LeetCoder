package Array.移除元素;

import java.util.Arrays;

public class T27 {
    public static void main(String[] args) {
        int[] arr01 = {3, 2, 2, 3};
        System.out.println(new Solution_T27_self().removeElement(arr01, 3));
        System.out.println(Arrays.toString(arr01));
    }
}

/*
重点是原地修改：遍历一遍，遇到val位移距离+1；非val则位移覆盖
 */
class Solution_T27_self {
    public int removeElement(int[] nums, int val) {
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val)
                cnt++;
            else
                nums[i - cnt] = nums[i];
        }
        return nums.length - cnt;
    }
}

// 思路二：快慢指针，快指针遍历，慢指针记录：每遇到非val，则慢指针+1且赋值：妙啊！
class Solution_T27_doubleP {
    public int removeElement(int[] nums, int val) {
        int slowIndex = 0;
        for (int fastIndex = 0; fastIndex < nums.length; fastIndex++) {
            // 当非val时，慢指针跟进；当val时，慢指针指示val索引等待非val覆盖
            if (nums[fastIndex] != val) {
                //将快指针的值赋给慢指针
                nums[slowIndex] = nums[fastIndex];
                slowIndex++;
            }
        }
        return slowIndex;
    }
    /*
    作者：￡心里 ★只有你
    链接：https://leetcode.cn/problems/remove-element/solutions/1959310/by-psxin-li-zhi-you-ni-wz9t/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}