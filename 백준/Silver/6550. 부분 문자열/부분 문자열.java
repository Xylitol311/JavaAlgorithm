// 메모리: 00000 KB / 시간: 00 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        String line;
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            st = new StringTokenizer(line);
            String s1 = st.nextToken();
            String s2 = st.nextToken();
            boolean result = false;

            int s1Idx = 0;
            for (int idx = 0; idx < s2.length(); idx++) {
                if (s2.charAt(idx) == s1.charAt(s1Idx)) {
                    s1Idx++;
                    if (s1Idx == s1.length()) {
                        result = true;
                        break;
                    }
                }
            }

            if (result)
                sb.append("Yes").append("\n");
            else
                sb.append("No").append("\n");
        }
        System.out.println(sb);
    }
}