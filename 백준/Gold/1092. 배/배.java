import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        int[] input = input();
        int result = solution(input);
        System.out.println(result);
    }
    
    public static int[] input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[] cranes = Arrays.stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
        
        int M = Integer.parseInt(br.readLine());
        int[] boxes = Arrays.stream(br.readLine().split(" "))
                           .mapToInt(Integer::parseInt)
                           .toArray();
        
        // 크레인 개수, 크레인 무게들, 박스 개수, 박스 무게들을 하나의 배열로 합침
        int[] result = new int[N + M + 2];
        result[0] = N;
        result[1] = M;
        System.arraycopy(cranes, 0, result, 2, N);
        System.arraycopy(boxes, 0, result, N + 2, M);
        
        return result;
    }
    
    // 그리디 알고리즘으로 최소 시간 계산
    public static int solution(int[] input) {
        int N = input[0];
        int M = input[1];
        
        // 크레인 무게 제한 추출 및 내림차순 정렬
        Integer[] cranes = new Integer[N];
        for (int i = 0; i < N; i++) {
            cranes[i] = input[i + 2];
        }
        Arrays.sort(cranes, Collections.reverseOrder());
        
        // 박스 무게 추출 및 내림차순 정렬
        List<Integer> boxes = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            boxes.add(input[i + N + 2]);
        }
        boxes.sort(Collections.reverseOrder());
        
        // 가장 무거운 박스를 가장 큰 크레인도 못 드는 경우
        if (boxes.get(0) > cranes[0]) {
            return -1;
        }
        
        int time = 0;
        
        // 모든 박스가 처리될 때까지 반복
        while (!boxes.isEmpty()) {
            time++;
            
            // 각 크레인마다 처리할 수 있는 박스 찾기
            for (int crane : cranes) {
                for (int i = 0; i < boxes.size(); i++) {
                    if (crane >= boxes.get(i)) {
                        boxes.remove(i);
                        break;
                    }
                }
            }
        }
        
        return time;
    }
}