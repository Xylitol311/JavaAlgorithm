package sw_expert.solved;

import java.util.Scanner;

public class Solution2 {

    // 0 1 2
    // 좌 상 우
    static int[] dr = {0,-1,0};
    static int[] dc = {-1,0,1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = 10; // 테스트케이스 10개

        for (int a=0; a<T; a++) { // 10개의 테스트케이스
            int N = 100;
            int tc = sc.nextInt();
            int[][] arr = new int[N][N];

            // 배열 입력
            for (int i=0;i<N;i++) {
                for(int j=0;j<N;j++) {
                    arr[i][j] = sc.nextInt();
                }
            }

            // 출발 지점 찾기
            int r = 0; // 로우
            int c = 0; // 컬럼
            for(int i=0;i<N;i++) {
                for(int j=0;j<N;j++) {
                    if(arr[i][j]==2) {
                        r=i;
                        c=j;
                    }
                }
            }

            int d = 1; // 델타
            while(r+dr[1] > 0) {// row가 0에 도착할 때까지 반복
                switch(d) {
                    case 0: // 왼쪽으로 간다
                        while(c+dc[d] >=0 && arr[r+dr[d]][c+dc[d]]==1) { // 범위밖으로 이동X, 1따라서 이동
                            r = r+dr[d];
                            c = c+dc[d];
                        }
                        d++; // 방향을 위로 변경
                        break; // case 0 종료 (왼쪽으로 이동 끝)
                    case 1: // 위로 이동
                        // 왼쪽이 끝이 아니면서 1인 경우 -> 방향을 왼쪽으로 변경

                        // 오른쪽이 끝이 아니면서 1인 경우 -> 방향을 오른쪽으로 변경

                        // 나머지는 그냥 위로 이동하면 됨.(세로는 항상 시작부터 끝까지 이어져있기 때문에 위로 이동할 때 위쪽이 1인지 확인하는 과정 불필요)

                        /*
                        1. 왼쪽 끝에서 위로 이동하는 경우 체크
                        2. 오른쪽 끝에서 위로 이동하는 경우 체크
                        3. 양쪽 끝이 아닐 때 위로 이동하는 경우
                            a. 위쪽이 1이면 위로 이동
                            b. 위쪽이 1이 아닌 경우
                                ㄱ. 양쪽이 0일 때 위로 이동
                        4. 왼쪽 끝이 아니면서 왼쪽에 1이 있는 경우 왼쪽으로 이동
                        5. 오른쪽 끝이 아니면서 오른쪽에 1이 있는 경우 오른쪽으로 이동
                         */


                        r = r+dr[d];
                        c = c+dc[d];
                        // 왼쪽 끝이 아니면서 왼쪽에 1이 있는 경우 방향을 왼쪽으로 설정
                        if (c+dc[0]>=0 && arr[r+dr[0]][c+dc[0]]==1) { // 왼쪽끝 아니고 왼쪽에 1이있으면
                            d = 0;
                        } else if(c+dc[2]<=N && arr[r+dr[2]][c+dc[2]]==1){ // 오른쪽끝 아니고 오른쪽에 1이있으면
                            d = 2;
                        }

                        break;

                    // 오른쪽으로 이동
                    case 2:
                        while(c+dc[d] < N && arr[r+dr[d]][c+dc[d]]==1) {
                            r = r+dr[d];
                            c = c+dc[d];
                        }
                        d--;
                        break;
                }
            }
            System.out.println("#"+tc+" "+c); // 행의 합 최대값 + 열의 합 최대값 최대값
        }
    }
}
