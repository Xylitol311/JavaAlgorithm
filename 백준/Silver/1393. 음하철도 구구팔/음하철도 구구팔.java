import java.io.*;
import java.util.*;

public class Main {
    static int xs, ys, dx, dy, xp, yp;
    
    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        xp = Integer.parseInt(st.nextToken());
        yp = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        xs = Integer.parseInt(st.nextToken());
        ys = Integer.parseInt(st.nextToken());
        dx = Integer.parseInt(st.nextToken());
        dy = Integer.parseInt(st.nextToken());
    }
    
    // 두 수의 최대공약수를 구함
    static int gcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    
    // 두 점 사이의 거리 제곱을 계산
    static long getDistanceSquare(int x1, int y1, int x2, int y2) {
        long diffX = x1 - x2;
        long diffY = y1 - y2;
        return diffX * diffX + diffY * diffY;
    }
    
    static void solution() {
        // 속도 벡터를 최대공약수로 나누어 최소 단위로 만듦
        int g = gcd(dx, dy);
        if (g != 0) {
            dx /= g;
            dy /= g;
        }
        
        long minDist = getDistanceSquare(xs, ys, xp, yp);
        int answerX = xs;
        int answerY = ys;
        
        // 시간을 증가시키며 최소 거리를 찾음
        int currentX = xs;
        int currentY = ys;
        long prevDist = minDist;
        
        while (true) {
            currentX += dx;
            currentY += dy;
            
            long currentDist = getDistanceSquare(currentX, currentY, xp, yp);
            
            if (currentDist < minDist) {
                minDist = currentDist;
                answerX = currentX;
                answerY = currentY;
                prevDist = currentDist;
            } else if (currentDist > prevDist) {
                // 거리가 증가하기 시작하면 종료
                break;
            }
            
            prevDist = currentDist;
        }
        
        System.out.println(answerX + " " + answerY);
    }
    
    public static void main(String[] args) throws IOException {
        input();
        solution();
    }
}