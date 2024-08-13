import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static final int QUEUE = 0;
    public static void main(String[] args) throws Exception {
    	// 설명
    	// 자료구조가 스택인 경우 들어간 초기값이 나올 일이 없다.
    	// 결국 자료구조가 큐인 경우에만 데이터의 입출력이 일어나는데 앞쪽 큐에서 뒤쪽 큐까지 순서대로 자료가 이동.
    	// 사실상 하나의 큐를 쓰는 것과 같다.
    	// 덱을 이용해서 초기값을 뒤에 들어온 값이 앞으로 오도록 저장하고,
    	// 이후 M만큼 값을 뒤로 넣고 앞에서 기존 저장된 값을 추출하는 과정을 반복한다.
    	// 마지막에 추출된 데이터를 출력한다.
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 자료구조 및 데이터의 개수
        int N = Integer.parseInt(br.readLine());
        
        // 반복문을 하나로 처리하고 싶어 StringTokenizer 2개를 이용
        // 자료구조를 입력받을 st
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 초기값을 입력받을 st2.
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        
        // 데이터를 처리할 덱
        Deque<Integer> queue = new LinkedList<>();
        
        // 초기값 입력
        for (int i = 0; i < N; i++) {
        	int structure = Integer.parseInt(st.nextToken()); // 자료구조
        	int data = Integer.parseInt(st2.nextToken()); // 데이터
        	if (structure == QUEUE) {// 자료구조가 큐인 경우
        		queue.addFirst(data); // 값을 앞에서부터 넣어 가장 늦게 들어간 값이 앞으로 위치하도록 구현.
        	}
        }
        
        // 입력받을 값의 개수
        int M = Integer.parseInt(br.readLine());
        // 입력받을 값
        st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < M; i++) {
        	int tmp = Integer.parseInt(st.nextToken());
        	// 새로 들어온 값을 뒤에 넣고
        	queue.offerLast(tmp);
        	// 기존에 있던 앞에 값을 추출
        	sb.append(queue.pollFirst()).append(" ");
        }
        
        System.out.println(sb);

    }
}