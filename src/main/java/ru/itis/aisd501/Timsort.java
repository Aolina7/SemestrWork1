package ru.itis.aisd501;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Timsort {
    //расчёт минимального размера подмассива, т.е. minRun
    public static int getMinrun(int n) {
        // n = N, размер входного массива.
        int r = 0;
        // 2^6 = 64.
        while (n >= 64) {
            r |= (n & 1);
            // Если среди младших битов n имеется хотя бы один ненулевой бит, переменная r станет равна 1.
            n >>= 1;
        }
        // Теперь переменная n содержит старшие 6 бит N.
        return n + r; // minrun
    }

    //основной метод сортировки
    public static void timsort(int[] arr) {
        int n = arr.length;
        int minRun = getMinrun(n);
        List<int[]> runs = new ArrayList<>();

        int i = 0;
        while (i < n) {
            int runEnd = findRun(arr, i, n);
            int runLen = runEnd - i;

            //расширяем короткие массивы до minRun с помощью сортировки вставками
            if (runLen < minRun) {
                int end = Math.min(i + minRun, n);
                insertionSort(arr, i, end - 1);
                runEnd = end;
            }
            runs.add(new int[]{i, runEnd});
            //текущий индекс равен индексу последнего элемента последнего run'а
            i = runEnd;

            //слияние двух массивов в один в этом же цикле
            while (runs.size() > 1) {
                //берёт последние два массива
                int[] run1 = runs.get(runs.size() - 2);
                int[] run2 = runs.get(runs.size() - 1);

                //l и r - левая и правая границы, левый и правый индексы соответсвенно
                int l1 = run1[0], r1 = run1[1];
                int l2 = run2[0], r2 = run2[1];
                int len1 = r1 - l1, len2 = r2 - l2;

                if (len1 <= len2) {
                    merge(arr, l1, r1 - 1, r2 - 1);
                    runs.remove(runs.size() - 1);
                    //добавляет в runs слившиеся два массива
                    //удалили последний массив, а оставшийся(т.е. предпоследний, т.к. индексы сместились)
                    // превратили в результат - новый слившийся из двух массивов массив
                    runs.set(runs.size() - 1, new int[]{l1, r2});
                } else break;
            }
        }

        //слияние оставшихся run'ов
        while (runs.size() > 1) {
            int[] run1 = runs.get(runs.size() - 2);
            int[] run2 = runs.get(runs.size() - 1);

            int l1 = run1[0], r1 = run1[1];
            int l2 = run2[0], r2 = run2[1];
            merge(arr, l1, r1 - 1, r2 - 1);
            runs.remove(runs.size() - 1);
            runs.set(runs.size() - 1, new int[]{l1, r2});
        }
    }

    //поиск правой границы, индекса подмассивов
    public static int findRun(int[] arr, int start, int n) {
        int end = start + 1;
        if (end == n) return end;

        //если элементы идут в порядке убывания, то меняем местами
        if (arr[end] < arr[start]) {
            //ищем индекс, до которого элементы идут в порядке убывания
            while (end < n && arr[end] < arr[end - 1])
                end++;
            reverse(arr, start, end - 1);
        } else {
            //ищем индекс, до которого порядок не нарушется
            while (end < n && arr[end] >= arr[end - 1])
                end++;
        }
        return end;
    }

    //обратный порядок массива с l до r
    public static void reverse(int[] arr, int l, int r) {
        while (l < r) {
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            l++;
            r--;
        }
    }

    //сортировка вставками для небольших массивов
    public static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    //слияние просиходит в исходном массиве!
    public static void merge(int[] arr, int l, int m, int r) {
        int[] left = Arrays.copyOfRange(arr, l, m + 1);
        int[] right = Arrays.copyOfRange(arr, m + 1, r + 1);

        int i = 0, j = 0, k = l;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) arr[k++] = left[i++];
            else arr[k++] = right[j++];
        }

        while (i < left.length) arr[k++] = left[i++];
        while (j < right.length) arr[k++] = right[j++];
    }
}
