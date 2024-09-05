import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 인접 리스트를 써서 모든 학생과 키 순서를 입력 받는다.
 * 2. 각 노드별 진입, 진출 차수를 카운트할 배열을 만든다.
 * 2. 각 노드 별로 자신보다 큰 학생들을 순회하며 진출 차수를 카운트한다.
 * 		2-1. 이때 탐색을 하며 진입하게 되는 노드들의 진입 차수도 1씩 카운트한다.
 * 		2-2. 중복 방문하지 않도록 방문 배열을 이용해서 처리한다.
 */
public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static class Student {
		int to;
		Student next;

		public Student(int to, Student next) {
			super();
			this.to = to;
			this.next = next;
		}
	}

	static Student[] students;
	static int[] counts;
	static int studentsSize, edgeSize;

	private static void input() throws IOException {
		studentsSize = Integer.parseInt(br.readLine());
		edgeSize = Integer.parseInt(br.readLine());

		students = new Student[studentsSize + 1];
		counts = new int[studentsSize + 1];

		for (int edge = 0; edge < edgeSize; edge++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			students[from] = new Student(to, students[from]);
		}
	}

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			input();
			solution();
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}

	static boolean[] visited;
	static int answer;
	private static void solution() throws IOException {
		answer = 0;
		
		// dfs 탐색을 통해 진출, 진입 차수 갱신
		for (int idx = 1; idx <= studentsSize; idx++) {
			visited = new boolean[studentsSize + 1];
			visited[idx] = true;
			dfs(idx, idx);
		}
		
		// counts 배열의 값이 전체 학생 수 - 1인 경우 정답 카운트
		for(int idx = 1; idx <= studentsSize; idx++) {
			if (counts[idx] == studentsSize - 1) answer++;
		}
	}

	/*
	 * 입력된 학생의 부모 혹은 자식 노드를 업데이트한다.
	 * 
	 */
	private static void dfs(int studentNum, int startNum) {
		for(Student nowStudent = students[studentNum]; nowStudent != null; nowStudent = nowStudent.next) {
			if(visited[nowStudent.to]) continue;
			visited[nowStudent.to] = true;
			counts[startNum]++; // 진출 차수 증가
			counts[nowStudent.to]++; // 진입 차수 증가
			dfs(nowStudent.to, startNum);
		}
	}
}