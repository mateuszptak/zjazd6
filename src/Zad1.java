import java.io.*;
import java.util.Scanner;

public class Zad1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj ścieżki do plików, oddzielając je spacją:");
        String liniaWejscia = scanner.nextLine(); // odczytujemy linię ze ścieżkami

        String[] sciezki = liniaWejscia.split("\\s+"); // dzielimy po spacjach

        for (String sciezka : sciezki) {
            System.out.println("----- Zawartość pliku: " + sciezka + " -----");

            // Próbujemy otworzyć plik i go odczytać
            try (BufferedReader reader = new BufferedReader(new FileReader(sciezka))) {
                String linia;
                while ((linia = reader.readLine()) != null) {
                    System.out.println(linia); // wypisujemy każdą linię z pliku
                }
            } catch (FileNotFoundException e) {
                System.out.println("Nie znaleziono pliku: " + sciezka);
            } catch (IOException e) {
                System.out.println("Błąd podczas odczytu pliku: " + sciezka);
            }

            System.out.println();
        }

        scanner.close();
    }
}
