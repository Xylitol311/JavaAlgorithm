package baekjoon.study8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj1592_영식이 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 최대 사람 수
        int peopleNum = Integer.parseInt(st.nextToken());
        // 이만큼 받으면 게임 종료
        int maxBallCnt = Integer.parseInt(st.nextToken());
        // 다음 인덱스
        int next = Integer.parseInt(st.nextToken());

        int[] people = new int[peopleNum];
        int personIdx = 0;
        int count = 0;
        while (true) {
            people[personIdx]++;

            // 한 명이 maxBallCnt만큼 받으면 종료
            if (people[personIdx] == maxBallCnt) {
                break;
            }

            // 홀수
            if (people[personIdx] % 2 == 1) {
                personIdx = (personIdx + next) % peopleNum;
            } else { // 짝수
                personIdx = (personIdx - next + peopleNum) % peopleNum;
            }
            count++;
        }

        System.out.println(count);
    }
}