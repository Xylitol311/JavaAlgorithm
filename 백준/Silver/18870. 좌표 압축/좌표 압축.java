import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/*
1. 원본 배열 입력
2. 복사해서 내림차순 정렬
3. 카운트 체크 배열과 순회할 때 방문배열 만들기(방문배열은 각 숫자의 순회가 시작할 때마다 초기화)
    1) 카운트 배열은 -1로 초기화
4. 정렬된 배열의 숫자를 차례대로 탐색하며 그 숫자보다 작은 숫자를 카운트하여 카운트 체크 배열에 갱신
    1) 만약 이미 카운트한 값이 있는 경우: 다음 숫자 탐색
    2) 만약 카운트한 값이 없는 경우: 카운트 배열의 값이 -1인 경우
        a) 그 다음부터 순회하며 방문여부를 체크
            가) 만약 방문한 경우: 통과
            나) 방문하지 않은 경우: 카운트를 하나 올리고 방문 체크
        b) 순회를 다 했다면 카운트를 카운트 체크 배열에 반영하고 4.로 돌아가 다음 숫자 탐색
 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int n;
    static int[] inputArr;

    private static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        inputArr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            inputArr[i] = Integer.parseInt(st.nextToken());
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
        System.out.println(sb.toString());
    }

    private static void solution() {
        // 1. set을 이용해 중복 제거
        Set<Integer> uniqueSet = new HashSet<>();
        for (int num : inputArr) {
            uniqueSet.add(num);
        }

        // 2. 정렬
        List<Integer> sortedList = new ArrayList<>(uniqueSet);
        Collections.sort(sortedList);

        // 3. 각 값을 키, 인덱스를 값으로 map에 저장
        //      각 값이 위치한 인덱스가 그 값보다 작은 수의 개수
        Map<Integer, Integer> resultMap = new HashMap<>();
        for (int idx = 0; idx < sortedList.size(); idx++) {
            resultMap.put(sortedList.get(idx), idx);
        }

        // 4. 결과 출력
        for (int num : inputArr) {
            sb.append(resultMap.get(num) + " ");
        }
    }
}