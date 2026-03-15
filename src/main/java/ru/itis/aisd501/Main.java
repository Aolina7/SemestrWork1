package ru.itis.aisd501;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Timsort timSort = new Timsort();
        Test testing = new Test();
        testing.create();

        //считывание данных, расчёт времени и количества итераций
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("test.txt"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] arrayStr = line.split(" ");
                int[] arr = new int[arrayStr.length];
                for (int i = 0; i < arr.length; i++) {
                    arr[i] = Integer.parseInt(arrayStr[i]);
                }
                long startTime = System.nanoTime();
                timSort.timsort(arr);
                long endTime = System.nanoTime();
                System.out.println(arr.length + ";" + (endTime - startTime) + ";" + arr.length + ";" + timSort.iterations);
                timSort.iterations = 0;
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
