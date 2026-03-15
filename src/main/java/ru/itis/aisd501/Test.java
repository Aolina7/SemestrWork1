package ru.itis.aisd501;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test {
    public void create() {
        List<List<Integer>> test = new ArrayList<>(100);
        Random random = new Random();

        //добавление списков разной вместимости
        for (int i = 0; i < 100; i++) {
            int capacity = random.nextInt(100, 10000);
            List<Integer> lst = new ArrayList<>(capacity);
            for (int j = 0; j < capacity; j++) {
                lst.add(random.nextInt(1, 1000));
            }
            test.add(lst);
        }

        //добаление данных в файл
        try {
            BufferedWriter file = new BufferedWriter(new FileWriter("test.txt"));
            for (List<Integer> i : test) {
                for (int j = 0; j < i.size(); j++) {
                    file.write(i.get(j).toString());
                    if (j != i.size() - 1) {
                        file.write(" ");
                    }
                }
                file.newLine();

            }
            test.clear();
            file.close();


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}