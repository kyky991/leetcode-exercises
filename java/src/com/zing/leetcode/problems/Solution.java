package com.zing.leetcode.problems;

import java.util.*;

/**
 * @author Zing
 * @date 2020-06-13
 */
public class Solution {

    /**
     * 283. Move Zeroes
     */
    public void moveZeroes(int[] nums) {
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[k++] = nums[i];
            }
        }
        for (int i = k; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    public void moveZeroes2(int[] nums) {
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (i != k) {
                    int tmp = nums[i];
                    nums[i] = nums[k];
                    nums[k] = tmp;
                }
                k++;
            }
        }
    }

    /**
     * 75. Sort Colors
     */
    public void sortColors(int[] nums) {
        int[] bucket = new int[3];
        for (int i = 0; i < nums.length; i++) {
            bucket[nums[i]]++;
        }
        int k = 0;
        for (int i = 0; i < bucket.length; i++) {
            while (bucket[i]-- > 0) {
                nums[k++] = i;
            }
        }
    }

    public void sortColors2(int[] nums) {
        int zero = 0, second = nums.length - 1;
        for (int i = 0; i <= second; i++) {
            if (nums[i] == 0) {
                swap(nums, i, zero);
                zero++;
            } else if (nums[i] == 2) {
                swap(nums, i, second);
                i--;
                second--;
            }
        }
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 11. Container With Most Water
     */
    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1;
        int res = 0;
        while (i < j) {
            int h = Math.min(height[i], height[j]);
            res = Math.max(res, h * (j - i));

            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return res;
    }

    /**
     * 209. Minimum Size Subarray Sum
     */
    public int minSubArrayLen(int s, int[] nums) {
        int i = 0, j = 0;
        int sum = 0;
        int res = Integer.MAX_VALUE;
        while (j < nums.length) {
            sum += nums[j];

            while (sum >= s && i <= j) {
                res = Math.min(res, j - i + 1);
                sum -= nums[i++];
            }
            j++;
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    /**
     * 3. Longest Substring Without Repeating Characters
     */
    public int lengthOfLongestSubstring(String s) {
        int[] window = new int[256];

        int res = 0;
        int left = 0, right = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            window[c]++;
            right++;

            while (window[c] > 1) {
                char d = s.charAt(left);
                window[d]--;
                left++;
            }
            res = Math.max(res, right - left);
        }
        return res;
    }

    public int lengthOfLongestSubstring2(String s) {
        int[] window = new int[256];

        int res = 0;
        int left = 0, right = -1;
        while (left < s.length()) {
            if (right + 1 < s.length() && window[s.charAt(right + 1)] == 0) {
                window[s.charAt(right + 1)]++;
                right++;
            } else {
                window[s.charAt(left)]--;
                left++;
            }
            res = Math.max(res, right - left + 1);
        }
        return res;
    }

    /**
     * 438. Find All Anagrams in a String
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();

        Map<Character, Integer> window = new HashMap<>();
        Map<Character, Integer> need = new HashMap<>();
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int valid = 0;
        int left = 0, right = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;

            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (need.get(c).equals(window.get(c))) {
                    valid++;
                }
            }

            while (right - left >= p.length()) {
                if (valid == need.size()) {
                    res.add(left);
                }
                char d = s.charAt(left);
                left++;
                if (need.containsKey(d)) {
                    if (need.get(d).equals(window.get(d))) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return res;
    }

    /**
     * 76. Minimum Window Substring
     */
    public String minWindow(String s, String t) {
        Map<Character, Integer> window = new HashMap<>();
        Map<Character, Integer> need = new HashMap<>();
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int valid = 0;
        int left = 0, right = 0;
        int start = 0, len = Integer.MAX_VALUE;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;

            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (need.get(c).equals(window.get(c))) {
                    valid++;
                }
            }

            while (valid == need.size()) {
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }

                char d = s.charAt(left);
                left++;
                if (need.containsKey(d)) {
                    if (need.get(d).equals(window.get(d))) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }

    /**
     * 349. Intersection of Two Arrays
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        for (int i : nums1) {
            set.add(i);
        }

        Set<Integer> res = new HashSet<>();
        for (int i : nums2) {
            if (set.contains(i)) {
                res.add(i);
            }
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * 242. Valid Anagram
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (char c : t.toCharArray()) {
            Integer count = map.get(c);
            if (count == null || count == 0) {
                return false;
            }
            count--;
            map.put(c, count);
        }
        return true;
    }

    /**
     * 202. Happy Number
     */
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (true) {
            int sum = 0;
            while (n != 0) {
                int t = n % 10;
                sum += t * t;
                n /= 10;
            }

            if (sum == 1) {
                return true;
            } else {
                if (set.contains(sum)) {
                    return false;
                }
                set.add(sum);
                n = sum;
            }
        }
    }

    /**
     * 290. Word Pattern
     */
    public boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");
        if (words.length != pattern.length()) {
            return false;
        }
        Map<Character, Integer> ci = new HashMap<>();
        Map<String, Integer> si = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if (!ci.getOrDefault(c, 0).equals(si.getOrDefault(words[i], 0))) {
                return false;
            }
            ci.put(c, i);
            si.put(words[i], i);
        }

        return true;
    }

    /**
     * 205. Isomorphic Strings
     */
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Integer> si = new HashMap<>();
        Map<Character, Integer> ci = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            char d = t.charAt(i);
            if (!si.getOrDefault(c, 0).equals(ci.getOrDefault(d, 0))) {
                return false;
            }
            si.put(c, i + 1);
            ci.put(d, i + 1);
        }

        return true;
    }

    /**
     * 451. Sort Characters By Frequency
     */
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        List<Character>[] bucket = new List[s.length() + 1];
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            int freq = entry.getValue();
            if (bucket[freq] == null) {
                bucket[freq] = new ArrayList<>();
            }
            bucket[freq].add(entry.getKey());
        }

        StringBuilder sb = new StringBuilder();
        for (int i = bucket.length - 1; i >= 0; i--) {
            if (bucket[i] != null) {
                for (char c : bucket[i]) {
                    for (int j = 0; j < i; j++) {
                        sb.append(c);
                    }
                }
            }
        }
        return sb.toString();
    }

    /**
     * 1. Two Sum
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int val = target - nums[i];
            if (map.containsKey(val)) {
                return new int[]{i, map.get(val)};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }

    /**
     * 15. 3Sum
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                int lo = i + 1, hi = nums.length - 1, sum = -nums[i];
                while (lo < hi) {
                    if (nums[lo] + nums[hi] == sum) {
                        res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                        while (lo < hi && nums[lo] == nums[lo + 1]) {
                            lo++;
                        }
                        while (lo < hi && nums[hi] == nums[hi - 1]) {
                            hi--;
                        }
                        lo++;
                        hi--;
                    } else if (nums[lo] + nums[hi] < sum) {
                        lo++;
                    } else {
                        hi--;
                    }
                }
            }
        }
        return res;
    }

    /**
     * 18. 4Sum
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);

        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int lo = j + 1, hi = nums.length - 1, sum = target - nums[i] - nums[j];
                while (lo < hi) {
                    if (nums[lo] + nums[hi] == sum) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[lo], nums[hi]));
                        while (lo < hi && nums[lo] == nums[lo + 1]) {
                            lo++;
                        }
                        while (lo < hi && nums[hi] == nums[hi - 1]) {
                            hi--;
                        }
                        lo++;
                        hi--;
                    } else if (nums[lo] + nums[hi] < sum) {
                        lo++;
                    } else {
                        hi--;
                    }
                }
            }
        }
        return res;
    }

    /**
     * 16. 3Sum Closest
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        int res = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            int lo = i + 1, hi = nums.length - 1;
            while (lo < hi) {
                int sum = nums[lo] + nums[hi] + nums[i];
                if (sum > target) {
                    hi--;
                } else {
                    lo++;
                }
                if (Math.abs(target - sum) < Math.abs(target - res)) {
                    res = sum;
                }
            }
        }
        return res;
    }

    /**
     * 454. 4Sum II
     */
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int c : C) {
            for (int d : D) {
                map.put(c + d, map.getOrDefault(c + d, 0) + 1);
            }
        }

        int res = 0;
        for (int a : A) {
            for (int b : B) {
                int val = -(a + b);
                if (map.containsKey(val)) {
                    res += map.get(val);
                }
            }
        }
        return res;
    }

    /**
     * 49. Group Anagrams
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(s);
        }
        return new ArrayList<>(map.values());
    }

    /**
     * 447. Number of Boomerangs
     */
    public int numberOfBoomerangs(int[][] points) {
        int res = 0;
        for (int i = 0; i < points.length; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = 0; j < points.length; j++) {
                if (i != j) {
                    int dis = dis(points[i], points[j]);
                    map.put(dis, map.getOrDefault(dis, 0) + 1);
                }
            }
            for (int value : map.values()) {
                res += value * (value - 1);
            }
        }
        return res;
    }

    private int dis(int[] p1, int[] p2) {
        return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
    }

    /**
     * 149. Max Points on a Line
     */
    public int maxPoints(int[][] points) {
        int res = 0;
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();

        for (int i = 0; i < points.length; i++) {
            map.clear();

            int overlap = 0, max = 0;
            for (int j = i + 1; j < points.length; j++) {
                int x = points[j][0] - points[i][0];
                int y = points[j][1] - points[i][1];
                if (x == 0 && y == 0) {
                    overlap++;
                    continue;
                }

                int gcd = gcd(x, y);
                if (gcd != 0) {
                    x /= gcd;
                    y /= gcd;
                }

                if (map.containsKey(x)) {
                    if (map.get(x).containsKey(y)) {
                        map.get(x).put(y, map.get(x).get(y) + 1);
                    } else {
                        map.get(x).put(y, 1);
                    }
                } else {
                    Map<Integer, Integer> m = new HashMap<>();
                    m.put(y, 1);
                    map.put(x, m);
                }
                max = Math.max(max, map.get(x).get(y));
            }
            res = Math.max(res, max + overlap + 1);
        }
        return res;
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    /**
     * 219. Contains Duplicate II
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            }

            set.add(nums[i]);

            if (set.size() == k + 1) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }

    /**
     * 217. Contains Duplicate
     */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }

}
