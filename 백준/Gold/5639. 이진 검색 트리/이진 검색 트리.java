// 메모리: 00000 KB / 시간: 00 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
중복되는 값이 존재하지 않고 왼쪽 자식 노드가 현재 노드보다 작고, 오른쪽 자식 노드가 현재 노드보다 큰 이진 탐색 트리를 구현하여,
후위 순회한 결과를 출력하는 문제
1. 이진 탐색 트리 구현
2. 후위 순회 구현
3. 탐색 결과 출력
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int result;
    static Node root;

    private static void input() throws IOException {
        root = new Node(Integer.parseInt(br.readLine()));
        String input;
        while ((input = br.readLine()) != null && !input.isEmpty()) {
            root.insert(Integer.parseInt(input));
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        postOrder(root);
        System.out.println(sb);
    }

    private static void postOrder(Node node) {
        if(node != null) {
            postOrder(node.left); // 왼쪽 자식 노드
            postOrder(node.right); // 오른쪽 자식 노드
            sb.append(node.value).append("\n"); // 부모 노드
        }
    }

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }

        Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        void insert(int num) {
            if (num < this.value) {
                // 비어 있으면 새로 생성
                if (this.left == null)
                    this.left = new Node(num);
                // 비어 있지 않으면 그 자식 노드 밑에 insert
                else this.left.insert(num);
            } else {
                // 비어 있으면 새로 생성
                if (this.right == null)
                    this.right = new Node(num);
                // 비어 있지 않으면 그 자식 노드 밑에 insert
                else this.right.insert(num);
            }
        }
    }
}