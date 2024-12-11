// 메모리: 00000 KB / 시간: 00 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
dp와 dfs를 활용해 문제 해결
해결 방법
1. 각 플레이어의 백팀, 흑팀일 때 능력치를 입력받는다.
2. 플레이어를 순서대로 dfs를 통해 탐색하며 선택을 하지 않는 경우, 백팀인 경우, 흑팀인 경우로 dfs 탐색을 한다.
3. 만약 dfs 탐색 중 dp 배열에 현재 선수 차례에 선택된 백팀, 흑팀 선수의 수가 일치하는 데이터가 있다면 그 값을 return한다.
4. 모든 선수들을 순회했거나 백팀, 흑팀 선수를 모두 선택한 경우 return 한다.
필요한 변수
- int[] white, black : 각 플레이어의 백팀, 흑팀일 경우의 능력치 저장 배열. 최대 1000명까지 있으므로 편의상 크기는 1001로 지정.
- int[n][15][15] dp : dfs를 통해 각 플레이어를 순서대로 순회할 때 선택된 백팀, 흑팀의 선수의 수를 저장할 dp 배열
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, whites[], blacks[], dp[][][];

    private static void input() throws IOException {
        whites = new int[1001];
        blacks = new int[1001];
        dp = new int[1001][16][16];
        N = 0;
        String s = "";
        while ((s = br.readLine()) != null) {
//            if (s.equals("")) break;
            st = new StringTokenizer(s);
            N++;
            whites[N] = Integer.parseInt(st.nextToken());
            blacks[N] = Integer.parseInt(st.nextToken());
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        System.out.println(dfs(N, 15, 15));
    }

    private static int dfs(int pIndex, int white, int black) {
        // 모든 선수를 탐색한 경우
        if (pIndex == 0) return 0;

        // 모든 선수를 선택한 경우
        if (white == 0 && black == 0) return 0;

        // 이미 저장된 dp가 있는 경우
        if (dp[pIndex][white][black] != 0) return dp[pIndex][white][black];

        // 선수를 선택하지 않는 경우
        int answer = dfs(pIndex - 1, white, black);

        // 백팀으로 선택하는 경우(백팀 선수 자리가 남았을 때만)
        if (white > 0) answer = Math.max(answer, dfs(pIndex - 1, white - 1, black) + whites[pIndex]);
        // 흑팀으로 선택하는 경우(흑팀 선수 자리가 남았을 때만)
        if (black > 0) answer = Math.max(answer, dfs(pIndex - 1, white, black - 1) + blacks[pIndex]);

        return dp[pIndex][white][black] = answer;
    }
}