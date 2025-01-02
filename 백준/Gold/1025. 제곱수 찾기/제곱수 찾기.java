import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
완전 제곱수: 어떤 정수를 제곱한 수

[풀이 방법]
- 만들 수 있는 모든 수에 대해서 완전 제곱수인지 판별 후 가장 큰 수를 출력한다.
1. for 문을 통해 행과 열의 수에 맞춰서 시작지점을 선택 후, 등차수열의 공차를 선택한다.
2. 공차만큼 더했을 때 범위에 포함될 경우, 완전 제곱수인지 확인하고 max와 비교하여 더 큰 수를 저장.
3. 위 과정을 반복 후 max 값을 출력한다.
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M, max, map[][];

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        max = -1;

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
        System.out.println(max);
    }

    private static void solution() {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                for (int dr = -N; dr < N; dr++) {
                    for (int dc = -M; dc < M; dc++) {
                        if (dr == 0 && dc == 0) {
                            continue;
                        }

                        // 첫 위치.
                        int num = 0;
                        int newRow = row;
                        int newCol = col;

                        while (newRow >= 0 && newRow < N && newCol >= 0 && newCol < M) {
                            num = num * 10 + map[newRow][newCol];

                            if (isPerfect(num)) {
                                max = Math.max(max, num);
                            }

                            newRow += dr;
                            newCol += dc;
                        }
                    }
                }
            }
        }
    }

    private static boolean isPerfect(int num) {
        int sqrt = (int) Math.sqrt(num);
        return sqrt * sqrt == num;
    }
}