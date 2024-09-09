package baekjoon.study17;

public class Pro_파괴되지않은건물 {
    public static int solution(int[][] board, int[][] skill) {
        int answer = 0;
        for (int row = 0; row < skill.length; row++) {
            useSkill(board, skill[row]);
        }
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] > 0) answer++;
            }
        }
        return answer;
    }

    static final int HEAL = 2;

    private static void useSkill(int[][] board, int[] skill) {
        int damage;
        if (skill[0] == HEAL) damage = 1;
        else damage = -1;
        for (int row = skill[1]; row <= skill[3]; row++) {
            for (int col = skill[2]; col <= skill[4]; col++) {
                board[row][col] += skill[5] * damage;
            }
        }
    }
}
