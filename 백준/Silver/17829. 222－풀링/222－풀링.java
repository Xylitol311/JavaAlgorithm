import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int fieldSize, map[][];

    private static void input() throws IOException {
        fieldSize = Integer.parseInt(br.readLine());

        map = new int[fieldSize][fieldSize];

        for (int row = 0; row < fieldSize; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < fieldSize; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        System.out.println(recursion(0, 0, fieldSize));
    }

    private static int recursion(int nowR, int nowC, int size) {
        int[] nums = new int[4];
        // 기저 조건 : size가 2인 경우 최종 4칸 중 두 번째 큰 값을 찾아 return
        if (size == 2) {
            int idx = 0;
            for (int row = nowR; row < nowR + size; row++) {
                for (int col = nowC; col < nowC + size; col++) {
                    nums[idx++] = map[row][col];
                }
            }

        }
        // 그 외의 경우 배열을 4등분 해서 각각 재귀
        else {
            size /= 2;
            nums[0] = recursion(nowR, nowC, size);
            nums[1] = recursion(nowR, nowC + size, size);
            nums[2] = recursion(nowR + size, nowC, size);
            nums[3] = recursion(nowR + size, nowC + size, size);
        }
        Arrays.sort(nums);
        return nums[2];
    }
}