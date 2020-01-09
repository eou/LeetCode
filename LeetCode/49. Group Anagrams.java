// 49. Group Anagrams
class Solution {
    // 时间复杂度为 O(NK)，where NN is the length of strs, and KK is the maximum length of a string in strs
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) {
            return new ArrayList();
        }

        Map<String, List> map = new HashMap<String, List>();
        int[] count = new int[26];
        for (String str : strs) {
            Arrays.fill(count, 0);
            for (char c : str.toCharArray()) {
                count[c - 'a']++;
            }

            StringBuilder stringBuilder = new StringBuilder("");
            for (int i = 0; i < 26; i++) {
                stringBuilder.append('#');
                stringBuilder.append(count[i]);
            }
            String key = stringBuilder.toString();
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList());
            }
            map.get(key).add(str);
        }

        return new ArrayList(map.values());
    }
}

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] c = str.toCharArray();
            Arrays.sort(c);
            String str1 = String.valueOf(c);
            List<String> list = map.containsKey(str1) ? map.get(str1) : new ArrayList<>();
            list.add(str);
            map.put(str1, list);
        }

        List<List<String>> res = new ArrayList<>();
        for (List<String> list : map.values()) {
            res.add(list);
        }
        return res;
    }
}