public class Solution {
    public int modernLudo(int length, int[][] connections) {
        // Write your code here
        Map<Integer, Set<Integer>> graph = buildGraph(length, connections);
        Map<Integer, Integer> dis = new HashMap<>();
        Queue<Integer> que = new LinkedList<>();
        dis.put(1, 0);
        que.offer(1);
        
        while (!que.isEmpty()) {
            int currNode = que.poll();
            int limit = Math.max(length + 1, currNode + 7);
            //find the nodes can diced to
            for (int i = currNode + 1; i < limit; i++) {
                if (dis.containsKey(i)) {
                    continue;
                }
                
                List<Integer> connNodes = getConnNodes(graph, dis, i);
                for (int j : connNodes) {
                    que.offer(j);
                    dis.put(j, dis.get(currNode) + 1);
                }
            }
        }
        
        return dis.get(length);
    }
    
    private List<Integer> getConnNodes(Map<Integer, Set<Integer>> graph, 
                                       Map<Integer, Integer> dis,
                                       int node) {
        List<Integer> res = new ArrayList<>();
        Queue<Integer> que = new LinkedList<>();
        que.offer(node);
        
        while (!que.isEmpty()) {
            int tempNode = que.poll();
            if (dis.containsKey(tempNode)) {
                continue;
            }
            res.add(tempNode);
            
            if (!graph.containsKey(tempNode)) {
                continue;
            }
            
            for (int nextNode : graph.get(tempNode)) {
                if (!dis.containsKey(nextNode)) {
                    que.offer(nextNode);
                    res.add(nextNode);
                }
                
            }
        }
        
        return res;
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