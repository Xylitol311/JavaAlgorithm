import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
최소신장트리 문제.
문제의 요구사항은 N개의 국가가 있을 때 이 국가들을 모두 방문할 수 있는 최소 비행기 종류의 수를 찾는 것.

<조건>
- 비행기는 양 국가를 양방향으로 이동한다.(양방향 그래프)
- 주어지는 비행 스케줄은 연결 그래프를 이룬다.

<풀이>
처음엔 인접행렬이나 인접리스트를 활용해 BFS 탐색을 할까 했지만,
주어진 조건 중 양방향 그래프이면서 항상 연결 그래프를 이룬다는 것을 보고
정답은 언제나 N-1이라는 것을 깨달음.
위 조건과 같을 때 주어진 N개의 노드를 이을 수 있는 최소 간선의 수(비행기 종류의 수)는 언제나 N-1이기 때문.
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int result;

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        int numOfCountries = Integer.parseInt(st.nextToken());
        int numOfAirplanes = Integer.parseInt(st.nextToken());

        // 비행기 종류 입력(굳이 입력받지 않고 흘림)
        // 무조건 N-1이 정답이기 때문
        for (int i = 0; i < numOfAirplanes; i++) {
            br.readLine();
        }
        result = numOfCountries - 1;
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            input();
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }

}