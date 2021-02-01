public class Solution {
    /**
     * @param length: the length of board
     * @param connections: the connections of the positions
     * @return: the minimum steps to reach the end
     */
    public int modernLudo(int length, int[][] connections) {
       if (length == 0) {
           return -1;
       }
       
       Map<Integer, Set<Integer>> graph = buildGraph(length, connections);
       
       int[] dp = new int[length + 1];
       for (int i = 2; i < length + 1; i++) {
           dp[i] = Integer.MAX_VALUE;
       }
       
       for (int i = 1; i < length + 1; i++) {
           int limit = Math.min(i + 7, length + 1);
           //deal with the connected node
           for (int nextNode : graph.get(i)) {
               dp[nextNode] = Math.min(dp[nextNode], dp[i]);
           }
           
           //deal with the diced node
           for (int nextNode = i + 1; nextNode < limit; nextNode++) {
               dp[nextNode] = Math.min(dp[nextNode], dp[i] + 1);
           }
       }
       
       return dp[length];
    }
    
    private Map<Integer, Set<Integer>> buildGraph(int length, int[][] connections) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        
        int len = connections.length;
        
        for (int i = 1; i <= length; i++) {
            graph.put(i, new HashSet<Integer>());
        }
        
        for (int i = 0; i < len; i++) {
            graph.get(connections[i][0]).add(connections[i][1]);
        }
        
        return graph;
    } 
}