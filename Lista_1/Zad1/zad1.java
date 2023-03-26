import java.util.Scanner;

public class zad1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Podaj ilosc przedmiotow: ");
        int liczbaPrzedmiotow = scanner.nextInt();

        System.out.print("Podaj ilosc ocen do wprowadzenia: ");
        int iloscOcen = scanner.nextInt();

        int[][] oceny = new int[liczbaPrzedmiotow][iloscOcen];
        int sumaOcen = 0;

        for (int i = 0; i < liczbaPrzedmiotow; i++) {
            for (int j = 0; j < iloscOcen; j++) {
                int ocena;
                do {
                    System.out.print("Podaj ocene " + (j+1) + " z przedmiotu " + (i+1) + ": ");
                    ocena = scanner.nextInt();
                    if (ocena < 2 || ocena > 5) {
                        System.out.println("Ocena musi byc w skali 2 - 5.");
                    }
                } while (ocena < 2 || ocena > 5);

                oceny[i][j] = ocena;
                sumaOcen += oceny[i][j];
            }
        }

        int iloscWszystkichOcen = iloscOcen * liczbaPrzedmiotow;
        double srednia = (double) sumaOcen / iloscWszystkichOcen;
        System.out.println("Twoja srednia wynosi: " + srednia);

        if (srednia > 4.1) {
            System.out.println("Kwalifikujesz sie do stypendium.");
        } else {
            System.out.println("Nie kwalifikujesz sie do stypendium.");
        }
    }
}