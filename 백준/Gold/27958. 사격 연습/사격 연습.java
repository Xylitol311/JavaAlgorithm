import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Node{
        int hp;
        boolean isFragment;

        Node(int hp){
            this.hp = hp;
            isFragment = false;
        }
    }
    static Node[][] board;
    static Node[][] tmpBoard;
    static ArrayList<ArrayList<Integer>> per;
    static int N;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        int ans = -1;

        // 보드 초기화
        board = new Node[N][N];
        tmpBoard = new Node[N][N];

        // 보드에 표적 정보 입력
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++){
                board[i][j] = new Node(Integer.parseInt(st.nextToken()));
            }
        }

        // 총알 정보 입력
        int[] bullet = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++){
            bullet[i] = Integer.parseInt(st.nextToken());
        }

        // 레일 번호 입력 (순열에서 사용)
        int[] line = new int[N];
        for (int i = 0; i < N; i++){
            line[i] = i;
        }
        // 순열 조합 시 선택된 레일 번호를 입력할 임시 배열
        int[] out = new int[K];

        // 방문 배열 초기화
        boolean[] visited = new boolean[N];

        // 순열 리스트 초기화 및 입력
        per = new ArrayList<>();
        getPermutation(line, N, K, 0, out);

        // 만들어진 순열 순서대로 발사
        for (ArrayList<Integer> element: per){
            // 임시 배열 초기화
            init();

            int scoreSum = 0;
            int bulletIdx = 0;

            // 선택된 레일에서 발사
            for (int shootingLine : element){
                // 왼쪽에서 오른쪽으로 총알 이동
                for (int i = 0; i < N; i++){
                    // 0이면 패스
                    if (tmpBoard[shootingLine][i].hp == 0){
                        continue;
                    }

                    // 보너스인 경우
                    if (tmpBoard[shootingLine][i].hp >= 10){
                        scoreSum += tmpBoard[shootingLine][i].hp;
                        tmpBoard[shootingLine][i].hp = 0;
                        break;
                    } else{
                        // 총알이 더 센 경우
                        if (tmpBoard[shootingLine][i].hp <= bullet[bulletIdx]){
                            // 새로 생긴 표적의 경우
                            if (tmpBoard[shootingLine][i].isFragment){
                                scoreSum += tmpBoard[shootingLine][i].hp;
                            } else{
                                scoreSum += board[shootingLine][i].hp;
                            }

                            // 현 위치 사방 탐색
                            for (int dir = 0; dir < 4; dir++){
                                int ny = shootingLine + dy[dir];
                                int nx = i + dx[dir];

                                // 범위 밖이면 패스
                                if (ny < 0 || ny >= N || nx < 0 || nx >= N){
                                    continue;
                                }

                                // 표적이 없고 1/4가 0이 아니면 새로 생성
                                if (tmpBoard[ny][nx].hp == 0){
                                    if (!tmpBoard[shootingLine][i].isFragment && board[shootingLine][i].hp / 4 != 0){
                                        tmpBoard[ny][nx].hp = board[shootingLine][i].hp / 4;
                                        // 파편 표적임을 표시
                                        tmpBoard[ny][nx].isFragment = true;
                                    } else{
                                        break;
                                    }
                                }
                            }

                            // 현 위치 표적 삭제
                            tmpBoard[shootingLine][i].hp = 0;
                        } else{
                            // 총알이 더 약한 경우 표적 hp 감소
                            tmpBoard[shootingLine][i].hp -= bullet[bulletIdx];
                        }
                        break;
                    }
                }
                // 다음 총알
                bulletIdx++;
            }

            // 모든 레일 발사가 끝나면 최댓값 갱신
            ans = Math.max(ans, scoreSum);
        }

        System.out.println(ans);
    }

    // 임시 보드 초기화
    public static void init(){
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                tmpBoard[i][j] = new Node(board[i][j].hp);
            }
        }
    }

    // 발사 레일 순열 조합
    public static void getPermutation(int[] line, int N, int totalRound, int depth, int[] out){
        if (depth == totalRound){
            ArrayList<Integer> result = new ArrayList<>();
            for(int element: out){
                result.add(element);
            }

            per.add(result);
            return;
        }

        for (int i = 0; i < N; i++){
            out[depth] = line[i];
            getPermutation(line, N, totalRound, depth + 1, out);
            out[depth] = 0;
        }
    }
}