import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 울타리의 개수나 위치는 중요하지 않음
// 양을 지킬 수 있는 지와 늑대가 양에게 가지 못하게만 하면 됨
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static final int FAIL = 0;
    static final int SUCCESS = 1;

    static int maxRow;
    static int maxCol;
    static char[][] field;
    static int result = SUCCESS;

    // 상하좌우
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        input();
        bfs();
        print();
    }

    private static void bfs() {
        Queue<Node> nodes = new LinkedList<>();

        // 늑대일 경우 큐에 추가
        for (int row = 0; row < maxRow; row++) {
            for (int col = 0; col < maxCol; col++) {
                if (field[row][col] == 'W') {
                    nodes.add(new Node(row, col));
                }
            }
        }

        // 큐에 들어간 늑대 좌표를 하나씩 꺼내서 늑대 주변을 울타리로 둘러쌈
        // 늑대 바로 주변에 양이 있으면 0을 리턴
        while (!nodes.isEmpty()) {
            Node node = nodes.poll();
            int y = node.y;
            int x = node.x;

            for (int direction = 0; direction < 4; direction++) {
                int nx = x + dx[direction];
                int ny = y + dy[direction];

                if (nx < 0 || nx >= maxCol || ny < 0 || ny >= maxRow) continue;

                // 양이 바로 옆에 있으면 실패
                if (field[ny][nx] == 'S') {
                    result = FAIL;
                    return;
                }
                if (field[ny][nx] == '.') {
                    field[ny][nx] = 'D';
                }
            }
        }
    }

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        maxRow = Integer.parseInt(st.nextToken());
        maxCol = Integer.parseInt(st.nextToken());

        field = new char[maxRow][maxCol];

        // 배열 입력
        for (int row = 0; row < maxRow; row++) {
            String str = br.readLine();
            for (int col = 0; col < maxCol; col++) {
                field[row][col] = str.charAt(col);
            }
        }
        br.close();
    }

    private static void print() throws IOException {
        bw.write(result + "\n");
        if (result == SUCCESS) {
            for (int row = 0; row < maxRow; row++) {
                for (int col = 0; col < maxCol; col++) {
                    bw.write(field[row][col]);
                }
                bw.write("\n");
            }
        }
        bw.flush();
        bw.close();
    }
}

class Node {
    int y;
    int x;

    public Node(int y, int x) {
        this.y = y;
        this.x = x;
    }
}
