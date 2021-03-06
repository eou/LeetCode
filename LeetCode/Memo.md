# Memoization

> 天灰灰 會不會 讓我忘了妳是誰

## 1 Two Sum
Use `Set`/`Map` recording the previous numbers' appearances. 

If it needs to return indices, use `Map<Integer, Integer>` (number => index).

---

## 2 Add Two Numbers
This problem intentionally make input linked lists reversely so that it will be easier to calculate sum one digit by one digit.

If the input linked lists's order are the same as digits in numbers, just reverse them then calculate.

---

## 3 Longest Substring Without Repeating Characters
- 159
- 340

Sliding window. 

Use `Map<Character, Integer>` (char => frequency). 

There is a trick for this problem that using (char => index) other than (char => frequency).

---

## 4 Median of Two Sorted Arrays

Binary Search. 

Find kth element in sorted array recursively. 

For non-recursively, find left half of sorted array. See how many elements would A contribute to the left half.

---

## 5 Longest Palindromic Substring

Expand from one character to both sides. 

The most efficient way is **Manacher's algorithm**. Use previous longest palindrome to help find current palindrome.

---

## 159 Longest Substring with At Most Two Distinct Characters

- 3
- 340

Sliding window. 

Use `Map<Character, Integer>` (char => frequency). Map.size() is the distinct characters number.

---

## 315 Count of Smaller Numbers After Self

- 493

- From right to left, binary Search current number's position
- calculate frequency array prefix sum => Fenwick Tree
- Binary Search Tree, record left children count but not smaller count

---

## 340 Longest Substring with At Most K Distinct Characters

- 3
- 159

Sliding window. 

Use `Map<Character, Integer>` (char => frequency). Map.size() is the distinct characters number.

---

## 493 Reverse Pairs

- 315

- Fenwick Tree. Find the correct lower bound of frequency array and calculate prefix sum.
- https://leetcode.com/problems/reverse-pairs/discuss/97268/general-principles-behind-problems-similar-to-reverse-pairs

---