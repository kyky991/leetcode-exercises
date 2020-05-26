package com.zing.leetcode.explore.learn.binarysearch;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Zing
 * @date 2020-05-20
 */
public class Solution {

    /**
     * Binary Search Template I
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
     * Binary Search Template I
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
     * Binary Search Template I
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
     * Binary Search Template I
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
     * Binary Search Template II
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
     * Binary Search Template II
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
     * Binary Search Template II
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
     * Binary Search Template III
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
     * Binary Search Template III
     * Find K Closest Elements
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

    /**
     * Pow(x, n)
     */
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1D;
        }
        if (n == Integer.MIN_VALUE) {
            x = x * x;
            n = n / 2;
        }
        if (n < 0) {
            n = -n;
            x = 1 / x;
        }
        return n % 2 == 0 ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2);
    }

    /**
     * Valid Perfect Square
     */
    public boolean isPerfectSquare(int num) {
        int left = 1, right = num;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (mid < num / mid) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left * left == num;
    }

    public boolean isPerfectSquare2(int num) {
        int i = 1;
        while (num > 0) {
            num -= i;
            i += 2;
        }
        return num == 0;
    }

    /**
     * Find Smallest Letter Greater Than Target
     */
    public char nextGreatestLetter(char[] letters, char target) {
        int left = 0, right = letters.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (letters[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return letters[right % letters.length];
    }

    /**
     * Find Minimum in Rotated Sorted Array II
     */
    public int findMinII(int[] nums) {
        return findMinII(nums, 0, nums.length - 1);
    }

    private int findMinII(int[] nums, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            return Math.min(findMinII(nums, left, mid), findMinII(nums, mid + 1, right));
        }
        return nums[left];
    }

    public int findMinII2(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                right--;
            }
        }
        return nums[left];
    }

    /**
     * Intersection of Two Arrays
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        int[] source = nums1.length < nums2.length ? nums1 : nums2;
        int[] target = nums1.length < nums2.length ? nums2 : nums1;
        Arrays.sort(target);

        Set<Integer> result = new HashSet<>();
        for (int i : source) {
            int left = 0, right = target.length - 1;
            while (left <= right) {
                int mid = (left + right) >>> 1;
                if (target[mid] == i) {
                    result.add(i);
                    break;
                } else if (target[mid] > i) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * Intersection of Two Arrays II
     */
    public int[] intersectionII(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            if (map.containsKey(nums1[i])) {
                map.put(nums1[i], map.get(nums1[i]) + 1);
            } else {
                map.put(nums1[i], 1);
            }
        }
        for (int i = 0; i < nums2.length; i++) {
            if (map.containsKey(nums2[i]) && map.get(nums2[i]) > 0) {
                result.add(nums2[i]);
                map.put(nums2[i], map.get(nums2[i]) - 1);
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * Two Sum II - Input array is sorted
     */
    public int[] twoSumII(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            int value = target - numbers[i];
            for (int j = i + 1; j < numbers.length; j++) {
                if (value == numbers[j]) {
                    return new int[]{i + 1, j + 1};
                }
            }
        }
        return new int[0];
    }

    public int[] twoSumII2(int[] numbers, int target) {
        int[] result = new int[2];
        if (numbers == null || numbers.length < 2) {
            return result;
        }
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                result[0] = left + 1;
                result[1] = right + 1;
                break;
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }
        }
        return result;
    }

    /**
     * Find the Duplicate Number
     */
    public int findDuplicate(int[] nums) {
        if (nums.length > 1) {
            int slow = nums[0];
            int fast = nums[nums[0]];
            while (slow != fast) {
                slow = nums[slow];
                fast = nums[nums[fast]];
            }
            fast = 0;
            while (slow != fast) {
                slow = nums[slow];
                fast = nums[fast];
            }
            return slow;
        }
        return -1;
    }

    /**
     * Median of Two Sorted ArrayscompareAndSetTail
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        if (m > n) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int minIdx = 0, maxIdx = m;
        int i = 0, j = 0, n1 = 0, n2 = 0, mid = (m + n + 1) >> 1;
        while (minIdx <= maxIdx) {
            i = (minIdx + maxIdx) >> 1;
            j = mid - i;
            if (i < m && j > 0 && nums2[j - 1] > nums1[i]) {
                minIdx = i + 1;
            } else if (i > 0 && j < n && nums1[i - 1] > nums2[j]) {
                maxIdx = i - 1;
            } else {
                if (i == 0) {
                    n1 = nums2[j - 1];
                } else if (j == 0) {
                    n1 = nums1[i - 1];
                } else {
                    n1 = Math.max(nums1[i - 1], nums2[j - 1]);
                }
                break;
            }
        }
        if ((m + n) % 2 == 1) {
            return n1;
        }
        if (i == m) {
            n2 = nums2[j];
        } else if (j == n) {
            n2 = nums1[i];
        } else {
            n2 = Math.min(nums1[i], nums2[j]);
        }
        return (n1 + n2) / 2.0;
    }
}
