import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    
    static int N, M;
    static int[] basket;
    
    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        basket = new int[N + 1];
        
        for (int i = 1; i <= N; i++){
            basket[i] = i;
        }
    }
    public static void main(String[] args) throws IOException{
        input();
        solution();
        System.out.println(sb.toString());
    }
    private static void solution() throws IOException{
        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int temp = basket[a];
            basket[a] = basket[b];
            basket[b] = temp;
        }
        
        for (int i = 1; i <= N; i++){
            sb.append(basket[i]).append(" ");
        }
    }
}