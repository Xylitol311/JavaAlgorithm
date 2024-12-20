
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int k;
    static int holeNum;
    static char lastHor, lastVer;
    
    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
    
    // 입력 처리
    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        
        // 마지막 가로/세로 접기 방향 저장
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * k; i++) {
            char fold = st.nextToken().charAt(0);
            if (fold == 'D' || fold == 'U') {
                lastHor = fold;
            } else {
                lastVer = fold;
            }
        }
        
        holeNum = Integer.parseInt(br.readLine());
    }
    
    // 세로 대칭 구멍 위치 계산
    static int ver(int holeNum) {
        switch(holeNum) {
            case 0: return 1;
            case 1: return 0;
            case 2: return 3;
            default: return 2;
        }
    }
    
    // 가로 대칭 구멍 위치 계산
    static int hor(int holeNum) {
        switch(holeNum) {
            case 0: return 2;
            case 1: return 3;
            case 2: return 0;
            default: return 1;
        }
    }
    
    // 문제 해결 및 출력
    static void solve() {
        // 마지막 접기 방향에 따른 구멍 위치 조정
        if (lastVer == 'L' && lastHor == 'U') {
            // 왼쪽 위로 마지막으로 접힌 경우 - 그대로 유지
        } else if (lastVer == 'R' && lastHor == 'U') {
            // 오른쪽 위로 마지막으로 접힌 경우
            holeNum = ver(holeNum);
        } else if (lastVer == 'L' && lastHor == 'D') {
            // 왼쪽 아래로 마지막으로 접힌 경우
            holeNum = hor(holeNum);
        } else {
            // 오른쪽 아래로 마지막으로 접힌 경우
            holeNum = hor(ver(holeNum));
        }
        
        // 2x2 패턴의 각 위치 계산
        int one = holeNum;               // 왼쪽 위
        int two = ver(holeNum);          // 오른쪽 위
        int three = hor(holeNum);        // 왼쪽 아래
        int four = hor(ver(holeNum));    // 오른쪽 아래
        
        // 2x2 패턴 반복 출력
        int size = (int)Math.pow(2, k);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size/2; j++) {
                if (i % 2 == 0) {
                    System.out.print(one + " " + two + " ");
                } else {
                    System.out.print(three + " " + four + " ");
                }
            }
            System.out.println();
        }
    }
}