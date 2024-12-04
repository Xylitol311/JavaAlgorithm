import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int[][] players = new int[11][11];
    static boolean[] visited;
    static int max;

    private static void input() throws IOException {
        for(int player = 0; player < 11; player++) {
            st = new StringTokenizer(br.readLine().trim());
            for(int position = 0; position < 11; position++) {
                players[player][position] = Integer.parseInt(st.nextToken());
            }
        }

        max = 0;
        visited = new boolean[11];
    }

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine().trim());
        for(int i = 0; i < t; i++) {
            input();
            backtracking(0, 0);
            sb.append(max).append("\n");
        }
        System.out.println(sb);
    }

    public static void backtracking(int pos, int total) {
        if(pos == 11) {
            max = Math.max(max, total);
            return;
        }

        for(int i = 0; i < 11; i++) {
            if(!visited[i] && players[pos][i] != 0) {
                visited[i] = true;
                backtracking(pos + 1, total + players[pos][i]);
                visited[i] = false;
            }
        }
    }
}