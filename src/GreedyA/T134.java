package GreedyA;

/*
在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
给定两个整数数组 gas 和 cost ，如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。
 */
public class T134 {
    public static void main(String[] args) {
        int[] gas = {5, 8, 2, 8};
        int[] cost = {6, 5, 6, 6};
        System.out.println(new Solution_T134_self().canCompleteCircuit(gas, cost));
    }
}

class Solution_T134_self {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        /*
        维护gas_sum和cost_sum，
            只要当前gas_sum>cost_sum就继续走：i++，
            否则二者清零并且从下一位开始，idx+1 & i++
            结束条件：① 挑出for循环 -> return -1; ② i == idx+cost.length-绕一圈 & gas_sum > cost_sum -> return idx
         */
        int gas_val, cost_val;
        for (int i = 0; i < cost.length; i++) {
            if (gas[i] < cost[i])
                continue;
            int startIdx = i;
            gas_val = gas[i];
            cost_val = cost[i];
            while (gas_val >= cost_val) {
                if (i == startIdx + cost.length)
                    return startIdx;
                i++;
                gas_val += gas[i % cost.length];
                cost_val += cost[i % cost.length];
            }
        }
        return -1;
    }
}


class Solution_T134_fast {
    /*
    这个思路很好：只要gas.sum>cost.sum就一定可以过；
    难点在于确定idx：
        设置sum，只要sum<0则说明+<-，后移一位，直到走完全程，
            若sum>0 -> return idx; 否则 return -1;
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int index = 0;
        int sum = 0;
        int less = 0;
        for (int i = 0; i < gas.length; i++) {
            sum += (gas[i] - cost[i]);
            if (sum < 0) {
                less += sum;
                sum = 0;
                index = i + 1;
            }
        }
        if (index == gas.length || sum + less < 0) return -1;
        return index;
    }
}