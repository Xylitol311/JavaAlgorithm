import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Integer> q = new LinkedList<>();

        // Queue에 1~N까지 값을 삽입한다.
        for (int i = 1; i <= N; i++) {
            q.offer(i);
        }

        // 정답을 저장할 문자열 StringBuilder 사용
        StringBuilder sb = new StringBuilder();
        sb.append("<");

        // Queue의 사이즈가 1이 될 때까지 반복
        while(q.size() != 1) {
            // K - 1번째까지 처음에 있던 값을 맨 뒤로 보냄
            for (int i = 0; i < K - 1; i++) {
                q.offer(q.poll());
            }
            // K번째 값은 poll한 후 출력
            sb.append(q.poll() + ", ");
        }

        // Queue의 사이즈가 1일 때 남은 요소를 poll한다.
        sb.append(q.poll() + ">");

        bw.write(sb.toString() + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
