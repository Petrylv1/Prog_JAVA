package com.company;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Quiz q;
        FileReader fileReader = new FileReader("pytania.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = bufferedReader.readLine();
        if (line.equals("0")) {
            q = new Quiz();
        }
        else {
            try {
                ObjectInputStream input = new ObjectInputStream(new FileInputStream("data.bak"));
                q = (Quiz)input.readObject();
                input.close();
            } catch (Exception e) {
                q = new Quiz();
            }
        }
        q.quiz();
    }
}
