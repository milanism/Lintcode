public class Solution {
    private int minPlan = Integer.MAX_VALUE;
    /**
     * @param arr: the distance between any two cities
     * @return: the minimum distance Alice needs to walk to complete the travel plan
     */
    public int travelPlan(int[][] arr) {
        // Write your code here.
        if (arr == null || arr.length == 0 || arr[0].length == 0) {
            return 0;
        }
        
        int n = arr.length;
        boolean[] visited = new boolean[n];
        visited[0] = true;
        int[] path = new int[n];
        
        DFS(arr, visited, path, 0, n - 1);
        
        return minPlan;
    }
    
    private void DFS(int[][] arr, boolean[] visited, int[] path, int currPlan, int remains) {
        int n = arr.length;
        int lastNode = path[n - remains - 1];
        
        if (remains == 0) {
            int finalPlan = currPlan + arr[lastNode][0];
            if (finalPlan < minPlan) {
                minPlan = finalPlan;
            }
            return;
        }

        for (int i = 1; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            
            visited[i] = true;
            path[n - remains] = i;
            currPlan += arr[lastNode][i];
            DFS(arr, visited, path, currPlan, remains - 1);
            
            visited[i] = false;
            path[n - remains] = 0;
            currPlan -= arr[lastNode][i];
        }
        
        return;
    }
}
