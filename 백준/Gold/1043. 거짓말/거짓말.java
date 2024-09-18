import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
1. 진실을 아는 사람과 함께 파티에 참석하는 경우가 있는 사람에게는 항상 진실만을 말해야 한다.
2. 파티들을 조회하여 같은 파티에 참석한 적이 있는 사람들을 유니온 파인드를 이용하여 하나의 그룹으로 묶어준다.
3. 해당 그룹에 진실을 알고 있는 사람이 있다면 그 그룹의 부모 노드가 진실을 알고 있다고 체크해준다.
4. 모든 파티에 대해 진실 체크가 완료되면 다시 파티를 탐색하면서 부모노드에 진실을 알고 있는 사람이 없는 파티만 카운트 한다.
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int peopleNum, numOfParty;
    static int[] parents;

    static List<Integer> trueList; // 진실을 아는 사람들을 저장할 리스트
    static List<List<Integer>> partyList; // 파티별 멤버 저장 리스트
    static boolean[] knowTruth;

    static boolean input() throws IOException {
        st = new StringTokenizer(br.readLine());
        peopleNum = Integer.parseInt(st.nextToken()); // 사람 수
        numOfParty = Integer.parseInt(st.nextToken()); // 파티의 수

        // 사람들의 부모 노드 저장 배열
        parents = new int[peopleNum + 1];
        Arrays.fill(parents, -1);

        // 진실을 아는 사람 저장
        st = new StringTokenizer(br.readLine());
        int trueKnowPeople = Integer.parseInt(st.nextToken());
        trueList = new ArrayList<>();

        if (trueKnowPeople == 0) { // 만약 한 명도 진실을 모르면 파티 개수를 출력하고 종료
            System.out.println(numOfParty);
            return false;
        } else {
            for (int i = 0; i < trueKnowPeople; i++)
                trueList.add(Integer.parseInt(st.nextToken()));
        }

        // 진실 체크
        knowTruth = new boolean[peopleNum + 1]; // 진실을 아는 사람을 체크할 배열
        for (int truePerson : trueList) { // 진실을 아는 사람들 진실 체크
            knowTruth[truePerson] = true;
        }


        // 파티 멤버 저장
        partyList = new ArrayList();
        for (int i = 0; i < numOfParty; i++) {
            st = new StringTokenizer(br.readLine());
            partyList.add(new ArrayList<>());
            int partyMemLen = Integer.parseInt(st.nextToken());
            for (int j = 0; j < partyMemLen; j++) {
                partyList.get(i).add(Integer.parseInt(st.nextToken())); //파티별로 참석하는 사람 저장
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        if (!input()) return;
        solution();
    }

    static void solution() {
        //같이 파티에 있었던 사람 union
        for (int i = 0; i < numOfParty; i++) {
            for (int j = 0; j < partyList.get(i).size() - 1; j++) {
                union(partyList.get(i).get(j), partyList.get(i).get(j + 1));
            }
        }

        //진실을 아는 사람의 parent인 경우 진실 처리
        for (int i = 1; i <= peopleNum; i++) {
            if (knowTruth[i]) {
                int parent = findSet(i);
                knowTruth[parent] = true;
            }
        }

        int answer = 0;
        //파티 참석자의 parent가 진실을 알고있다면 패스
        for (int i = 0; i < numOfParty; i++) {
            boolean possibleGuraping = true; // 거짓말이 가능한지 확인할 변수

            for (int j = 0; j < partyList.get(i).size(); j++) {
                int p = partyList.get(i).get(j); // 현재 파티에 참석한 인원
                int parent = findSet(p); // p의 부모 노드 탐색
                if (knowTruth[parent]) { // 부모 노드가 진실을 알고 있다면 현재 파티는 종료
                    possibleGuraping = false;
                    break;
                }
            }
            if (possibleGuraping)
                answer++;
        }

        // 모든 파티 탐색 후 정답 출력
        System.out.println(answer);
    }

    static int findSet(int a) {
        if (parents[a] < 0)
            return a;
        return parents[a] = findSet(parents[a]); // 집합의 대표자를 자신의 부모로 변경(패스 압축)
    }

    static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        if (aRoot == bRoot)
            return false;

        parents[aRoot] += parents[bRoot];
        parents[bRoot] = aRoot;
        return true;
    }
}