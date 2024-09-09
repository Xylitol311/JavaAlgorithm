package sw_expert.solved;

import java.util.Scanner;
public class d2_1859 {
    public static void main(String args[]) throws Exception
    {

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int answer = 0;
            int t=sc.nextInt();
            int[] nums = new int[t];
            for(int i = 0; i < t; i++) {
                nums[i] = sc.nextInt();
            }
            int max = nums[t-1];
            int now = nums[t-2];
            if (t == 2) {
                answer = max-now > 0 ? max-now : 0;
                System.out.printf("#%d %d\n", test_case, answer);
                continue;
            }
            for(int i = t - 2; i >= 0; i--){
                now = nums[i];
                if (max - now > 0) {
                    answer += max - now;
                } else {
                    max = now;
                }
            }
            System.out.printf("#%d %d\n", test_case, answer);
        }
    }
}
