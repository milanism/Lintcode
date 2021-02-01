public class Solution {
    /**
     * @param length: the length of board
     * @param connections: the connections of the positions
     * @return: the minimum steps to reach the end
     */
    public int modernLudo(int length, int[][] connections) {
        // Write your code here
        Map<Integer, Set<Integer>> graph = buildGraph(length, connections);
        Map<Integer, Integer> dis = new HashMap<>();
        List<Integer> que = new ArrayList<>();
        dis.put(1, 0);
        que.add(1);
        
        while (!que.isEmpty()) {
            List<Integer> newQue = new ArrayList<>();
            
            //deal with the connected nodes, add to this level
            for (int i = 0; i < que.size(); i++) {
                int tempNode = que.get(i);
                
                if (!graph.containsKey(tempNode)) {
                    continue;
                }
                for (int nextNode : graph.get(tempNode)) {
                    if (dis.containsKey(nextNode)) {
                        continue;
                    }
                    
                    dis.put(nextNode, dis.get(tempNode));
                    que.add(nextNode);
                }
            }
            
            //deal with the diced nodes, add to next level
            for (int i = 0; i < que.size(); i++) {
                int tempNode = que.get(i);
                int limit = Math.min(tempNode + 7, length + 1);
                for (int j = tempNode + 1; j < limit; j++) {
                    if (dis.containsKey(j)) {
                        continue;
                    }
                    
                    dis.put(j, dis.get(tempNode) + 1);
                    newQue.add(j);
                }
            }
            
            que = newQue;
        }
        
        return dis.get(length);
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