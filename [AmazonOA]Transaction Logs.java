public class Solution {
    public String[] processLogFile(String[] logs, int threshold) {
        if (logs == null || logs.length == 0) {
            return new String[0];
        }

        int len = logs.length;
        int cnt = 0;
        Map<String, Integer> hm = new HashMap<>();
        Queue<String> pq = new PriorityQueue<>();
        for (int i = 0; i < len; i++) {
            String[] temp = logs[i].split(" ");
            String sender = temp[0];
            String recipient = temp[1];

            hm.put(sender, hm.getOrDefault(sender, 0) + 1);
            if (!sender.equals(recipient)) {
                hm.put(recipient, hm.getOrDefault(recipient, 0) + 1);
            }
        }

        for (String usr : hm.keySet()) {
            if (hm.get(usr) >= threshold) {
                pq.offer(usr);
                cnt++;
            }
        }

        String[] res = new String[cnt];
        for (int i = 0; i < cnt; i++) {
            res[i] = pq.poll();
        }

        return res;
    }
}