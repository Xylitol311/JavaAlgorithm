
public class Combination {
	// 알고리즘 할 때 어지간한 건 다 static으로 빼기
	static int[] elementArray = { 1, 2, 3 };
	static int[] selectArray = new int[2];

	static final int ELEMENT_COUNT = 3; // 원소의 개수
	static final int SELECT_COUNT = 2; // 선택할 원소의 개수

	static boolean[] elementUsedArray = { false, false, false };

	// selectIndex: 선택해서 담을 위치.
	// 재귀함수로 구현할 예정.
	public static void combination(int elementIndex, int selectIndex) {
		// 기저 조건
		// 선택할 수 있을만큼 다 선택했으면
		if (selectIndex == SELECT_COUNT) {
			return;
		}

		// 선택하면서 모든 원소들을 확인했으면
		if (elementIndex == ELEMENT_COUNT) {
			return;
		}

		// 진행로직
		// 전처리
		// 재귀
		// 후처리

		// 원소를 선택해서 담아줌
		selectArray[selectIndex] = elementArray[elementIndex];
		combination(elementIndex + 1, selectIndex + 1);

		// 원소를 선택하지 않고 넘어감
		selectArray[selectIndex] = -1;
		combination(elementIndex + 1, selectIndex);
	}

	public static void main(String[] args) {

	}
}
