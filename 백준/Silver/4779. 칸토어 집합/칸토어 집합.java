import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        StringBuilder result = new StringBuilder();

        while ((input = br.readLine()) != null && !input.isEmpty()) {
            int N = Integer.parseInt(input);
            int len = (int) Math.pow(3, N);
            char[] arr = new char[len];
            for (int i = 0; i < len; i++) {
                arr[i] = '-';
            }
            
            generateCantorSet(arr, len);

            result.append(arr).append("\n");
        }

        System.out.print(result.toString());
    }

    public static void generateCantorSet(char[] arr, int length) {
        int segment = length;
        while (segment > 1) {
            int third = segment / 3;
            for (int i = 0; i < length; i += segment) {
                for (int j = i + third; j < i + 2 * third; j++) {
                    arr[j] = ' ';
                }
            }
            segment /= 3;
        }
    }
}
