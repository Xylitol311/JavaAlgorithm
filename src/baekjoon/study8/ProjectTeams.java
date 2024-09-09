package baekjoon.study8;

import java.util.Arrays;
import java.util.Scanner;

public class ProjectTeams {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int[] students = new int[2 * N];

        for (int i = 0; i < students.length; i++) {
            students[i] = sc.nextInt();
        }

        Arrays.sort(students);
        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            int tmp = students[i] + students[students.length - 1 - i];
            answer = Math.min(answer, tmp);
        }
        System.out.println(answer);
    }
}
