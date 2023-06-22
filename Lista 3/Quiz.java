package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Quiz implements Serializable {

    public static List<Wynik> wyniki;
    public static Frame frame;
    static {
        wyniki = new ArrayList<Wynik>();
        DataInputStream stream = null;
        try {
            stream = new DataInputStream(new FileInputStream("wyniki.data"));
            String name = null;
            int wynik = 0;
            do  {
                name = stream.readUTF();
                wynik = stream.readInt();
                wyniki.add(new Wynik(name,wynik));
            } while ( stream.available() > 0);


        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        try {
            assert stream != null;
            stream.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    int pkt;
    int i;
    List<Pytanie> tab;
    String identyfikator;
    public Quiz() {
        frame = new Frame();
        identyfikator = (String)JOptionPane.showInputDialog(
                frame,"Wpisz identyfikator",
                "ID",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "");

        if (identyfikator.equals("admin")) {
            AdminPanel admin = new AdminPanel();
            return;
        }
        tab = Load.returnList();
        i=0;
        pkt = 0;
    }

    public void quiz() {

        List<Integer> poprawneOdpowiedzi = new ArrayList<>();
        List<String> niepoprawneOdpowiedzi = new ArrayList<>();
        List<Integer> udzieloneOdpowiedzi = new ArrayList<>();

        for (; i < tab.size(); i++) {
            try {
                ObjectOutputStream strumien = new ObjectOutputStream(new FileOutputStream("data.bak"));
                strumien.writeObject(this);
                strumien.close();
            } catch (Exception exception) {

            }
            int n = JOptionPane.showOptionDialog(frame,
                    tab.get(i).pytanie,
                    "pytanie nr " + tab.get(i).numer,
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    tab.get(i).odpowiedzi, 0);
            var b = Integer.parseInt(tab.get(i).poprawna);

            if (n == b) {
                pkt++;
                poprawneOdpowiedzi.add(Integer.parseInt(tab.get(i).numer));
            } else {
                niepoprawneOdpowiedzi.add(tab.get(i).numer);
            }

            udzieloneOdpowiedzi.add(n);
        }
        StringBuilder builder = new StringBuilder();
        builder.append("Zdobyłeś ").append(pkt).append(" punktów").append("\n\n");
        builder.append("Poprawne odpowiedzi:\n");
        for (Integer numer : poprawneOdpowiedzi) {
            for (Pytanie pytanie : tab) {
                if (Integer.parseInt(pytanie.numer) == numer) {
                    builder.append("Pytanie: ").append(pytanie.pytanie).append("\n");
                    builder.append("Poprawna odpowiedź: ").append(pytanie.odpowiedzi[Integer.parseInt(pytanie.poprawna)]).append("\n\n");
                    break;
                }
            }
        }
        builder.append("Niepoprawne odpowiedzi:\n");
        for (int j = 0; j < niepoprawneOdpowiedzi.size(); j++) {
            String numer = niepoprawneOdpowiedzi.get(j);
            int udzielonaOdpowiedz = udzieloneOdpowiedzi.get(j);
            for (Pytanie pytanie : tab) {
                if (pytanie.numer.equals(numer)) {
                    builder.append("Pytanie: ").append(pytanie.pytanie).append("\n");
                    builder.append("Udzielona odpowiedź: ").append(pytanie.odpowiedzi[udzielonaOdpowiedz]).append("\n");
                    builder.append("Poprawna odpowiedź: ").append(pytanie.odpowiedzi[Integer.parseInt(pytanie.poprawna)]).append("\n\n");
                    break;
                }
            }
        }

        JOptionPane.showMessageDialog(frame, builder.toString());

        try {
            new File("data.bak").delete();
            DataOutputStream stream = null;
            try {
                stream = new DataOutputStream(new FileOutputStream("wyniki.data"));
            } catch (Exception exception) {

            }
            assert stream != null;
            wyniki.add(new Wynik(identyfikator, pkt));
            for (int j = 0; j < wyniki.size(); j++) {
                stream.writeUTF(wyniki.get(j).nazwa);
                stream.writeInt(wyniki.get(j).iloscPkt);
            }
            stream.close();
        } catch (Exception e) {

        }
    }



}

