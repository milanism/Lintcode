public class Solution {
    public int finalInstances(int instances, int[] averageUtil) {
        int len = averageUtil.length;
        int currIndex = 0;
        
        while (currIndex < len) {
            int currAvg = averageUtil[currIndex];
            if (currAvg >= 25 && currAvg <= 60) {
                //take no action 
                currIndex++;
                continue;
            }
            
            if (currAvg < 25) {
              //half
                if (instances == 1) {
                    currIndex++;
                    continue;
                }
                instances = instances % 2 == 0? instances / 2: instances / 2 + 1;
            }
            
            if (currAvg > 60) {
                if (instances > Math.pow(10, 8)) {
                    currIndex++;
                    continue;
                }
               
                instances *= 2;
            }
            
            currIndex += 11;
        }
        
        return instances; 
    }
}