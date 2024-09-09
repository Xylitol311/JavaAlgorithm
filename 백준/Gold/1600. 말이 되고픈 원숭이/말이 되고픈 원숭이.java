import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Main {
	public static int K;
	public static int W, H;
	public static int result=-1;
	public static int[][] map;
	public static boolean[][][] visited;
	public static Queue<Node> q = new LinkedList<Node>();
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	K = Integer.parseInt(st.nextToken());
    	st = new StringTokenizer(br.readLine());
    	W = Integer.parseInt(st.nextToken());
    	H = Integer.parseInt(st.nextToken());
    	map = new int[H][W];
    	visited = new boolean[H][W][K+1];
    	
    	for(int i=0;i<H;i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j=0;j<W;j++) {
    			map[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	
    	BFS();
    	System.out.println(result);
    	
    
    }
    public static void BFS() {
    	//일반움직임
    	int[] dx = {-1,1,0,0};
    	int[] dy = {0,0,-1,1};
    	
    	//말 움직임
    	int[] horsedx = {-1, -2, -2, -1, 1, 2, 2, 1};
    	int[] horsedy = {-2, -1, 1, 2, 2, 1, -1, -2};
    	q.offer(new Node(0,0,0,K));
    	
    	//처음 (0,0) 시작점 초기화.
    	visited[0][0][K] = true;
    	
    	while(!q.isEmpty()) {
    		Node temp = q.poll();
    		int x = temp.x;
    		int y = temp.y;
    		int cnt = temp.cnt;
    		int horsemovecnt = temp.horsemovecnt;
    		
    		if(x == H-1 && y == W-1) {
    			result = cnt;
    			return ;
    		}
    		
    		//먼저 4가지 방향으로 가는 경우
    		for(int dir=0;dir<4;dir++) {
    			int nx = x + dx[dir];
    			int ny = y + dy[dir];
    			
    			if( nx < 0 || nx >= H || ny < 0 || ny >= W) continue;
    			if(visited[nx][ny][horsemovecnt] == true || map[nx][ny] == 1) continue;
    			//이동을 하여 cnt + 1, 말의 움직임이 아닌 일반 움직임으로 처리했으니 horsemovecnt는 변화없음
    			visited[nx][ny][horsemovecnt] = true;
    			q.offer(new Node(nx, ny, cnt+1, horsemovecnt));
    		}
    		
    		if(horsemovecnt == 0) continue;
    		for(int dir=0;dir<8;dir++) {
    			int nx = x + horsedx[dir];
    			int ny = y + horsedy[dir];
    			if( nx < 0 || nx >= H || ny < 0 || ny >= W) continue;
    			if(visited[nx][ny][horsemovecnt - 1] == true || map[nx][ny] == 1 ) continue;
    			visited[nx][ny][horsemovecnt - 1] = true;
    			q.offer(new Node(nx, ny, cnt+1, horsemovecnt - 1));
    		}
    	}
    	
    }
    
    
}
 
class Node{
	int x;
	int y;
	int cnt;
	int horsemovecnt;
	public Node(int x, int y, int cnt, int horsemovecnt) {
		this.x=x;
		this.y=y;
		this.cnt = cnt;
		this.horsemovecnt = horsemovecnt;
	}
}