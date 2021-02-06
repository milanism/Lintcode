public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        
        int len = nums.length;
        int[] leftPdt = new int[len];
        int[] rightPdt = new int[len];
        leftPdt[0] = 1;
        rightPdt[len - 1] = 1;
        
        for (int i = 1; i < len; i++) {
            leftPdt[i] = leftPdt[i - 1] * nums[i - 1];
            rightPdt[len - i - 1] = rightPdt[len - i] * nums[len - i];
        }
        
        int[] res = new int[len];
        
        for(int j = 0; j < len; j++) {
            res[j] = leftPdt[j] * rightPdt[j];
        }
        
        return res;
}