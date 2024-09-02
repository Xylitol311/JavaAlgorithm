import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N;
	static int[] dr = {0,1,0,-1};
	static int[] dc = {1,0,-1,0};
	static char[][] zone;
	static boolean[][] visited;
	static Queue<int[]> queue;
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	N = sc.nextInt();
    	zone = new char[N][N];
    	visited = new boolean[N][N];

    	for(int r=0; r<N; r++) {
    		String str = sc.next();
    		for(int c=0; c<N; c++) {
    			zone[r][c] = str.charAt(c);
    		}
    	}
    	
    	int cnt = 0;
    	for(int r=0; r<N; r++) {
    		for(int c=0; c<N; c++) {
    			if(!visited[r][c]) {
    				bfs1(r,c);
    				cnt++;
    			}
    		}
    	}
    	
    	System.out.print(cnt+" ");
    	cnt = 0;
    	visited = new boolean[N][N];

    	for(int r=0; r<N; r++) {
    		for(int c=0; c<N; c++) {
    			if(!visited[r][c]) {
    				bfs2(r,c);
    				cnt++;
    			}
    		}
    	}
    	
    	System.out.println(cnt);

    }
    
    static void bfs1(int r, int c) {
    	queue = new LinkedList<>();
    	queue.add(new int[] {r,c});
    	visited[r][c] = true;
		char currColor = zone[r][c];

    	while(!queue.isEmpty()) {
    		int[] curr = queue.poll();
    		int currR = curr[0];
    		int currC = curr[1];
    		
    		for(int i=0; i<4; i++) {
    			int nr = currR+dr[i];
    			int nc = currC+dc[i];
    			
    			if(nr < N && nc < N && nr >= 0 && nc >= 0 && !visited[nr][nc] && zone[nr][nc] == currColor) {
    				queue.add(new int[] {nr,nc});
    				visited[nr][nc] = true;
    			}
    		}
    	}
    	
    }
    
    static void bfs2(int r, int c) {
    	queue = new LinkedList<>();
    	queue.add(new int[] {r,c});
    	visited[r][c] = true;
		char currColor = zone[r][c];

    	while(!queue.isEmpty()) {
    		int[] curr = queue.poll();
    		int currR = curr[0];
    		int currC = curr[1];
    		
    		for(int i=0; i<4; i++) {
    			int nr = currR+dr[i];
    			int nc = currC+dc[i];
    			
    			if(currColor == 'B') {
        			if(nr < N && nc < N && nr >= 0 && nc >= 0 && !visited[nr][nc] && zone[nr][nc] == 'B') {
        				queue.add(new int[] {nr,nc});
        				visited[nr][nc] = true;
        			}
    			} else {
        			if(nr < N && nc < N && nr >= 0 && nc >= 0 && !visited[nr][nc] && zone[nr][nc] != 'B') {
        				queue.add(new int[] {nr,nc});
        				visited[nr][nc] = true;
        			}
    			}
    		}
    	}
    }

}