// 메모리: 00000 KB / 시간: 00 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
트라이를 이용해 문자열을 저장하고 자식노드가 2개 이상이거나 어느 단어의 마지막 글자인데 자식노드가 또 존재하는 경우
숫자를 카운트 한다.
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static List<String> words;
    static TrieNode trie;
    private static void input(String line) throws IOException {
        // input
        int n = Integer.parseInt(line);
        words = new ArrayList<>();
        trie = new TrieNode();
        for (int i = 0; i < n; i++) {
            String inputWord = br.readLine();
            words.add(inputWord);
            trie.insert(inputWord);
        }
    }
    public static void main(String[] args) throws IOException {
        String line = br.readLine();

        while (line != null && !line.equals("")) {
            input(line);

            double result = 0;
            for (String word : words) {
                result += trie.autoModule(word);
            }
            double avg = result / words.size();
            sb.append(String.format("%.2f", avg)).append("\n");

            line = br.readLine();
        }
        System.out.println(sb);
    }

    static class TrieNode {
        Map<Character, TrieNode> childNode = new HashMap<>();
        boolean end;
        int childCount = 0;

        public TrieNode() {

        }

        public void insert(String word) {
            TrieNode trieNode = this;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);

                // 자식 노드가 존재하지 않을 경우 새로 생성
                if (!trieNode.childNode.containsKey(c)) {
                    trieNode.childNode.put(c, new TrieNode());
                    trieNode.childCount++; // 자식 노드 수 증가
                }
                trieNode = trieNode.childNode.get(c);

                // i가 단어의 마지막이라면 end를 true로 변경
                if (i == word.length() - 1) {
                    trieNode.end = true;
                }
            }
        }

        public int autoModule(String word) {
            TrieNode trieNode = this;
            int cnt = 0;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);

                // 첫 글자는 항상 입력. 현재 문자의 자식 노드가 둘 이상이거나 중간 문자인데 end가 true인 경우
                if (i == 0 || trieNode.end || trieNode.childCount > 1) {
                    cnt++;
                }

                trieNode = trieNode.childNode.get(c);
            }
            return cnt;
        }
    }
}