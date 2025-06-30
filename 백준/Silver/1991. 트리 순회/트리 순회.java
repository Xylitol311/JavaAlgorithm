import java.io.*;
import java.util.*;

class Node {
    char data;
    Node left;
    Node right;
    
    Node(char data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

public class Main {
    static Map<Character, Node> nodeMap = new HashMap<>();
    
    // 입력 처리 함수
    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        // 모든 노드를 미리 생성
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char parent = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);
            
            // 부모 노드가 없으면 생성
            if (!nodeMap.containsKey(parent)) {
                nodeMap.put(parent, new Node(parent));
            }
            
            // 왼쪽 자식이 '.'이 아니면 연결
            if (left != '.') {
                if (!nodeMap.containsKey(left)) {
                    nodeMap.put(left, new Node(left));
                }
                nodeMap.get(parent).left = nodeMap.get(left);
            }
            
            // 오른쪽 자식이 '.'이 아니면 연결
            if (right != '.') {
                if (!nodeMap.containsKey(right)) {
                    nodeMap.put(right, new Node(right));
                }
                nodeMap.get(parent).right = nodeMap.get(right);
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        input();
        
        Node root = nodeMap.get('A'); // 루트는 항상 A
        
        preorder(root);   // 전위 순회
        System.out.println();
        inorder(root);    // 중위 순회
        System.out.println();
        postorder(root);  // 후위 순회
        System.out.println();
    }
    
    // 전위 순회 (루트 -> 왼쪽 -> 오른쪽)
    static void preorder(Node node) {
        if (node == null) return;
        
        System.out.print(node.data);
        preorder(node.left);
        preorder(node.right);
    }
    
    // 중위 순회 (왼쪽 -> 루트 -> 오른쪽)
    static void inorder(Node node) {
        if (node == null) return;
        
        inorder(node.left);
        System.out.print(node.data);
        inorder(node.right);
    }
    
    // 후위 순회 (왼쪽 -> 오른쪽 -> 루트)
    static void postorder(Node node) {
        if (node == null) return;
        
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.data);
    }
}