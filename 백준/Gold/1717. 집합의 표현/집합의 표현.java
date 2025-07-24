import java.io.*;
import java.util.*;

public class Main {
   static int[] parent;
   
   public static void input() throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());
       
       int n = Integer.parseInt(st.nextToken());
       int m = Integer.parseInt(st.nextToken());
       
       parent = new int[n + 1];
       for (int i = 0; i <= n; i++) {
           parent[i] = i;
       }
       
       solution(br, m);
   }
   
   // Union-Find 자료구조를 이용해 집합 연산을 처리
   public static void solution(BufferedReader br, int m) throws IOException {
       StringBuilder sb = new StringBuilder();
       
       for (int i = 0; i < m; i++) {
           StringTokenizer st = new StringTokenizer(br.readLine());
           int operation = Integer.parseInt(st.nextToken());
           int a = Integer.parseInt(st.nextToken());
           int b = Integer.parseInt(st.nextToken());
           
           if (operation == 0) {
               union(a, b);
           } else {
               if (find(a) == find(b)) {
                   sb.append("YES\n");
               } else {
                   sb.append("NO\n");
               }
           }
       }
       
       System.out.print(sb.toString());
   }
   
   // 경로 압축을 적용한 find 연산
   public static int find(int x) {
       if (parent[x] != x) {
           parent[x] = find(parent[x]);
       }
       return parent[x];
   }
   
   // 두 집합을 합치는 union 연산
   public static void union(int a, int b) {
       int rootA = find(a);
       int rootB = find(b);
       
       if (rootA != rootB) {
           parent[rootB] = rootA;
       }
   }
   
   public static void main(String[] args) throws IOException {
       input();
   }
}