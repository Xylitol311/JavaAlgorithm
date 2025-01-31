// 메모리: 00000 KB / 시간: 00 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
민수는 철수의 카드보다 큰 카드 중에서 가장 작은 카드를 내야하고
한 번 사용한 카드는 다시 사용불가.

[풀이 방법]
1. Union-find를 활용해서 사용할 수 있는 카드를 관리함.
2. 이진 탐색을 활용하여 철수의 카드보다 큰 카드 중에서 가장 작은 카드를 탐색
3. 사용한 카드는 union() 메서드를 활용해 다음으로 큰 카드와 연결

[설명]
카드 배열 [4,6,7]
부모 배열 [0,1,2]
민수가 뽑은 카드 4, 6, 7이 있고 철수가 5를 두번 낸다고 가정했을 때
(첫 턴)
이진 탐색을 통해 5보다 큰 수 중 작은 6을 내야한다는 것을 찾음.
카드 배열에서 6의 인덱스는 1. 부모 배열의 인덱스 1의 값은 1. (인덱스와 그 값이 동일하면 사용 가능한 카드라는 얘기)
6번 카드를 출력하고 union()을 통해 사용 처리를 해준다. (부모 배열: [0, 2, 2])
(둘째 턴)
이진 탐색을 통해 5보다 큰 수 중 작은 6을 내야한다는 것을 찾음.
카드 배열에서 6의 인덱스는 1. 부모 배열의 인덱스 1의 값은 2. (이미 1번 인덱스의 카드를 사용했으므로 그 다음으로 큰 2번 인덱스)
카드 배열에서 2번 인덱스인 7번 카드를 출력하고 union()을 통해 사용 처리.

 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M, K;
    static int parents[], cards[];

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        parents = new int[M]; // union-find를 위한 인덱스 배열
        cards = new int[M]; // 민수가 뽑은 카드 배열. 가장 큰 카드를 뽑은 경우 처리를 위해 M + 1

        // 민수 카드 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
            parents[i] = i;  // 초기에는 인덱스와 값을 동일하게 설정
        }
        Arrays.sort(cards); // 이진 탐색을 위해 정렬
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
        System.out.println(sb.toString());
    }

    private static void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int nowCSCard = Integer.parseInt(st.nextToken());

            // 철수보다 큰 것 중 가장 작은 카드의 인덱스 탐색
            int cardIdx = binarySearch(cards, nowCSCard);

            // 사용 가능한 다음 카드의 인덱스 탐색
            int possibleIdx = find(cardIdx);

            sb.append(cards[possibleIdx]).append("\n");

            // 사용한 카드 처리(다음 인덱스의 값으로 변경)
            union(possibleIdx, possibleIdx + 1);
        }
    }

    private static int find(int a) {
        if (parents[a] == a) {
            return a;
        }
        return parents[a] = find(parents[a]); // 경로 압축
    }

    private static void union(int a, int b) {
        // 배열 범위를 벗어날 경우 중지
        if (b >= M) return;

        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) return;
        // b가 항상 a보다 큰 값이 입력됨.
        // 해당 인덱스의 카드를 낸 경우 똑같은 인덱스가 나왔을 땐 그 다음으로 큰 카드를 내야함.
        parents[rootA] = rootB;
    }

    // 이진 탐색으로 특정 값보다 큰 첫 번째 값의 인덱스를 찾는 함수
    static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}