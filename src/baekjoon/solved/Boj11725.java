package baekjoon.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj11725 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // 그래프 구현
        ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tree.add(new ArrayList<>());
        }

        // 그래프 입력
        for(int i = 0; i < n - 1; i++){
            int a = sc.nextInt() - 1; // 인덱스값으로 보기 쉽게 -1을 함. 1번 노드의 경우 제일 첫번째인 인덱스 0으로
            int b = sc.nextInt() - 1;
            tree.get(a).add(b);
            tree.get(b).add(a);
        }

        boolean[] visited = new boolean[n];
        int[] parent = new int[n];

        //BFS를 이용해 탐색
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited[0] = true;

        while (!queue.isEmpty()){
            int p = queue.remove(); // parent node
            for(int node : tree.get(p)){
                if(!visited[node]){
                    visited[node] = true;;
                    queue.add(node);
                    parent[node] = p;
                }
            }
        }

        for (int i = 1; i < n; i++) {
            System.out.println(parent[i] + 1);
        }

    }
}
