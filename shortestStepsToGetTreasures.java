public class Solution {
    public class Node {
        int x;
        int y;
        int tag;
        public Node(int x, int y, int tag) {
            this.x = x;
            this.y = y;
            this.tag = tag;
        }
    }
    /**
     * @param init_state: the initial state of chessboard
     * @param final_state: the final state of chessboard
     * @return: return an integer, denote the number of minimum moving
     */
    public int mMaze(String[] maze) {
        if (maze == null || maze.length == 0) {
            return -1;
        }

        int n = maze.length;
        int m = maze[0].length();
        int treasureCnt = 0;
        int startX = -1, startY = -1;
        int desX = -1, desY = -1;
        int currTreasure = 0, minSteps = 0;
        int bitMask = 0;
        HashSet<String> visited = new HashSet<>();
        Queue<Node> que = new LinkedList<>();
    
        for (int i = 0; i < n; i++) {
            String temp = maze[i];
            for (int j = 0; j < temp.length(); j++) {
                if (temp.charAt(j) >= '0' && temp.charAt(j) <= '9') {
                    treasureCnt++;
                }

                if (temp.charAt(j) == 'S') {
                    startX = i;
                    startY = j;
                }

                if (temp.charAt(j) == 'T') {
                    desX = i;
                    desY = j;
                }
            }
        }

        Node start = new Node(startX, startY, 0);
        que.offer(start);
        visited.add("x="+startX+"y="+startY+"t="+0);
        
        int[] dx = new int[]{-1, 0, 0, 1};
        int[] dy = new int[]{0, -1, 1, 0};

        while (!que.isEmpty()) {
            int size = que.size();

            for (int i = 0; i < size; i++) {
                Node temp = que.poll();

                if (temp.x == desX && temp.y == desY && temp.tag == (1 << treasureCnt) - 1) {
                    return minSteps;
                }

                for (int j = 0; j < 4; j++) {
                    int newX = temp.x + dx[j];
                    int newY = temp.y + dy[j];
                    int newT = temp.tag;

                    if (!isValid(maze, newX, newY)) {
                        continue;
                    }

                    if (maze[newX].charAt(newY) >= '0' && maze[newX].charAt(newY) <= '9') {
                        int index = maze[newX].charAt(newY) - '0';
                        if ((newT & (1 << index)) == 0) {
                            //not visited, need to update the bitmask
                            newT = newT | (1 << index);  
                        }
                    }

                    if (!visited.add("x="+newX+"y="+newY+"t="+newT)) {
                        continue;
                    }

                    que.offer(new Node(newX, newY, newT));
                }
            }
            minSteps++;
            //System.out.println(minSteps);
        }
        
        return -1;
    }

    private boolean isValid(String[] maze, int newX, int newY) {
        int n = maze.length;
        int m = maze[0].length();

        if (newX < 0 || newX >= n) {
            return false;
        }

        if (newY < 0 || newY >= m) {
            return false;
        }

        if (maze[newX].charAt(newY) == '*') {
            return false;
        }

        return true;
    }
}
