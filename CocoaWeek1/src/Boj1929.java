import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Boj1929 {
    public static boolean[] prime;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int min = sc.nextInt();
        int max = sc.nextInt();

        prime = new boolean[max + 1];
        get_prime();

        for(int i = min; i <= max; i++) {
            //소수는 false. false값 출력
            if(!prime[i]) System.out.println(i);
        }
    }

    public static void get_prime() {
        prime[0] = prime[1] = true; //0과 1은 소수가 아니니까 true

        for(int i = 2; i <= Math.sqrt(prime.length); i++) {
            if(prime[i]) continue; //prime[i]가 true면 이미 그 배수는 true로 변환되었기 때문에 건너뜀
            for(int j = i * i; j < prime.length; j += i) {//소수일 경우 실행. 소수값의 제곱값부터 소수를 더해가며 소수의 배수를 true로 변환
                prime[j] = true;
            }
        }
    }
}
