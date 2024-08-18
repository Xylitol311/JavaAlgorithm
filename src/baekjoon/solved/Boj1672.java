package baekjoon.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj1672 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[][] ch = new char[][]{
                {'A', 'C', 'A', 'G'},
                {'C', 'G', 'T', 'A'},
                {'A', 'T', 'C', 'G'},
                {'G', 'A', 'G', 'T'}
        };
        // A,G,C,T = 0,1,2,3
        int n = Integer.parseInt(br.readLine());
        String str = br.readLine();
        char[] strToChar = new char[str.length()];

        for (int i = 0; i < str.length(); i++) {
            strToChar[i] = str.charAt(i);
        }

        for (int i = strToChar.length - 1; i > 0; i--) {
            int first = index(strToChar[i - 1]);
            int second = index(strToChar[i]);
            char ans = ch[first][second];
            strToChar[i - 1] = ans;
        }

        System.out.println(strToChar[0]);
    }

    public static int index(char c) {
        switch (c) {
            case 'A':
                return 0;
            case 'G':
                return 1;
            case 'C':
                return 2;
            default:
                return 3;
        }
    }
}
