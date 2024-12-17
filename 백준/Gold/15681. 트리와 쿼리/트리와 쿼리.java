// 메모리: 00000 KB / 시간: 00 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static ArrayList<Integer>[] list, tree;
    static int parent[], size[];
    static int n, r, q;

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        parent = new int[n + 1];
        size = new int[n + 1];
        list = new ArrayList[n + 1];
        tree = new ArrayList[n + 1];
        for (int i = 0; i < list.length; i++) {
            list[i] = new ArrayList<>();
            tree[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            list[u].add(v);
            list[v].add(u);
        }
        makeTree(r, -1);
    }
    
    public static void main(String[] args) throws IOException {
        input();
        countSubtreeNodes(r);
        StringBuffer sb = new StringBuffer();
        while (q-- > 0) {
            int query = Integer.parseInt(br.readLine());
            sb.append(size[query]).append("\n");
        }
        System.out.println(sb);
    }

    // list를 바탕으로 tree(Subtree) 구성한다.
    // p가 -1은 부모가 없음을 의미한다. 즉 루트 노드이다.
    public static void makeTree(int curNode, int p) {
        for (int node : list[curNode]) {
            if (node != p) {
                tree[curNode].add(node);
                parent[node] = curNode;
                makeTree(node, curNode);
            }
        }
    }

    // 자신도 자신을 루트로 하는 서브트리에 포함되므로 0이 아닌 1에서 시작한다.
    public static void countSubtreeNodes(int curNode) {
        size[curNode] = 1;
        for (int node : tree[curNode]) {
            countSubtreeNodes(node);
            size[curNode] += size[node];
        }
    }
}