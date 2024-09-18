import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
이분 탐색을 이용하여 거리 별로 공유기를 몇대 설치 가능한지 확인하고,
주어진 공유기 개수만큼 설치가 가능한 거리 중 최대값을 찾아 출력
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int numOfHouse, numOfNetwork;

    public static int[] house;

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine()," ");

        numOfHouse = Integer.parseInt(st.nextToken());
        numOfNetwork = Integer.parseInt(st.nextToken());

        house = new int[numOfHouse];

        for(int i = 0; i < numOfHouse; i++) {
            house[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(house);  // 이분탐색을 위해 정렬
    }

    public static void main(String[] args) throws IOException {
        input();
        System.out.println(solution());
    }

    private static int solution() {
        int lo = 1;
        int hi = house[numOfHouse - 1] - house[0] + 1; // 마지막 집과 첫번째 집의 거리(처음 최대 거리)

        while(lo < hi) { //upper bound 방식
            int mid = (hi + lo) / 2;

            if(canInstall(mid) < numOfNetwork) { // 현재 거리로 설치 가능한 공유기가 주어진 것보다 적을 경우
                hi = mid;
            }
            else {
                lo = mid + 1;
            }
        }

        // lo는 탐색 값을 초과하는 첫 번째 값을 가리키기 때문에 - 1을 해줘야 조건에 맞는 값을 출력 가능
        return lo - 1;
    }

    public static int canInstall(int distance) {
        int count = 1; // 첫 번째 집은 항상 설치하므로 1로 시작
        int lastHouse = house[0];

        for(int i = 1; i < house.length; i++) {
            int nowHouse = house[i];

            if(nowHouse - lastHouse >= distance) { // 주어진 거리보다 집들간의 거리가 더 큰 경우 카운트
                count++;
                lastHouse = nowHouse;
            }
        }
        return count;
    }
}