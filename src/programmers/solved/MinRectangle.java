package programmers.solved;

public class MinRectangle {
    public static void main(String[] args) {

    }
    public int solution(int[][] sizes) {
        int answer = 0;
        int garo = 0;
        int sero = 0;

        for(int i = 0; i < sizes.length; i++){
            int max = Math.max(sizes[i][0], sizes[i][1]);
            int min = Math.min(sizes[i][0], sizes[i][1]);
            garo = Math.max(garo, max);
            sero = Math.max(sero, min);
        }
        answer = garo * sero;
        return answer;
    }
}