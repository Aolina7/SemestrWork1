package ru.itis.aisd501;

import java.util.ArrayList;
import java.util.List;

public class Timsort {
    public static void main(String[] args) {

    }

    //расчёт минимального размера подмассива, т.е. minRun
    static int getMinrun(int n) {
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
    static void timsort(int[] arr) {
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

        }
    }

    //поиск правой границы, индекса подмассивов
    static int findRun(int[] arr, int start, int n) {
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
    static void reverse(int[] arr, int l, int r) {
        while (l < r) {
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            l++;
            r--;
        }
    }

    //сортировка вставками для небольших массивов
    static void insertionSort(int[] arr, int left, int right) {
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
}
