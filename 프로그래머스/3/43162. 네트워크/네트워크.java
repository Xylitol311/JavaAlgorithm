class Solution {
    static boolean[] visited;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            if(!visited[i]) {
                dfs(computers, i, n);
                answer++;
            }
        }
        
        return answer;
    }
    
    private void dfs(int[][] computers, int node, int n) {
        visited[node] = true;
        
        for (int next = 0; next < n; next++) {
            if (!visited[next] && computers[node][next] != 0) {
                dfs(computers, next, n);
            }
        }
    }
}