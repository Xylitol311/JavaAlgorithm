import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        
        Stack<Integer>[] stacks = new Stack[7]; // 1-6번 줄
        for (int i = 1; i <= 6; i++) {
            stacks[i] = new Stack<>();
        }
        
        int cnt = 0;
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int line = Integer.parseInt(st.nextToken());
            int fret = Integer.parseInt(st.nextToken());
            
            // 현재 프렛보다 큰 값들을 모두 pop
            while (!stacks[line].isEmpty() && stacks[line].peek() > fret) {
                stacks[line].pop();
                cnt++;
            }
            
            // 같은 프렛이 아니라면 push
            if (stacks[line].isEmpty() || stacks[line].peek() != fret) {
                stacks[line].push(fret);
                cnt++;
            }
        }
        
        System.out.println(cnt);
    }
}