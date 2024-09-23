import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
조건
1. 탐사 시작 지점으로 돌아올 필요가 없다 -> 모든 행성 방문 시 탐색을 종료하고 최단 거리 갱신
2. 방문한 곳을 중복해서 방문 가능 -> 이미 방문했던 곳을 경유지로 거쳐서 갈 수 있는 최단 거리를 사용 가능 -> 플루이드 워셜
풀이
1. 플로이드 워셜을 사용해서 모든 행성 간의 최단 거리를 구한다.
2. 순열을 통해서 지정된 시작 행성으로부터 모든 행성을 방문하는 최단 거리의 합을 구한다.
 */
public class Main {
    static int numOfPlanets, startPlanet, map[][], result;

    private static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 총 행성 수
        numOfPlanets = Integer.parseInt(st.nextToken());
        // 시작 행성 번호
        startPlanet = Integer.parseInt(st.nextToken());
        map = new int[numOfPlanets][numOfPlanets];

        for (int i = 0; i < numOfPlanets; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < numOfPlanets; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        System.out.println(solution());

    }

    private static int solution() {
        // 플로이드 워셜로 최단 거리 갱신
        for (int k = 0; k < numOfPlanets; k++) {
            for (int i = 0; i < numOfPlanets; i++) {
                if (i == k) {
                    continue;
                }
                for (int j = 0; j < numOfPlanets; j++) {
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }

        //순열 탐색
        result = 10001;
        permutation(startPlanet, 0, 1 << startPlanet);
        return result;
    }

    private static void permutation(int start, int time, int visited) {
        // (1 << numOfPlanets) - 1) == 전체 행성을 방문한 것과 같다.
        if (visited == (1 << numOfPlanets) - 1) {
            // 모든 행성 방문 시 현재 케이스의 소요 시간과 비교하여 최솟값 갱신
            result = Math.min(result, time);
            return;
        }

        for (int i = 0; i < numOfPlanets; i++) {
            // 방문 체크
            if ((visited & (1 << i)) != 0) {
                continue;
            }
            // 행성 i를 시작점으로 재귀 반복
            permutation(i, time + map[start][i], visited | (1 << i));
        }
    }
}