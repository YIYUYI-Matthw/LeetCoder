package String;

public class T344 {

}

class Solution_T344_self {
    // 双指针，换位置
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left <= right) {
            char temp = s[left];
            s[left++] = s[right];
            s[right++] = temp;
        }
    }
}
