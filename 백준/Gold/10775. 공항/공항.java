// 메모리: 00000 KB / 시간: 00 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
union-find를 이용해야 1초 안에 풀 수 있는 문제
- i번째 비행기를 1부터 gi번 게이트 중 하나에 영구적으로 도킹하므로 무조건 gi번에 가까운 큰 수에 우선적으로 배치하는 것이 많은 비행기를 배치할 수 있다.
- ex) 4번과 1번 비행기가 순서대로 들어올 때 4번 비행기를 4번 게이트에 배치하면 1번 비행기도 도킹할 수 있지만 4번 비행기를 1번 게이트에 배치하면 1번 비행기는 도킹할 수 없다.
1. 들어온 비행기를 find()를 이용해 도킹할 수 있는 가장 큰 수의 게이트를 찾아서 도킹한다.
2. 도킹이 될 때 union()을 이용해서 이미 도킹된 게이트를 도킹할 수 없도록 연결하고 그와 동시에 도킹 정보를 저장하는 배열에 반영한다.
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int G, P, dokingGate[];

    private static void input() throws IOException {
        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());

        dokingGate = new int[G + 1];

        // 자기 자신의 인덱스를 값으로 갖도록 초기화
        for (int i = 1; i <= G; i++) {
            dokingGate[i] = i;
        }

    }

    public static void main(String[] args) throws IOException {
        input();
        int planeNum, possibleGateNum, result = 0;
        for (int i = 0; i < P; i++) {
            // 현재 비행기 번호
            planeNum = Integer.parseInt(br.readLine());
            // 도킹 가능한 게이트 번호
            possibleGateNum = find(planeNum);

            // 도킹이 가능한 경우(0인 경우 가장 작은 1번 게이트에도 도킹이 불가능하므로 0이 아니어야 함)
            if (possibleGateNum != 0) {
                union(possibleGateNum, possibleGateNum - 1);
                result++;
            } else {
                // 도킹 불가능한 경우 바로 종료
                break;
            }
        }

        System.out.println(result);
    }

    private static int find(int a){
        if (a == dokingGate[a]) {
            return a;
        }
        return dokingGate[a] = find(dokingGate[a]);
    }

    private static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        // 무조건 a가 b보다 크므로 새로 도킹한 a에 그보다 작은 b값을 입력
        // dokingGate[4]에 도킹한 경우 그보다 한칸 작은 인덱스의 값 dokingGate[3]의 값을 dokingGate[4]에 입력함으로써 다음 비행기가 도킹할 수 있는 게이트를 빠르게 찾을 수 있도록 함.
        dokingGate[rootA] = rootB;
    }
}