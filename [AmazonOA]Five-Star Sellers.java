public int fiveStarReviews(int[][] productRatings, int ratingsThreshold) {
        if (productRatings == null || productRatings.length == 0) {
            return 1;
        }
        
        int res = 0;
        int len = productRatings.length;
        double currRate = 0.0;
        Queue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                double resA = a[0] == a[1]? 0: (double)(a[1] - a[0]) / (a[1] * (a[1] + 1));
                double resB = b[0] == b[1]? 0: (double)(b[1] - b[0]) / (b[1] * (b[1] + 1));
                if (resA <= resB) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });

        for (int i = 0; i < len; i++) {
            currRate += (double)productRatings[i][0] / productRatings[i][1] / len;
            pq.add(productRatings[i]);
        }

        if (currRate * 100 >= ratingsThreshold) {
            return 0;
        }

        while (!pq.isEmpty()){
            int[] tempNode = pq.poll();
            if (tempNode[0] == tempNode[1]) {
                break;
            }

            currRate += (double)(tempNode[1] - tempNode[0]) / (tempNode[1] * (tempNode[1] + 1)) / len;
            res++;
            if (currRate * 100 + Math.pow(10, -8)>= ratingsThreshold) {
                return res;
            }
            tempNode[0] = tempNode[0] + 1;
            tempNode[1] = tempNode[1] + 1;
            pq.offer(tempNode);
        }

        return -1;
    }