// 메모리: 00000 KB / 시간: 00 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
1. 정답으로 구할 K의 값의 최솟값을 min의 설정하고, 모든 금액의 합을 k의 값의 최댓값으로 하여 max에 저장한다.
2. min, max의 중간값을 mid로 설정하여 min<=max일 때만 이분 탐색을 진행한다.
3. 탐색마다 sum과 count를 초기화하고, 배열을 탐색하며 현재 금액을 sum에 저장.
4. sum이 mid보다 커지면 sum을 현재 금액으로 초기화하고 count++
5. 한 번의 탐색이 끝나면 count의 값을 M과 비교. 같으면 종료 후 출력, 작거나 같으면 max = mid - 1, 크면 min = mid + 1
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, min, max, money[];

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        money = new int[N];
        min = 1;
        max = 1;

        for (int i = 0; i < N; i++) {
            money[i] = Integer.parseInt(br.readLine());
            min = Math.max(min, money[i]);
            max += money[i];
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
        System.out.println(min);
    }

    private static void solution(){
        int mid, count, sum;
        while (min <= max) {
            mid = (min + max) / 2;

            sum = 0;
            count = 1;

            for (int i = 0; i < N; i++) {
                sum += money[i];
                if (sum > mid) {
                    sum = money[i];
                    count++;
                }
            }
            if (count > M) min = mid + 1;
            else max = mid - 1;
        }
    }

}