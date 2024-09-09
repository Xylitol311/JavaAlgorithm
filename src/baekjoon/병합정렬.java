package baekjoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
어떤 배열이 주어진 경우 최대한으로 쪼개서 쪼갠 배열끼리 오름차순 정렬을 하면서 합치는 정렬 방법
6, 3, 5, 1 배열이 주어진 경우
[6, 3], [5, 1]로 나누고 이를 또
[6],[3],[5],[1]로 나눠서
[3,6], [1, 5]합치고
[1, 3, 5, 6]으로 완성
 */
public class 병합정렬 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int[] arr;
    static int[] tmp;
    static int count; // 파이프 최대 연결 개수
    static int arrSize;
    static int K;
    static int answer = -1;

    // 입력값 받기
    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        arrSize = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        count = 0;

        arr = new int[arrSize];
        tmp = new int[arrSize];

        // 배열 입력
        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < arrSize; idx++) {
            arr[idx] = Integer.parseInt(st.nextToken());
        }
    } // input();

    public static void main(String[] args) throws IOException {
        input();
        mergeSort(0, arrSize - 1);
        System.out.println(answer);
    } // main();

    static void mergeSort(int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(start, mid);
            mergeSort(mid + 1, end);
            merge(start, mid, end);
        }
    } // mergeSort();

    static void merge(int start, int mid, int end) {
        int i = start;
        int j = mid + 1;
        int tIdx = 0;
        while(i < mid && j <= end) {
            if (arr[i] <= arr[j]) {
                tmp[tIdx++] = arr[i++];
            } else {
                tmp[tIdx++] = arr[j++];
            }
        }
        while(i <= mid) {
            tmp[tIdx++] = arr[i++];
        }
        while(j <= end) {
            tmp[tIdx++] = arr[j++];
        }
        i = start;
        tIdx = 0;
        while(i <= end) {
            arr[i++] = tmp[tIdx++];
            count++;
            if (count == K) {
                answer = tmp[tIdx];
                return;
            }
        }
    }
}
