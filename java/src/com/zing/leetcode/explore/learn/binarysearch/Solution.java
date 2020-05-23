package com.zing.leetcode.explore.learn.binarysearch;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * First Bad Version
     */
    public int firstBadVersion(int n) {
        int left = 1, right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (!isBadVersion(mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }

    private boolean isBadVersion(int version) {
        return true;
    }

    /**
     * Find Peak Element
     */
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int mid2 = mid + 1;
            if (nums[mid] < nums[mid2]) {
                left = mid2;
            } else {
                right = mid;
            }
        }
        return left;
    }

    /**
     * Find Minimum in Rotated Sorted Array
     */
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            if (nums[left] < nums[right]) {
                return nums[left];
            }
            int mid = left + (right - left) / 2;
            if (nums[mid] >= nums[left]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }

    /**
     * Search for a Range
     */
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        if (nums.length == 0) {
            return result;
        }
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (nums[left] != target) {
            return result;
        }
        result[0] = left;

        right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2 + 1;
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        result[1] = right;
        return result;
    }

    /**
     *  Find K Closest Elements
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0, right = arr.length - k;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (x - arr[mid] > arr[mid + k] - x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return Arrays.stream(arr, left, left + k).boxed().collect(Collectors.toList());
    }
}
