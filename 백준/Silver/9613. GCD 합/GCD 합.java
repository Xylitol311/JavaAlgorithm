import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int arr[];
    static long result;
    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());

        int num = Integer.parseInt(st.nextToken()); // 숫자의 개수
        result = 0; // 정답 저장
        arr = new int[num]; // 숫자 저장할 배열

        for (int i = 0; i < num; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            input();
            solution();
            System.out.println(result);
        }
    }

    private static void solution() {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                result += gcd(arr[i], arr[j]);
            }
        }
    }

    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}