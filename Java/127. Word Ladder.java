// 127. Word Ladder
// 求最短转换次数这种最短路径问题一定是BFS
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null) {
            return 0;
        }
        if (beginWord.equals(endWord)) {
            return 1;
        }
        
        // 注意如果不用Set转换一下 List，后面判断某一个单词是否在 wordList 中会超时
        Set<String> dict = new HashSet<>(wordList);
        Set<String> set = new HashSet<>();
        Deque<String> queue = new ArrayDeque<>();
        queue.offer(beginWord);
        set.add(beginWord);

        // 注意初始化len = 1，因为题目定义 beginWord 和 endWord 一样的时候转换次数就是1
        int len = 1;
        while (!queue.isEmpty()) {
            len++;
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                String cur = queue.poll();
                for (String next : nextWord(cur, dict)) {
                    if (next.equals(endWord)) {
                        return len;
                    }
                    if (!set.contains(next)) {
                        queue.offer(next);
                        set.add(next);
                    }
                }
            }
        }
        return 0;
    }
    
    private ArrayList<String> nextWord(String word, Set<String> dict) {
        ArrayList<String> nextWords = new ArrayList<String>();
        for (char c = 'a'; c <= 'z'; c++) {
            for (int i = 0; i < word.length(); i++) {
                if (c == word.charAt(i)) {
                    continue;
                }
                String nextWord = replace(word, i, c);
                // 此处如果是 wordList.contains(nextWord) 会TLE
                if (dict.contains(nextWord)) {
                    nextWords.add(nextWord);
                }
            }
        }
        return nextWords;
    }
    
    private String replace(String s, int index, char c) {
        // toCharArray()
        char[] word = s.toCharArray();
        word[index] = c;
        return new String(word);
    }
}

class Solution {
    // 另一种匹配单词的方式，从wordList中找与当前单词只差一个字母的单词，找到后删除，但是比较慢
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null) {
            return 0;
        }
        if (beginWord.equals(endWord)) {
            return 1;
        }

        Set<String> dict = new HashSet<>(wordList);
        Set<String> set = new HashSet<>();
        Deque<String> queue = new ArrayDeque<>();
        queue.offer(beginWord);
        set.add(beginWord);
        int len = 1;
        while (!queue.isEmpty()) {
            len++;
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                String cur = queue.poll();
                for (String next : findNextAndRemove(cur, dict)) {
                    if (next.equals(endWord)) {
                        return len;
                    }
                    if (!set.contains(next)) {
                        queue.offer(next);
                        set.add(next);
                    }
                }
            }
        }
        return 0;
    }

    // 找到所有和a只差一个字母的单词 并从dict里面删除
    public List<String> findNextAndRemove(String s, Set<String> dict) {
        List<String> next = new ArrayList<>();

        for (String d : dict) {
            int diff = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == d.charAt(i)) {
                    continue;
                }
                diff++;
            }
            if (diff == 1) {
                next.add(d);
            }
        }
        
        dict.removeAll(next);
        return next;
    }
}

class Solution {
    // 最快的版本，双向队列BFS
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord.equals(endWord)) {
            return 1;
        }

        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) {
            return 0;
        }

        Queue<String> queA = new LinkedList<>();
        Set<String> setA = new HashSet<>();
        queA.offer(beginWord);
        setA.add(beginWord);

        Queue<String> queB = new LinkedList<>();
        Set<String> setB = new HashSet<>();
        queB.offer(endWord);
        setB.add(endWord);

        Queue<String> que = null;
        Set<String> setCur = null, setOp = null;

        int len = 1;
        while (!queA.isEmpty() && !queB.isEmpty()) {
            if (queA.size() <= queB.size()) {
                que = queA;
                setCur = setA;
                setOp = setB;
            } else {
                que = queB;
                setCur = setB;
                setOp = setA;
            }

            len++;
            int size = que.size();
            for (int i = 0; i < size; ++i) {
                String cur = que.poll();
                for (String next : nextWord(cur, dict)) {
                    if (setOp.contains(next)) {
                        return len;
                    }
                    if (!setCur.contains(next)) {
                        que.offer(next);
                        setCur.add(next);
                    }
                }
            }
        }

        return 0;
    }

    private ArrayList<String> nextWord(String cur, Set<String> dict) {
        ArrayList<String> res = new ArrayList<>();
        StringBuilder strBuilder = new StringBuilder(cur);

        for (int i = 0; i < cur.length(); ++i) {
            char old = strBuilder.charAt(i);

            for (char c = 'a'; c <= 'z'; ++c) {
                if (c != old) {
                    strBuilder.setCharAt(i, c);
                    if (dict.contains(strBuilder.toString())) {
                        res.add(strBuilder.toString());
                    }
                }
            }

            strBuilder.setCharAt(i, old);
        }

        return res;
    }
}