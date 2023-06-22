package com.company;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.RandomAccessFile;

public class AdminPanel {

    public AdminPanel() {
        StringBuilder odp = new StringBuilder();
        for (var item :
                Quiz.wyniki) {
            odp.append(item.nazwa).append(" pkt:").append(item.iloscPkt).append("\n");
        }

        JOptionPane.showMessageDialog(Quiz.frame, odp);

    }
}
