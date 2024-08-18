package baekjoon.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj24174 {
    public static int cnt = 0, target = 0; // cnt: 교환한 횟수, target: 배열을 출력할 횟수
    public static boolean isOut = false; // cnt가 K에 도달하기 전에 모든 교환이 끝났음을 확인할 변수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num = Integer.parseInt(st.nextToken()); // 배열의 크기
        target = Integer.parseInt(st.nextToken()); // 목표 K
        int[] arr = new int[num + 1]; // 힙을 저장할 배열
        arr[0] = 0; // 맨 처음 인덱스는 비워놓기
        st = new StringTokenizer(br.readLine());

        for (int i = 1; i < arr.length; i++) arr[i] = Integer.parseInt(st.nextToken());
        heapSort(arr);

        if (!isOut) {
            System.out.print(-1);
        }
    }

    public static void swap (int[] arr, int a, int b){
        cnt++;
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
        if(cnt == target){ // 교환 후 cnt가 K(target)에 도달했다면 현재 상태 출력
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < arr.length; i++) {
                sb.append(arr[i]);
                sb.append(" ");
            }
            System.out.println(sb.toString());
            isOut = true;
        }
    }

    public static void heapSort(int[] arr) {
        // 최소 힙 만들기
        buildMinHeap(arr, arr.length - 1);
        for (int i = arr.length - 1; i > 1 ; i--) { // 최소 힙을 만족하면 가장 앞에 값이 가장 작은 수가 앞에 위치
            // 정렬되지 않은 맨 뒤에 값과 위치 변경. 가장 작은 값을 정렬되지 않은 범위 중 맨 뒤로 보냄
            swap(arr, 1 , i);
            // 재귀를 통해정렬되지 않은 값들이 최소 힙 조건을 만족하도록 하여 가장 작은 값이 맨 앞에 위치하도록 확인 및 변경
            heapify(arr, 1, i - 1);
        } // 반복문이 끝나면 오름차순으로 정렬됨.
    }

    public static void buildMinHeap(int[] arr, int num) { // 최소 힙 만들기
        for (int i = num / 2; i >= 1 && !isOut; i--) {
            heapify(arr, i, num);
        }
    }

    public static void heapify(int[] arr, int k, int num) { // 최소 힙 조건에 일치하는지 확인
        int left = 2 * k;
        int right = 2 * k + 1;
        int smaller = -1;

        // 자식노드 left와 right 중 어느 것이 더 작은 지 비교
        if (right <= num) { // right가 num보다 큰 경우는 없는 노드라는 뜻
            smaller = arr[left] < arr[right] ? left : right;
        } else if (left <= num) { // left가 num보다 큰 경우는 없는 노드라는 뜻
            smaller = left;
        } else { // 자식 노드가 존재하지 않으니 return
            return;
        }

        // left와 right 중 작은 쪽이 부모노드보다 작은 지 확인.
        if (arr[smaller] < arr[k]) {
            swap(arr,k,smaller); // 만약 자식노드가 부모노드보다 작으면 두 위치를 스왑
            heapify(arr, smaller, num); // 바꾼 결과가 최소 힙 조건을 만족하는지 확인
        }
    }
}