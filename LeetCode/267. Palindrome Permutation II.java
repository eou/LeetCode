// 267. Palindrome Permutation II
class Solution {
    // 时间复杂度为 O((n/2)!)
    public List<String> generatePalindromes(String s) {
        List<String> results = new ArrayList<>();
        // 也可以用 count[256] 来存字符出现频率
        // int[] count = new int[256];
        Map<Character, Integer> map = new HashMap<>();
        int odd = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
            odd += map.get(c) % 2 != 0 ? 1 : -1;
        }

        // cannot form palindrome strings
        if (odd > 1) {
            return results;
        }

        StringBuilder str = new StringBuilder();
        for (Character c : map.keySet()) {
            if (map.get(c) % 2 == 1) {
                str.append(c);
            }
        }
        getAllPalindrome(results, map, str, s.length());
        return results;
    }

    private void getAllPalindrome(List<String> results, Map<Character, Integer> map, StringBuilder str, int len) {
        if (str.length() == len) {
            results.add(str.toString());
            return;
        }

        for (Character c : map.keySet()) {
            if (map.get(c) > 1) {
                map.put(c, map.get(c) - 2);
                str.insert(0, c);
                str.append(c);
                getAllPalindrome(results, map, str, len);
                map.put(c, map.get(c) + 2);
                str.deleteCharAt(0);
                str.deleteCharAt(str.length() - 1);
            }
        }
    }
}

// interview version 2019.10
class Solution {
    public List<String> generatePalindromes(String s) { 
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return res;
        }

        Map<Character, Integer> map = new HashMap<>();
        for (Character c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int oddChar = 0;
        Character oddCharacter = null;
        for (Character c : map.keySet()) {
            if (map.get(c) % 2 == 1) {
                oddChar++;
                oddCharacter = c;
                if (oddChar > 1) {
                    return res;
                }
            }
        }

        if (oddChar == 1) {
            String cur = "" + oddCharacter;
            dfs(s, map, res, cur);
        } else {
            dfs(s, map, res, "");
        }
        
        return res;
    } 

    public void dfs(String s, Map<Character, Integer> map, List<String> res, String cur) {
        if (s.length() == cur.length()) {
            res.add(cur);
            return;
        }

        for (Character c : map.keySet()) {
            int frequency = map.get(c);
            if (frequency > 1) {
                String next = new String(cur);
                next = next + c;
                next = c + next;
                map.put(c, map.get(c) - 2);
                dfs(s, map, res, next);
                // backtracking
                map.put(c, frequency);
            }
        }
    }
}


class Solution {
    public List<String> generatePalindromes(String s) {
        List<String> results = new ArrayList<>();
        List<Character> lists = new ArrayList<>();
        Map<Character, Integer> map = new HashMap<>();
        String mid = "";
        int odd = 0;
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
            odd += map.get(c) % 2 != 0 ? 1 : -1;
        }
        
        // cannot form palindrome strings
        if(odd > 1) {
            return results;
        }
        
        for(Character c : map.keySet()) {
            if(map.get(c) % 2 != 0) {
                mid += c;
            }
            for(int i = 0; i < map.get(c) / 2; i++) {
                lists.add(c);
            }
        }
        
        getAllPalindrome(lists, mid, new boolean[lists.size()], new StringBuilder(), results);
        return results;
    }
    
    private void getAllPalindrome(List<Character> lists, String mid, boolean[] used, StringBuilder str, List<String> results) {
        // already used all the characters in the lists
        if(str.length() == lists.size()) {
            results.add(str.toString() + mid + str.reverse().toString());
            str.reverse();
            return;
        }
        
        for(int i = 0; i < lists.size(); i++) {
            if(i > 0 && lists.get(i) == lists.get(i - 1) && !used[i - 1]) {
                continue;
            }
            
            if(!used[i]) {
                used[i] = true;
                str.append(lists.get(i));
                getAllPalindrome(lists, mid, used, str, results);
                used[i] = false;
                str.deleteCharAt(str.length() - 1);
            }
        }
    }
}
