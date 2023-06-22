package com.company;

import java.io.Serializable;

public class Pytanie implements Serializable {
    public String numer;
    public String pytanie;
    public String[] odpowiedzi;
    public String poprawna;

    public Pytanie(String numer, String pytanie, String[] odpowiedzi, String poprawna) {
        this.numer = numer;
        this.pytanie = pytanie;
        this.odpowiedzi = odpowiedzi;
        this.poprawna = poprawna;
    }
}
