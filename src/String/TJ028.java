package String;

public class TJ028 {
    public static void main(String[] args) {
        System.out.println(new Solution_self_TJ028().reverseLeftWords("abcdefg", 2));
    }
}

class Solution_self_TJ028 {
    public String reverseLeftWords(String s, int n) {
        return s.substring(n) + s.substring(0, n);
    }
}

/*
法二：
reverseString(sb,0,n-1);
reverseString(sb,n,len-1);
sb.reverse();
 */