package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.cert.PolicyQualifierInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Load {
    public Load() {

    }

    public static List<Pytanie> returnList() {
        Random r = new Random();
        try {
            List<Pytanie> tab = new ArrayList<Pytanie>();
            FileReader fileReader = new FileReader("pytania.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();

            while ((line = bufferedReader.readLine()) != null) {
                String[] question = line.split(";");
                String[] a = {question[2],question[3],
                        question[4],question[5]};
                question[6] = String.valueOf(Integer.parseInt(question[6])-1);
                for (int i = 0; i < 8; i++) {
                    int pierwszePytanie = r.nextInt(4);
                    int drugiePytanie = r.nextInt(4);
                    if (pierwszePytanie == drugiePytanie) {
                        continue;
                    }
                    if (pierwszePytanie == Integer.parseInt(question[6])) {
                     question[6] = String.valueOf(drugiePytanie);
                    }
                    else if (drugiePytanie == Integer.parseInt(question[6])) {
                        question[6] = String.valueOf(pierwszePytanie);
                    }
                    var temp = a[pierwszePytanie];
                    a[pierwszePytanie] = a[drugiePytanie];
                    a[drugiePytanie] = temp;
                }

                tab.add(new Pytanie(question[0],question[1],a,question[6]));
            }

            for (int i = 0; i < tab.size()*2; i++) {
                var pierwszePytanie = r.nextInt(tab.size());
                var drugiePytanie = r.nextInt(tab.size());
                if (pierwszePytanie == drugiePytanie) {
                    continue;
                }
                var temp = tab.get(pierwszePytanie);
                tab.set(pierwszePytanie, tab.get(drugiePytanie));
                tab.set(drugiePytanie, temp);
            }
            return tab;
        } catch (IOException exception) {
            return new ArrayList<Pytanie>();

        }
    }
}
