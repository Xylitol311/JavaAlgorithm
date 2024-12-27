// 메모리: 00000 KB / 시간: 00 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
주어진 채널과 고장난 리모컨 버튼을 입력받고 사용할 수 있는 버튼을 이용해 주어진 채널로 이동하는데 최소 몇번의 버튼을 눌러야 하는지 찾는 문제
[조건]
- 기본 채널은 100번
- 0번에서 밑으로 내려갈 수는 없다.
- 채널은 무한대
- N은 최대 500,000

[풀이]
1. 채널과 고장난 버튼을 입력 받는다.
2. 남아있는 숫자 버튼을 이용해 주어진 채널과 최대한 가깝도록 이동한다.
3. 이동하기 위해 버튼을 누르는 모든 횟수를 카운팅한다.
4. 최대한 가까운 채널로 이동했을 때 주어진 채널과의 차를 구한다.
5. 3번과 4번을 더한 합계를 출력한다.

[버튼 클릭 횟수가 최소가 되는 경우]
1. 직접 하나씩 이동하는 것과 최대한 가까운 채널로 이동 후 하나씩 이동하는 것 중 최솟값
2. 꼭 숫자 자릿수를 맞춰 가까운 채널로 이동 후 하나씩 이동하는 것보다 자릿수를 맞추지 않고 중간에 하나씩 이동하는 게 더 적은 경우도 있음

[숫자로 가까운 채널 탐색 방법]
1. N은 최대 6자릿수고 한자리 당 최대 9개의 숫자만 들어갈 수 있으므로 그냥 모든 숫자를 탐색한다.
2. N을 String 형태로 받고 dfs를 이용해 N의 자릿수와 depth가 일치할 때까지 dfs를 반복한다.
3. N의 자릿수와 일치하는 수가 완성되면 현재까지 depth와 N의 현재 숫자의 차이를 더하고 전역변수 min값과 비교하여 최솟값을 갱신한다.
4. 모든 자릿수에 대해 모든 숫자에 대한 처리가 끝나면 min값을 출력한다.
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static boolean[] remoteCon = new boolean[10];
    static int m;
    static int minVal;
    static String N;

    private static void input() throws IOException {
        N = br.readLine();
        minVal = Math.abs(Integer.parseInt(N) - 100);

        // 고장난 리모컨 버튼 입력
        m = Integer.parseInt(br.readLine());
        if (m == 0) return; // 버튼이 고장나지 않은 경우
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            remoteCon[Integer.parseInt(st.nextToken())] = true;
        }
    }

    public static void main(String[] args) throws IOException {
        input();

        // 목표 채널이 100인 경우
        if (N.equals("100")) {
            System.out.println(0);
            return;
        }

        // 리모컨이 고장나지 않은 경우
        if (m == 0) {
            // 직접 채널 번호를 누르는 것과 하나씩 이동하는 것 중 작은 값 출력
            System.out.println(Math.min(minVal, N.length()));
            return;
        }

        // 모든 버튼이 고장난 경우
        if (m == 10) {
            // 모든 버튼이 고장났을 땐 목표 채널에서 100과의 차이만큼 버튼을 눌러야 함
            System.out.println(minVal);
            return;
        }

        // 숫자를 이용해 이동 후 +,-를 하는 경우
        dfs(0, 0);

        System.out.println(minVal);
    }

    private static void dfs(int depth, int now) {
        if (depth == 6) return;

        for (int i = 0; i < 10; i++) {
            // 고장난 버튼이면 pass
            if (remoteCon[i]) continue;

            int next = now * 10 + i;
            int sum = depth + 1 + (Math.abs(Integer.parseInt(N) - next));
            minVal = Math.min(minVal, sum);

            dfs(depth + 1, next);
        }
    }
}