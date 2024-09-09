package baekjoon.solved.day0812;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Boj24511 {

    static int[] queueStack;
    static Deque[] deque;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        // 자료구조 종류 저장할 배열
        queueStack = new int[N];

        // 자료구조 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            queueStack[i] = Integer.parseInt(st.nextToken());
        }


        deque = new Deque[N];

        for (int i = 0; i < N; i++) {
            deque[i] = new ArrayDeque();
        }

        // 각 자료구조에 들어갈 값 배열
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            deque[i].add(Integer.parseInt(st.nextToken()));
        }

        int M = Integer.parseInt(br.readLine());


        // 덱을 이용해 큐와 스택처럼 데이터 관리
        // 1. 종류가 스택일 경우 값을 넣을 땐 마지막에, 값을 뺄 땐 마지막에서
        // 2. 종류가 큐일 경우 값을 넣을 땐 마지막에, 값을 뺄 땐 첫번째에서
    }

    public static void addOut(int num) {

    }
}