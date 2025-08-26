import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        solution(N);
    }

    private static void solution(int N) {
        int K = getDepth(N);

        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= N; col++) {
                if (isBlank(row, col, K)) {
                    sb.append(" ");
                } else {
                    sb.append("*");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static int getDepth(int N) {
        int depth = 0;
        int num = N;
        while (num > 1) {
            num /= 3;
            depth++;
        }
        return depth;
    }

    // 해당 좌표가 공백인지 판단하는 함수 (1-based 인덱싱)
    private static boolean isBlank(int row, int col, int K) {
        // 각 레벨(3^k)에서 가운데 영역인지 확인
        for (int k = 1; k <= K; k++) {
            int size = (int) Math.pow(3, k);
            int centerStart = size / 3 + 1;  // 가운데 구간 시작
            int centerEnd = 2 * size / 3;    // 가운데 구간 끝

            // 현재 레벨에서 가운데 영역에 있는지 확인
            int rowInBlock = ((row - 1) % size) + 1;  // 1-based로 변환
            int colInBlock = ((col - 1) % size) + 1;  // 1-based로 변환

            if (rowInBlock >= centerStart && rowInBlock <= centerEnd &&
                colInBlock >= centerStart && colInBlock <= centerEnd) {
                return true;
            }
        }
        return false;
    }
}