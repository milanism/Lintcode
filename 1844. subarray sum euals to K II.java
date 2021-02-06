public int subarraySumEqualsKII(int[] nums, int k) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int res = Integer.MAX_VALUE;
        int[] pfSum = new int[len + 1];
        Map<Integer, Integer> hm = new HashMap<>();
        pfSum[0] = 0;
        hm.put(0, 0);
        
        for (int i = 1; i <= len; i++) {
            pfSum[i] = pfSum[i - 1] + nums[i - 1];
        }
        
        for (int j = 1; j <= len; j++) {
            if (hm.containsKey(pfSum[j] - k)) {
                int pos = hm.get(pfSum[j] - k);
                res = Math.min(res, j - pos);
            }
            
            hm.put(pfSum[j], j);
        }
        
        return res;
    }