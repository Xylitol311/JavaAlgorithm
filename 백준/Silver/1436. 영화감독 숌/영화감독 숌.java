import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int num = Integer.parseInt(br.readLine());
        System.out.println(solution(num));
    }

    private static int solution(int num) {
        int count = 0;
        int current = 665; //시작하자마자 1 더할 거니까

        // 현재 카운트가 num보다 작을 때만 반복. 같으면 그때 숫자가 정답임.
        while (count < num) {
            current++;
            if (String.valueOf(current).contains("666")) {// current를 string으로 변환 후 666 포함 여부 확인
                count++;
            }
        }

        return current;
    }
}