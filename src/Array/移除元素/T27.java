package Array.移除元素;

import java.util.Arrays;

/*
给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。

原地修改：不许额外增加数组装载其他元素
e.g.
输入：nums = [0,1,2,2,3,0,4,2], val = 2
输出：5, nums = [0,1,4,0,3]（实际上是[01403???]，依据5则可得出剩下元素为01403）
解释：函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。注意这五个元素可为任意顺序。你不需要考虑数组中超出新长度后面的元素。
 */
public class T27 {
    public static void main(String[] args) {
        int[] arr01 = {0, 1, 2, 2, 3, 0, 4, 2};
        System.out.println(new Solution_T27_again().removeElement(arr01, 2));
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

class Solution_T27_again {
    /*
    倒序快慢指针
    如果不是val，则快指针前进一，若是，则快慢指针交换数值，再分别前进1
    正序：非val均在右侧；倒序：非val均在左侧
     */
    public int removeElement(int[] nums, int val) {
        int fast = nums.length - 1, slow = nums.length - 1, cnt = 0;
        while (fast >= 0) {
            int temp;
            if (nums[fast] == val) {
                cnt++;
                temp = nums[fast];
                nums[fast] = nums[slow];
                nums[slow--] = temp;
            }
            fast--;
        }
        return nums.length - cnt;
    }
}