package ru.itis.aisd501;

public class Timsort {
    public static void main(String[] args) {

    }

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
}
