import java.io.*;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[] sequence;
    private static StringBuilder sb = new StringBuilder();
    
    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sequence = new int[M];
    }
    
    public static void main(String[] args) throws IOException {
        input();
        dfs(0);
        System.out.print(sb.toString());
    }
    
    private static void dfs(int depth) {
        // 기저 조건: M개의 수를 모두 선택했을 때
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(sequence[i]);
                if (i < M - 1) sb.append(" ");
            }
            sb.append("\n");
            return;
        }
        
        // 1부터 N까지 모든 수에 대해 중복을 허용하여 선택
        for (int i = 1; i <= N; i++) {
            sequence[depth] = i;
            dfs(depth + 1);
        }
    }
}