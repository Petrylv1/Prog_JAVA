import java.util.Scanner;

public class zad2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Podaj n: ");
        int n = scanner.nextInt();

        int silnia = 1;
        for (int i = 1; i <= n; i++) {
            silnia *= i;
        }
        System.out.println("Wartosc n!: " + silnia);

        double suma = 0.0;
        for (int i = 1; i <= n; i++) {
            double wyraz = 1.0 / (i + n);
            if (i % 2 == 0) {
                wyraz = -wyraz;
            }
            suma += wyraz;
        }
        System.out.println("Suma ciagu: " + suma);
    }
}
