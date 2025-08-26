

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static char[][] pattern;

    public static void main(String[] args) throws IOException {
        int N = input();
        solution(N);
    }
    
    private static int input() throws IOException {
        return Integer.parseInt(br.readLine());
    }

    private static void solution(int N) {
        pattern = new char[N][N];
        drawPattern(0, 0, N, false);
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(pattern[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    // 재귀적으로 패턴을 그리는 함수
    private static void drawPattern(int x, int y, int size, boolean isBlank) {
        if (isBlank) {
            // 공백 영역이면 모두 공백으로 채움
            for (int i = x; i < x + size; i++) {
                for (int j = y; j < y + size; j++) {
                    pattern[i][j] = ' ';
                }
            }
            return;
        }
        
        if (size == 1) {
            pattern[x][y] = '*';
            return;
        }
        
        int newSize = size / 3;
        int cnt = 0;
        
        // 3×3 격자로 나누어 재귀 호출
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) {
                    // 가운데는 공백
                    drawPattern(x + i * newSize, y + j * newSize, newSize, true);
                } else {
                    // 나머지는 패턴 그리기
                    drawPattern(x + i * newSize, y + j * newSize, newSize, false);
                }
            }
        }
    }
}