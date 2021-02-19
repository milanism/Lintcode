public class Solution {
    class type implements Comparable<type> {
        int time;
        int flag;
        public type(int time, int flag) {
            this.time = time;
            this.flag = flag;
        }
        
        public int compareTo(type o) {
            return this.time == o.time? this.flag - o.flag: this.time - o.time;
        }
    }
    /**
     * @param airplanes: An interval array
     * @return: Count of airplanes are in the sky.
     */
    public int countOfAirplanes(List<Interval> airplanes) {
        // write your code here
        if (airplanes == null || airplanes.size() == 0) {
            return 0;
        }
        int res = 0, maxRes = 0;
        
        Queue<type> pq = new PriorityQueue<type>();
        for (Interval temp : airplanes) {
            System.out.println("start=" + temp.start + " end=" + temp.end);
            type tempNode1 = new type(temp.start, 1);
            type tempNode2 = new type(temp.end, -1);
            pq.offer(tempNode1);
            pq.offer(tempNode2);
        }
        
        //System.out.println("size=" + pq.size());
        while (!pq.isEmpty()) {
            type tempNode = pq.poll();
            //System.out.println("time=" + tempNode.time + " flag=" + tempNode.flag);
            if (tempNode.flag == 1) {
                res++;
                maxRes = Math.max(maxRes, res);
            } else if (tempNode.flag == -1) {
                res--;
            }
        }
        
        return maxRes;
    }
}