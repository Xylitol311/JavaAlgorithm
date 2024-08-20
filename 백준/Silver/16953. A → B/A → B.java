import java.io.*;
import java.util.StringTokenizer;

public class Main {
    // 숫자 B에서 A를 만드는 데 필요한 횟수를 구하기
    // B가 홀수면 10으로 나누기 연산
    // B가 짝수면 2로 나누는 연산
    // B가 A보다 클 때만 진행
    // 모든 연산 종료 후 B != A면 -1 출력
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static long a;
    static long b;
    static long result;

    public static void main(String[] args) throws IOException{
        input();
        solution();
        print();
    }

    private static void solution() {
        while (b > a) {
            // 끝자리가 1인 경우 10으로 나누기
            if (b % 10 == 1) {
                b /= 10;
            } else if (b % 2 == 0) { // 짝수인 경우 2로 나누기
                b >>= 1;
            } else { // 그 외는 실패 처리
                result = -1;
                return;
            }
            result++;
        }

        // 연산의 최솟값에 1을 더한 값 출력
        result++;

        if (a != b) {
            result = -1;
        }
    }

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        br.close();
    }

    private static void print() throws IOException {
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }
}
