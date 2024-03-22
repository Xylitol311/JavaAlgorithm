package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.Function;

public class Boj11581 {
    private static final String CYCLE = "CYCLE";
    private static final String NOT_CYCLE = "NO CYCLE";
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Function<String,Integer> stoi = Integer::parseInt;
        int n = stoi.apply(st.nextToken());
        List<Integer>[] map = new List[n+1];
        for(int i = 1 ; i < n ; i++){
            map[i] = new LinkedList<>();
            int num = stoi.apply(br.readLine());
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < num ; j++){
                int next = stoi.apply(st.nextToken());
                map[i].add(next);
            }
        }
        boolean[] visited = new boolean[n+1];
        if(!detectCycle(map,1,visited,n)){
            System.out.println(CYCLE);
        }else{
            System.out.println(NOT_CYCLE);
        }
    }
    private static boolean detectCycle(List<Integer>[] map,int now,boolean[] visited, int n){
        if(now == n){
            return true;
        }
        visited[now] = true;
        boolean result = true;
        for(int i = 0 ; i < map[now].size() ; i++){
            int next = map[now].get(i);
            if(visited[next]){
                return false;
            }
            if(!detectCycle(map,next,visited,n)){
                result = false;
            }
        }
        visited[now] = false;
        return result;
    }
}