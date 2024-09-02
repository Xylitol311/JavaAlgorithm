import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N, M;
	static int[] num, newNum;
	static boolean[] visited;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		num = new int[N];
		newNum = new int[M];
		visited = new boolean[N];
		
		for(int i=0; i<N; i++) {
			num[i] = i+1;
		}
		sunyeol(0, 0);
	}
	
	static void sunyeol(int idx, int start) {
		if(idx == M) {
			for(int i=0; i<M; i++) {
				System.out.print(newNum[i]+" ");
			}
			System.out.println();
			return;
		}
		
		for(int i=start; i<N; i++) {  
			if(visited[i])
				continue;
			newNum[idx] = num[i];
			visited[i] = true;
			sunyeol(idx+1, i+1);  
			visited[i] = false;
		}
	}
}