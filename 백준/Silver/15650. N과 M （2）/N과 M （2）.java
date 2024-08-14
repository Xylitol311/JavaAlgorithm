import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	static int[] elementArray;
	static int[] selectArray;

	static int elementCnt; // 원소의 개수
	static int selectCnt; // 선택할 원소의 개수

	// elementIndex: elementArray에서 요소를 가리키는 인덱스
	// selectIndex: 선택해서 담을 위치.
	public static void combination(int elementIndex, int selectIndex) {
		// 선택할 수 있을만큼 다 선택 완료 시
		if (selectIndex == selectCnt) {
			// 출력
			for (int selectNum : selectArray) {
				sb.append(selectNum).append(" ");
			}
			sb.append("\n");
			return;
		}

		// 선택하면서 모든 원소들을 확인 완료 시
		if (elementIndex == elementCnt) {
			return;
		}

		// 현재 원소를 저장하고 다음 위치로 이동
		selectArray[selectIndex] = elementArray[elementIndex];
		combination(elementIndex + 1, selectIndex + 1);

		// 현재 원소를 선택하지 않고 현 위치에서 다음 원소 확인
		selectArray[selectIndex] = -1;
		combination(elementIndex + 1, selectIndex);
	}

	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		elementCnt = Integer.parseInt(st.nextToken());
		selectCnt = Integer.parseInt(st.nextToken());
		elementArray = new int[elementCnt];
		selectArray = new int[selectCnt];

		for (int idx = 0; idx < elementCnt; idx++) {
			elementArray[idx] = idx + 1;
		}

		combination(0, 0);
		System.out.println(sb);

	}
}
