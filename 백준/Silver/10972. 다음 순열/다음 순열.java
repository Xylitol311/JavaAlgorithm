import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, arr[];

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine().trim());
        arr = new int[N];

        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

    }

    public static void main(String[] args) throws IOException {
        input();
        if (!np())
            System.out.println(-1);
        else
            print();
    }

    private static boolean np() {
        int i = N - 1;
        // 꼭대기 찾기
        while (i > 0 && arr[i - 1] >= arr[i]) i--;
        if (i == 0) return false; // 이미 마지막

        // 꼭대기 앞 숫자보다 큰 수 중에 가장 작은 수 탐색해서 교체
        int j = N - 1;
        while (arr[i - 1] >= arr[j]) j--;
        swap(i - 1, j);

        // 꼭대기부터 오름차순 정렬
        j = N - 1;
        while (i < j) {
            swap(i++, j--);
        }
        return true;
    }

    private static void swap(int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static void print() {
        for (int num : arr) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
        sb.setLength(0);
    }
}