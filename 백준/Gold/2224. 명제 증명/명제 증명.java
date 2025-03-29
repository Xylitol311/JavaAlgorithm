// 메모리: 00000 KB / 시간: 00 ms


import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    
    // 전역 변수: 52x52 boolean 배열 (대문자 0-25, 소문자 26-51)
    static boolean[][] graph = new boolean[52][52];
    static int N;
    
    private static void input() throws NumberFormatException, IOException  {
    	// 입력: 명제의 개수 N
        N = Integer.parseInt(br.readLine());
        
        // N개의 줄에 대해 "P => Q" 형태의 명제를 파싱하여 그래프에 저장
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            // 앞의 문자 (전건)
            char from = st.nextToken().charAt(0);
            // "=>" 토큰은 건너뜀
            st.nextToken();
            // 뒤의 문자 (후건)
            char to = st.nextToken().charAt(0);
            
            // 두 문자를 인덱스로 변환 후 그래프에 표시
            int fromIdx = charToIndex(from);
            int toIdx = charToIndex(to);
            graph[fromIdx][toIdx] = true;
        }
    }
    
    public static void main(String[] args) throws IOException {
        input();
        solve();
        System.out.print(sb.toString());
    }
    
    // 전체 문제를 해결하는 함수
    private static void solve() throws IOException {
        // 모든 가능한 중간 문자(k)를 거쳐, i에서 j로 가는 경로가 존재하는지 확인
        for (int k = 0; k < 52; k++) {
            for (int i = 0; i < 52; i++) {
                // i->k 경로가 존재할 때만 j를 체크
                if (graph[i][k]) {
                    for (int j = 0; j < 52; j++) {
                        if (graph[k][j]) {
                            graph[i][j] = true;
                        }
                    }
                }
            }
        }
        
        // 결과 리스트 생성: (전건, 후건) 쌍 저장
        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < 52; i++) {
            for (int j = 0; j < 52; j++) {
                // "P => P" 형태는 출력하지 않음
                if (i != j && graph[i][j]) {
                    result.add(new int[]{i, j});
                }
            }
        }
        
        // 정렬: 전건 인덱스(i) 순, 동일하면 후건 인덱스(j) 순 (대문자가 소문자보다 우선하도록 인덱스 사용)
        Collections.sort(result, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if(a[0] == b[0]) return a[1] - b[1];
                return a[0] - b[0];
            }
        });
        
        // 결과 출력: 결과 개수와 각 명제 출력
        sb.append(result.size()).append("\n");
        for (int[] pair : result) {
            // 인덱스를 문자로 변환하여 "P => Q" 형식으로 출력
            sb.append(indexToChar(pair[0])).append(" => ").append(indexToChar(pair[1])).append("\n");
        }
    }
    
    // 문자를 인덱스로 변환
    private static int charToIndex(char c) {
        if (Character.isUpperCase(c)) {
            return c - 'A';
        } else { // 소문자인 경우
            return c - 'a' + 26;
        }
    }
    
    // 인덱스를 문자로 변환
    private static char indexToChar(int index) {
        if (index < 26) {
            return (char)(index + 'A');
        } else {
            return (char)(index - 26 + 'a');
        }
    }
}
