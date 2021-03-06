// 273. Integer to English Words
class Solution {
    // 这种包含不同数据一一对应的问题，要么数组存储，要么map存储
    // 首先比较直观的解法是根据英文读法的惯例，每三位算一次，在每三位之间添加 Thousand, Million, Billion 这些词
    // 问题就转换成两点：如何把三位数转化成英文单词，和如何判断何时应该添加 Thousand, Million, Billion 这些词
    // 1. 一百以内的数字转换成英文分为1 - 19, 20, 30, ... , 90 这些单词，如果小于20的数字就可以直接转换；如果是20 - 99的数字，可以拆分后处理
    // 2. 何时应该添加 Thousand, Million, Billion 这几个词，是根据高三位的数字判断的，如果高三位数字（4-6,7-9,10-12）全为0，就不添加，否则就添加
    // 3. 单词之间的空格也需要留意
    // 4. 测试用例就用 1234567 和10000, 100001, 1000010等数字即可
    // 一个常识：32位Integer最大值2147483647, 约为21亿，2.1 billion
    private final String[] LESS_THAN_20 = { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen" };
    private final String[] TENS = { "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };
    private final String[] THOUSANDS = { "", "Thousand", "Million", "Billion" }; // 数组第一个元素必须放一个空字符串以保证最低三位也能以同样规律添加单词

    public String numberToWords(int num) {
        if (num == 0)
            return "Zero";

        int i = 0;
        String words = "";

        while (num > 0) {
            if (num % 1000 != 0)
                words = helper(num % 1000) + THOUSANDS[i] + " " + words;
            num /= 1000;
            i++;
        }

        return words.trim();
    }

    private String helper(int num) {
        if (num == 0) {
            return "";
        } else if (num < 20) {
            return LESS_THAN_20[num] + " ";
        } else if (num < 100) {
            return TENS[num / 10] + " " + helper(num % 10); // 这里用 return TENS[num / 10] + " " + LESS_THAN_20[num % 10] + " ";会报错，如果是40，50这种数字无法保证末尾的空格是1个，还是递归可靠
        } else {
            return LESS_THAN_20[num / 100] + " Hundred " + helper(num % 100);
        }
    }
}

class Solution {
    // 更彻底的递归解法
    private final String[] LESS_THAN_20 = { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen" };
    private final String[] TENS = new String[] { "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };

    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        } else {
            return helper(num);
        }
    }

    private String helper(int num) {
        String result;
        if (num < 20) {
            result = LESS_THAN_20[num];
        } else if (num < 100) {
            result = TENS[num / 10] + " " + helper(num % 10);
        } else if (num < 1000) {
            result = helper(num / 100) + " Hundred " + helper(num % 100);
        } else if (num < 1000000) {
            result = helper(num / 1000) + " Thousand " + helper(num % 1000);
        } else if (num < 1000000000) {
            result = helper(num / 1000000) + " Million " + helper(num % 1000000);
        } else {
            result = helper(num / 1000000000) + " Billion " + helper(num % 1000000000);
        }
        // 注意去掉空格
        return result.trim();
    }
}

class Solution {
    // 非递归版本
    private String[] LESS_THAN_20 = { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen" };
    private String[] TENS = { "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };
    private String[] THOUSANDS = { "Thousand", "Million", "Billion" };

    // 1,234,567,890
    public String numberToWords(int num) {
        // 1,234,567 => 890
        String result = convertHundred(num % 1000);
        for (int i = 0; i < 3; ++i) {
            // 1,234,567 => 1,234 + 567
            // 1,234 => 1 + 234
            // 1 => 0 + 1
            num /= 1000;
            if (num % 1000 != 0) {
                result = convertHundred(num % 1000) + " " + THOUSANDS[i] + " " + result;
            }
        }
        result = result.trim();
        return result == "" ? "Zero" : result;
    }

    private String convertHundred(int num) {
        // 123 => 1 + 20 + 3
        String result = "";
        int a = num / 100;
        int b = num % 100;
        int c = num % 10;
        if (b < 20) {
            result = LESS_THAN_20[b];
        } else {
            result = TENS[b / 10] + (c != 0 ? " " + LESS_THAN_20[c] : "");
        }
        if (a > 0) {
            result = LESS_THAN_20[a] + " Hundred" + (b != 0 ? " " + result : "");
        }

        return result;
    }
}

// follow up: 可以转换负数
// 先转换成正数然后同样处理，注意 Integer.MIN_VALUE 会溢出，所以要用 long 存储
class Solution {
    private String[] LESS_THAN_20 = { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen" };
    private String[] TENS = { "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };

    public String IntegerToEnglish(int num) {
        if (num == 0) {
            return "zero";
        }
        long n = (long) num;
        n = Math.abs(n);

        if (num < 0) {
            return "Negative " + helper(n);
        } else {
            return helper(n);
        }
    }

    private String helper(long n) {
        String result;
        if (n < 20) {
            // 数组下标注意转换成 int
            result = LESS_THAN_20[(int) n];
        } else if (n < 100) {
            result = TENS[(int) n / 10] + " " + helper(n % 10);
        } else if (n < 1000) {
            result = helper(n / 100) + " Hundred " + helper(n % 100);
        } else if (n < 1000000) {
            result = helper(n / 1000) + " Thousand " + helper(n % 1000);
        } else if (n < 1000000000) {
            result = helper(n / 1000000) + " Million " + helper(n % 1000000);
        } else {
            result = helper(n / 1000000000) + " Billion " + helper(n % 1000000000);
        }

        return result.trim();
    }
}