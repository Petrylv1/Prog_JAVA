import java.util.Scanner;

public class zad1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Podaj ilosc ocen do wprowadzenia: ");
        int iloscOcen = scanner.nextInt();
        
        int sumaOcen = 0;
        for (int i = 1; i <= iloscOcen; i++) {
            System.out.print("Podaj ocene z przedmiotu " + i + ": ");
            int ocena = scanner.nextInt();
            sumaOcen += ocena;
        }
        
        double srednia = (double) sumaOcen / iloscOcen;
        System.out.println("Twoja srednia wynosi: " + srednia);
        
        if (srednia > 4.1) {
            System.out.println("Kwalifikujesz sie do stypendium.");
        } else {
            System.out.println("Nie kwalifikujesz sie do stypendium.");
        }
    }
}
