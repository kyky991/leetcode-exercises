package com.zing.leetcode.explore.learn.binarysearch;

/**
 * @author Zing
 * @date 2020-05-20
 */
public class Solution {

    /**
     * Binary Search
     */
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * Sqrt(x)
     */
    public int mySqrt(int x) {
        int left = 1, right = x;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid == x / mid) {
                return mid;
            } else if (mid < x / mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }

    /**
     * Guess Number Higher or Lower
     */
    public int guessNumber(int n) {
        int left = 1, right = n;
        while (true) {
            int mid = left + (right - left) / 2;
            int guess = guess(mid);
            if (0 == guess) {
                return mid;
            } else if (1 == guess) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    }

    private int guess(int num) {
        return -1;
    }

    /**
     * Search in Rotated Sorted Array
     */
    public int searchRotated(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        int offset = left;
        left = 0;
        right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int rmid = (offset + mid) % nums.length;
            if (nums[rmid] == target) {
                return rmid;
            } else if (nums[rmid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
