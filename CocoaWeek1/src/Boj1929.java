import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Boj1929 {
    public  static   void main(String[] args) {
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in);

        StringTokenizer sto = new StringTokenizer((buf.readLine(), " "));

        int min = Integer.parseInt(sto.nextToken());
        int max = Integer.parseInt(sto.nextToken());

        boolean[] arr = new boolean[max + 1];
        arr[0] = true;
        arr[1] = true;

        for (int i = 2; i <= Math.sqrt(max); i++) {
            if (arr[i] == true) continue;
            for (int j = i * i; j < arr.length; j+=i) {

            }

        }
    }
}
