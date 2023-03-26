import java.io.*;

class UTWOR {
    String autor;
    String wykonawca;
    int czasTrwania;

    UTWOR(String autor, String wykonawca, int czasTrwania) {
        this.autor = autor;
        this.wykonawca = wykonawca;
        this.czasTrwania = czasTrwania;
    }
}

class CD {
    String tytul;
    String wykonawca;
    String wydawca;
    int rokWydania;
    double cena;
    UTWOR[] utwory = new UTWOR[10];
    int liczbaUtworow = 0;

    CD(String tytul, String wykonawca, String wydawca, int rokWydania, double cena) {
        this.tytul = tytul;
        this.wykonawca = wykonawca;
        this.wydawca = wydawca;
        this.rokWydania = rokWydania;
        this.cena = cena;
    }

    void dodajUtwor(String autor, String wykonawca, int czasTrwania) {
        if (liczbaUtworow < 10) {
            utwory[liczbaUtworow++] = new UTWOR(autor, wykonawca, czasTrwania);
        }
    }

    void wyswietlUtwory() {
        for (int i = 0; i < liczbaUtworow; i++) {
            UTWOR utwor = utwory[i];
            System.out.println("Autor: " + utwor.autor);
            System.out.println("Wykonawca: " + utwor.wykonawca);
            System.out.println("Czas trwania: " + utwor.czasTrwania);
        }
    }

    void wyswietlInformacje() {
        System.out.println("Tytul albumu: " + tytul);
        System.out.println("Nazwisko wykonawcy: " + wykonawca);
        System.out.println("Nazwa wydawcy: " + wydawca);
        System.out.println("Rok wydania: " + rokWydania);
        System.out.println("Cena plyty: " + cena);
    }
}

class KOLEKCJA_PLYT {
    CD[] plyty = new CD[10];
    int liczbaPlyt = 0;

    void dodajPlyte(String tytul, String wykonawca, String wydawca, int rokWydania, double cena) {
        if (liczbaPlyt < 10) {
            plyty[liczbaPlyt++] = new CD(tytul, wykonawca, wydawca, rokWydania, cena);
        }
    }

    void wyswietlPlyty() {
        for (int i = 0; i < liczbaPlyt; i++) {
            CD plyta = plyty[i];
            System.out.println("Plyta #" + (i+1));
            plyta.wyswietlInformacje();
            System.out.println("-----------------------------");
            System.out.println("Lista utworow:");
            System.out.println("-----------------------------");
            plyta.wyswietlUtwory();
            System.out.println("-----------------------------");
        }
    }
}

public class Zad3 {
    public static void main(String[] args){
        KOLEKCJA_PLYT kolekcja = new KOLEKCJA_PLYT();
    
        
        kolekcja.dodajPlyte("Jeden", "Bibu", "Studio Code", 1973, 65.40);
        kolekcja.dodajPlyte("Dwa", "Bobo", "Studio Community", 1969, 41.15);
        kolekcja.dodajPlyte("Trzy", "Bubu", "Studio Code Records", 1980, 45.20);
    
        
        CD plyta1 = kolekcja.plyty[0];
        plyta1.dodajUtwor("Bibu", "Bibu", 433);
        plyta1.dodajUtwor("Bibu", "Bibu", 420);
        plyta1.dodajUtwor("Bobo", "Bibu", 530);

        CD plyta2 = kolekcja.plyty[1];
        plyta2.dodajUtwor("Toto", "Zyzz", 433);
        plyta2.dodajUtwor("Cartman", "Rock", 356);
        plyta2.dodajUtwor("Bagiet", "Bobo", 312);

        CD plyta3 = kolekcja.plyty[2];
        plyta3.dodajUtwor("Samson", "Bubu", 312);
        plyta3.dodajUtwor("Petryl", "Bubu", 356);
        plyta3.dodajUtwor("Sheeper", "Bubu", 433);
    
        
        kolekcja.wyswietlPlyty();
    }
}