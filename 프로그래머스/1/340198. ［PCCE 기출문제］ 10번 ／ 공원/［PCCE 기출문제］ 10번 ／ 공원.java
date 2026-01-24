import java.util.Arrays;
import java.util.Collections;

class Solution {
    public int solution(int[] mats, String[][] park) {
        Integer[] sortedMats = Arrays.stream(mats).boxed().toArray(Integer[]::new);
        Arrays.sort(sortedMats, Collections.reverseOrder());
        
        int rows = park.length;
        int cols = park[0].length;
        
        for (int size : sortedMats) {
            if (canPlace(park, rows, cols, size)) {
                return size;
            }
        }
        return -1;
    }
    
    // 해당 크기의 돗자리를 놓을 수 있는 위치가 있는지 확인
    private boolean canPlace(String[][] park, int rows, int cols, int size) {
        for (int i = 0; i <= rows - size; i++) {
            for (int j = 0; j <= cols - size; j++) {
                if (isAllEmpty(park, i, j, size)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    // (r, c)에서 시작하는 size × size 영역이 모두 빈 칸인지 확인
    private boolean isAllEmpty(String[][] park, int r, int c, int size) {
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                if (!park[i][j].equals("-1")) {
                    return false;
                }
            }
        }
        return true;
    }
}