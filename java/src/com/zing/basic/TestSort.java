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
     * 最佳情况：T(n) = O(nlogn)   最差情况：T(n) = O(n^2)   平均情况：T(n) = O(nlogn)  不稳定
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
     * 挖坑填数法
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

    /**
     * 最佳情况：T(n) = O(nlogn) 最差情况：T(n) = O(nlogn) 平均情况：T(n) = O(nlogn)  不稳定
     * <p>
     * a.将无需序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆;
     * b.将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
     * c.重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序。
     */
    public static int[] heapSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }
        //1.构建大顶堆
        //从最后一个非叶子结点开始
        for (int i = arr.length / 2 - 1; i >= 0; --i) {
            adjustHeap(arr, i, arr.length);
        }

        //2.调整堆结构+交换堆顶元素与末尾元素
        for (int i = arr.length - 1; i > 0; --i) {
            //堆顶元素和末尾元素进行交换
            int tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;

            //重新对堆进行调整
            adjustHeap(arr, 0, i);
        }
        return arr;
    }

    private static void adjustHeap(int[] arr, int start, int end) {
        int tmp = arr[start];
        for (int k = 2 * start + 1; k < end; k = 2 * k + 1) {
            //如果右边值大于左边值，指向右边
            if (k + 1 < end && arr[k] < arr[k + 1]) {
                k++;
            }
            //如果子节点大于父节点，将子节点值赋给父节点,并以新的子节点作为父节点（不用进行交换）
            if (arr[k] > tmp) {
                arr[start] = arr[k];
                start = k;
            } else {
                break;
            }
        }
        //put the value in the final position
        arr[start] = tmp;
    }

    /**
     * 最佳情况：T(n) = O(n+k)  最差情况：T(n) = O(n+k)  平均情况：T(n) = O(n+k)  稳定
     */
    public static int[] countingSort(int[] arr) {
        int min = arr[0];
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int[] bucket = new int[max - min + 1];
        for (int i = 0; i < arr.length; i++) {
            bucket[arr[i] - min]++;
        }

        int index = 0;
        for (int i = 0; i < bucket.length; i++) {
            while (bucket[i] > 0) {
                arr[index++] = i + min;
                bucket[i]--;
            }
        }
        return arr;
    }

    /**
     * 最佳情况：T(n) = O(n+k)  最差情况：T(n) = O(n^2)  平均情况：T(n) = O(n+k)  稳定
     */
    public static int[] bucketSort(int[] arr) {
        return bucketSort(arr, 5);
    }

    public static int[] bucketSort(int[] arr, int bucketSize) {
        int min = arr[0];
        int max = arr[0];
        for (int value : arr) {
            if (value < min) {
                min = value;
            } else if (value > max) {
                max = value;
            }
        }

        int bucketCount = (max - min) / bucketSize + 1;
        int[][] buckets = new int[bucketCount][0];

        for (int i = 0; i < arr.length; i++) {
            int index = (arr[i] - min) / bucketSize;
            int[] b = Arrays.copyOf(buckets[index], buckets[index].length + 1);
            b[b.length - 1] = arr[i];
            buckets[index] = b;
        }

        int idx = 0;
        for (int[] bucket : buckets) {
            if (bucket.length <= 0) {
                continue;
            }

            insertSort(bucket);

            for (int b : bucket) {
                arr[idx++] = b;
            }
        }

        return arr;
    }

    /**
     * 最佳情况：T(n) = O(n * k)   最差情况：T(n) = O(n * k)   平均情况：T(n) = O(n * k)  稳定
     */
    public static int[] radixSort(int[] arr) {
        int max = arr[0];
        for (int value : arr) {
            max = Math.max(max, value);
        }
        int len = 0;
        while (max != 0) {
            len++;
            max = max / 10;
        }

        int mod = 10, div = 1;
        for (int i = 0; i < len; i++, div = 10 * div, mod = 10 * mod) {
            int[][] counter = new int[mod * 2][0];
            for (int j = 0; j < arr.length; j++) {
                int bucket = ((arr[j] % mod) / div) + mod;
                int[] b = Arrays.copyOf(counter[bucket], counter[bucket].length + 1);
                b[b.length - 1] = arr[j];
                counter[bucket] = b;
            }

            int pos = 0;
            for (int[] bucket : counter) {
                for (int b : bucket) {
                    arr[pos++] = b;
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(2, 5, 4, 1, 9, 5, 4, 3);

        Collections.shuffle(list);
        int[] arr = list.stream().mapToInt(Integer::intValue).toArray();
        System.out.println("bubbleSort" + "\t\tbefore:" + Arrays.toString(arr) + "\t\tafter:" + Arrays.toString(bubbleSort(arr)));

        Collections.shuffle(list);
        arr = list.stream().mapToInt(Integer::intValue).toArray();
        System.out.println("selectionSort" + "\t\tbefore:" + Arrays.toString(arr) + "\t\tafter:" + Arrays.toString(selectionSort(arr)));

        Collections.shuffle(list);
        arr = list.stream().mapToInt(Integer::intValue).toArray();
        System.out.println("insertSort" + "\t\tbefore:" + Arrays.toString(arr) + "\t\tafter:" + Arrays.toString(insertSort(arr)));

        Collections.shuffle(list);
        arr = list.stream().mapToInt(Integer::intValue).toArray();
        System.out.println("shellSort" + "\t\tbefore:" + Arrays.toString(arr) + "\t\tafter:" + Arrays.toString(shellSort(arr)));

        Collections.shuffle(list);
        arr = list.stream().mapToInt(Integer::intValue).toArray();
        System.out.println("mergeSort" + "\t\tbefore:" + Arrays.toString(arr) + "\t\tafter:" + Arrays.toString(mergeSort(arr)));

        Collections.shuffle(list);
        arr = list.stream().mapToInt(Integer::intValue).toArray();
        System.out.println("quickSort" + "\t\tbefore:" + Arrays.toString(arr) + "\t\tafter:" + Arrays.toString(quickSort(arr)));

        Collections.shuffle(list);
        arr = list.stream().mapToInt(Integer::intValue).toArray();
        System.out.println("heapSort" + "\t\tbefore:" + Arrays.toString(arr) + "\t\tafter:" + Arrays.toString(heapSort(arr)));

        Collections.shuffle(list);
        arr = list.stream().mapToInt(Integer::intValue).toArray();
        System.out.println("countingSort" + "\t\tbefore:" + Arrays.toString(arr) + "\t\tafter:" + Arrays.toString(countingSort(arr)));

        Collections.shuffle(list);
        arr = list.stream().mapToInt(Integer::intValue).toArray();
        System.out.println("bucketSort" + "\t\tbefore:" + Arrays.toString(arr) + "\t\tafter:" + Arrays.toString(bucketSort(arr)));

        Collections.shuffle(list);
        arr = list.stream().mapToInt(Integer::intValue).toArray();
        System.out.println("radixSort" + "\t\tbefore:" + Arrays.toString(arr) + "\t\tafter:" + Arrays.toString(radixSort(arr)));
    }

}
