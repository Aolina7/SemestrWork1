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
}
