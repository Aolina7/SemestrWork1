package ru.itis.aisd501;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Test {
    public void create() {
        ArrayList<ArrayList<Integer>> tests = new ArrayList<>(100);
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int capacity = random.nextInt(100, 10000);
            ArrayList<Integer> arraylist = new ArrayList<>(capacity);
            for (int j = 0; j < capacity; j++) {
                arraylist.add(random.nextInt(1, 1000));
            }
            tests.add(arraylist);
        }
        try {
            BufferedWriter file = new BufferedWriter(new FileWriter("test.txt"));
            for (ArrayList<Integer> i : tests) {
                for (int j = 0; j < i.size(); j++) {
                    file.write(i.get(j).toString());
                    if (j != i.size() - 1) {
                        file.write(" ");
                    }
                }
                file.newLine();

            }
            tests.clear();
            file.close();


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}