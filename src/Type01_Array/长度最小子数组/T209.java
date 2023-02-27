package Type01_Array.长度最小子数组;


// TODO：二刷：随想录题解

/*
给定一个含有 n 个正整数的数组和一个正整数 target 。
找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 */
public class T209 {
    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 1, 1, 1};
        System.out.println(new Solution_T209_double().minSubArrayLen(5, nums));
    }
}

/*
理解错题意了，连续数组是说位置连续，而不是值挨个递增或递减
 */
class Solution_T29_self {
    public int minSubArrayLen(int target, int[] nums) {
        int isCon = 0; // +1-升序；-1-降序；0-不连续
        int sum = nums[0], len = nums.length, cl = 1, end = 0;
        for (int i = 1; i < nums.length; i++) {
            int dif = nums[i] - nums[i - 1];
            if (Math.abs(dif) == 1) {
                // 有可能连续
                if (isCon == 0)
                    isCon = dif;
                if (dif == isCon) {
                    sum += nums[i];
                    cl++;
                } else {
                    // 升降序更改
                    sum = nums[i - 1] + nums[i];
                    cl = 2;
                }
            } else {
                // 判断是否满足条件
                if (sum >= target && (cl < len || len == 0)) {
                    len = cl;
                    end = i;
                }
                isCon = 0;
                sum = nums[i];
                cl = 1;
            }
        }
        if (sum >= target && (cl < len || len == 0)) {
            len = cl;
        }
        return len;
    }
}

/*
不要以为for里放一个while就以为是O(n^2)
主要是看【每一个元素被操作的次数】，每个元素在滑动窗后进来操作一次，出去操作一次，每个元素都是被操作两次，所以时间复杂度是 2 × n 也就是O(n)。
个人：
1. 初始记录长度设定为nums.length而不是0
2. 先计算长度再减少sum，然后进入下一次while循环：不然即使这一次循环不满足，长度也已经被剪短了
 */
class Solution_T29_doubP {
    public int minSubArrayLen(int target, int[] nums) {
        int sum = 0, cl = 0, len = nums.length + 1;
        int left = 0, right;
        for (right = 0; right < nums.length; right++) {
            sum += nums[right];
            cl++;
            while (sum > target) {
                len = Math.min(cl, len);
                if (sum - nums[left] >= target) {
                    len = --cl < len ? cl : len;
                    sum -= nums[left++];
                } else {
                    break;
                }
            }
        }
        len = len == nums.length + 1 ? 0 : len;
        return len;
    }
}

class Solution_T209_Carl {
    // 滑动窗口
    public int minSubArrayLen(int s, int[] nums) {
        int left = 0;
        int sum = 0;
        int result = Integer.MAX_VALUE;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum >= s) {
                // 差距在这个while循环中
                result = Math.min(result, right - left + 1);
                sum -= nums[left++];
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }
}

class Solution_T209_again {
    public int minSubArrayLen(int target, int[] nums) {
        int sum = 0, cl = 0, len = nums.length + 1;
        int left = 0, right;
        for (right = 0; right < nums.length; right++) {
            sum += nums[right];
            cl++;
            while (sum >= target) {
                len = Math.min(cl, len);
                if (sum - nums[left] >= target) {
                    len = --cl < len ? cl : len;
                    sum -= nums[left++];
                } else {
                    break;
                }
            }
        }
        len = len == nums.length + 1 ? 0 : len;
        return len;
    }
}

class Solution_T209_double {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0, right = 0, sum = 0, currentLen = 0, minLen = (int) 1e5 + 1;
        for (; right < nums.length; right++) {
            sum += nums[right];
            currentLen += 1;
            if (sum >= target) {
                while (left <= right && sum - nums[left] >= target) {
                    // TODO：优化：currentLen = right - left +1
                    sum -= nums[left++];
                    currentLen--;
                }
                minLen = Math.min(currentLen, minLen);
            }
        }
        return sum >= target ? minLen : 0;
    }
}