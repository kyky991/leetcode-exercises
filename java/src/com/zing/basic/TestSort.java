package com.zing.basic;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Zing
 * @date 2020-05-29
 */
public class TestSort {

    /**
     * 最佳情况：T(n) = O(n)  最差情况：T(n) = O(n^2)  平均情况：T(n) = O(n^2)  稳定
     */
    public static int[] bubbleSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }
        boolean flag = false;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = tmp;

                    // 使用flag最佳情况变为O[n]
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
        return arr;
    }

    /**
     * 最佳情况：T(n) = O(n^2)  最差情况：T(n) = O(n^2)  平均情况：T(n) = O(n^2)  不稳定
     */
    public static int[] selectionSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }
        for (int i = 0; i < arr.length; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                int tmp = arr[i];
                arr[i] = arr[minIdx];
                arr[minIdx] = tmp;
            }
        }
        return arr;
    }

    /**
     * 最佳情况：T(n) = O(n)  最差情况：T(n) = O(n^2)  平均情况：T(n) = O(n^2)  稳定
     */
    public static int[] insertSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }
        for (int i = 1; i < arr.length; i++) {
            int tmp = arr[i];

            int j = i;
            while (j > 0 && tmp < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }
            if (j != i) {
                arr[j] = tmp;
            }
        }
        return arr;
    }

    /**
     * 不稳定
     */
    public static int[] shellSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }
        int gap = 1;
        while (gap < arr.length / 3) {
            gap = gap * 3 + 1;
        }
        while (gap > 0) {
            for (int i = gap; i < arr.length; i++) {
                int tmp = arr[i];
                int j = i - gap;
                while (j >= 0 && arr[j] > tmp) {
                    arr[j + gap] = arr[j];
                    j -= gap;
                }
                arr[j + gap] = tmp;
            }
            gap = gap / 3;
        }
        return arr;
    }

    public static int[] mergeSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }
        mergeSort(arr, 0, arr.length - 1);
        return arr;
    }

    private static void mergeSort(int[] arr, int lo, int hi) {
        if (lo < hi) {
            int mid = (lo + hi) >>> 1;
            mergeSort(arr, lo, mid);
            mergeSort(arr, mid + 1, hi);
            merge(arr, lo, mid, hi);
        }
    }

    private static void merge(int[] arr, int lo, int mid, int hi) {
        int[] tmp = new int[hi - lo + 1];
        int i = lo, j = mid + 1, k = 0;
        while (i <= mid && j <= hi) {
            if (arr[i] < arr[j]) {
                tmp[k++] = arr[i++];
            } else {
                tmp[k++] = arr[j++];
            }
        }
        while (i <= mid) {
            tmp[k++] = arr[i++];
        }
        while (j <= hi) {
            tmp[k++] = arr[j++];
        }
        for (int l = 0; l < tmp.length; l++) {
            arr[lo + l] = tmp[l];
        }
    }

    /**
     * 最佳情况：T(n) = O(nlogn)   最差情况：T(n) = O(n^2)   平均情况：T(n) = O(nlogn)
     */
    public static int[] quickSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }
        quickSort(arr, 0, arr.length - 1);
        return arr;
    }

    private static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int p = partition(arr, left, right);
            quickSort(arr, left, p - 1);
            quickSort(arr, p + 1, right);
        }
    }

    /**
     * 挖坑填数发
     */
    private static int partition(int[] arr, int left, int right) {
        int i = left, j = right;
        //s[l]即s[i]就是第一个坑
        int tmp = arr[left];
        while (i < j) {
            // 从右向左找小于x的数来填s[i]
            while (i < j && arr[j] >= tmp) {
                j--;
            }
            if (i < j) {
                //将s[j]填到s[i]中，s[j]就形成了一个新的坑
                arr[i] = arr[j];
                i++;
            }

            // 从左向右找大于或等于x的数来填s[j]
            while (i < j && arr[i] <= tmp) {
                i++;
            }
            if (i < j) {
                //将s[i]填到s[j]中，s[i]就形成了一个新的坑
                arr[j] = arr[i];
                j--;
            }
        }

        //退出时，i等于j。将x填到这个坑中。
        arr[i] = tmp;
        return i;
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(2, 5, 4, 1, 9, 5, 4, 3);

        Collections.shuffle(list);
        int[] arr = list.stream().mapToInt(Integer::intValue).toArray();
        System.out.println("before:" + Arrays.toString(arr) + "\t\tafter:" + Arrays.toString(bubbleSort(arr)));

        Collections.shuffle(list);
        arr = list.stream().mapToInt(Integer::intValue).toArray();
        System.out.println("before:" + Arrays.toString(arr) + "\t\tafter:" + Arrays.toString(selectionSort(arr)));

        Collections.shuffle(list);
        arr = list.stream().mapToInt(Integer::intValue).toArray();
        System.out.println("before:" + Arrays.toString(arr) + "\t\tafter:" + Arrays.toString(insertSort(arr)));

        Collections.shuffle(list);
        arr = list.stream().mapToInt(Integer::intValue).toArray();
        System.out.println("before:" + Arrays.toString(arr) + "\t\tafter:" + Arrays.toString(shellSort(arr)));

        Collections.shuffle(list);
        arr = list.stream().mapToInt(Integer::intValue).toArray();
        System.out.println("before:" + Arrays.toString(arr) + "\t\tafter:" + Arrays.toString(mergeSort(arr)));

        Collections.shuffle(list);
        arr = list.stream().mapToInt(Integer::intValue).toArray();
        System.out.println("before:" + Arrays.toString(arr) + "\t\tafter:" + Arrays.toString(quickSort(arr)));
    }

}