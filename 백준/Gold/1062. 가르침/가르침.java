// 메모리: 00000 KB / 시간: 00 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
<입력 조건>
N <= 50, 0<=K<=26, 8<=전체 길이<=15, 단어의 중복 x

<문제 조건>
1. anta, tica 사이에 글자가 들어가있는 단어.
2. a, c, i, n, t는 무조건 들어감.
3. 즉 K가 5보다 작으면 읽을 수 있는 단어는 없음.
4. 단어의 길이가 8인 경우 K가 5 이상이면 무조건 읽을 수 있음.
5. 앞뒤 anta, tica를 잘라내고 중간만 보면 됨.
6. 중간 글자에 a, c, i, n, t를 제외한 글자의 개수가 (K-5)보다 작거나 같으면 글자를 읽을 수 있음.

<풀이(오답)>
1. n,k와 모든 단어를 입력 받는다.
2. K가 5 미만인 경우 0 출력, K가 26인 경우 N 출력. (N이면 모든 단어 출력 가능)
3. 각 단어를 순회한다
    3-1) 길이가 8인 경우. 무조건 읽을 수 있으므로 answer에 1 추가 후 다음 단어 순회
    3-2) 길이가 8보다 큰 경우 -> set을 이용해 각 단어를 구성하는 알파벳들을 구한다.
        a) 단어를 구성하는 알파벳의 개수가 5개인 경우 -> answer에 1 추가 후 다음 단어 순회
        b) 단어를 구성하는 알파벳의 개수가 K개보다 큰 경우 -> 단어를 읽을 수 없으므로 다음 단어 순회
    3-3) 그 외의 경우 필수 알파벳 5개를 제외한 나머지 알파벳을 비트마스킹을 이용해 map에 저장한다.(이미 존재한다면 거기에 1 추가)
4. map에 입력된 알파벳 조합(비트마스크)를 서로 비교하여 상위 조합에 속하는 하위 조합의 개수를 가산하고 그 최댓값을 저장한다.
5. 구한 최댓값을 answer에 가산 후 출력한다.

틀린 이유: 각 단어별로의 최댓값만 고려하여 K개의 글자로 전체 단어 중 몇개를 읽을 수 있는지 알 수가 없음. 예제 3번처럼 딱 한 글자씩 서로 다른 글자가 들어가는 경우 K-5인 3개의 단어를 읽을 수 있어야 하는데 1개의 단어만 읽을 수 있다고 파악함.
 */

/*
dfs 방식으로 전체 알파벳 조합을 먼저 구하고 각각 몇개의 단어를 읽을 수 있는지 구하는 방식으로 수정.



 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, K;
    static int[] words;    // 각 단어의 비트마스크를 저장
    static int maxCount = 0;
    static final int BASIC_BITS = (1 << ('a' - 'a')) | (1 << ('c' - 'a')) |
            (1 << ('i' - 'a')) | (1 << ('n' - 'a')) |
            (1 << ('t' - 'a'));  // a,c,i,n,t의 비트마스크

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        words = new int[N];
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            // 각 단어를 비트마스크로 변환하여 저장
            int bitmap = 0;
            for (int j = 0; j < word.length(); j++) {
                bitmap |= (1 << (word.charAt(j) - 'a'));
            }
            words[i] = bitmap;
        }
    }

    public static void main(String[] args) throws IOException {
        input();

        if (K < 5) {
            System.out.println(0);
            return;
        }
        if (K == 26) {
            System.out.println(N);
            return;
        }

        dfs(0, 0, BASIC_BITS);
        System.out.println(maxCount);
    }

    // index: 현재 검사할 알파벳, count: 선택한 알파벳 수, learned: 배운 알파벳 비트마스크
    private static void dfs(int index, int count, int learned) {
        // K개의 알파벳을 모두 배웠을 때
        if (count == K - 5) {
            int readable = countReadable(learned);
            maxCount = Math.max(maxCount, readable);
            return;
        }

        // 남은 알파벳이 필요한 개수보다 적으면 종료
        if (26 - index < K - 5 - count) return;

        // z까지 다 봤으면 종료
        if (index == 26) return;

        // 현재 알파벳이 기본 글자(a,c,i,n,t)인 경우 건너뛰기
        if ((BASIC_BITS & (1 << index)) > 0) {
            dfs(index + 1, count, learned);
            return;
        }

        // 현재 알파벳 배우기
        dfs(index + 1, count + 1, learned | (1 << index));
        // 현재 알파벳 건너뛰기
        dfs(index + 1, count, learned);
    }

    // 현재 배운 알파벳들로 읽을 수 있는 단어 개수 반환
    private static int countReadable(int learned) {
        int count = 0;
        for (int word : words) {
            // 단어의 모든 알파벳이 배운 알파벳에 포함되어 있으면 카운트
            if ((word & learned) == word) {
                count++;
            }
        }
        return count;
    }
}