package baekjoon.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj1254 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int answer = s.length();
        for (int i = 0; i < s.length(); i++) {
            if (isPalindrome(s.substring(i))) {
                break;
            }
            answer++;
        }
        System.out.println(answer);
    }

    private static boolean isPalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;
        while (start <= end) {
            if (s.charAt(start) != s.charAt((end))) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
