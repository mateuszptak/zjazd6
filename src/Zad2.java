import java.io.*;
import java.util.Scanner;

public class Zad2 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj ścieżki do plików, oddzielając je spacją:");
        String liniaWejscia = scanner.nextLine();

        String[] sciezki = liniaWejscia.split("\\s+"); // podział po spacjach

        // Ścieżka do katalogu i pliku wynikowego
        String katalogDocelowy = "src/files/zapisy";
        String nazwaPlikuWynikowego = katalogDocelowy + "/wynik.txt";

        // Upewniamy się, że katalog istnieje – jak nie, to tworzymy
        File katalog = new File(katalogDocelowy);
        if (!katalog.exists()) {
            katalog.mkdirs(); // tworzy katalog i nadrzędne jeśli trzeba
        }

        // Tworzymy writer do pliku wynikowego
        try (BufferedWriter zapis = new BufferedWriter(new FileWriter(nazwaPlikuWynikowego))) {

            for (String sciezka : sciezki) {
                String naglowek = "----- Zawartość pliku: " + sciezka + " -----";
                System.out.println(naglowek);
                zapis.write(naglowek);
                zapis.newLine();

                try (BufferedReader reader = new BufferedReader(new FileReader(sciezka))) {
                    String linia;
                    while ((linia = reader.readLine()) != null) {
                        System.out.println(linia);      // wypisuje na ekran
                        zapis.write(linia);            // zapisuje do pliku
                        zapis.newLine();               // dodaje nową linię
                    }
                } catch (FileNotFoundException e) {
                    String blad = "Nie znaleziono pliku: " + sciezka;
                    System.out.println(blad);
                    zapis.write(blad);
                    zapis.newLine();
                } catch (IOException e) {
                    String blad = "Błąd podczas odczytu pliku: " + sciezka;
                    System.out.println(blad);
                    zapis.write(blad);
                    zapis.newLine();
                }

                zapis.newLine(); // pusty wiersz między plikami
                System.out.println();
            }

            System.out.println("Zawartość została zapisana do pliku: " + nazwaPlikuWynikowego);

        } catch (IOException e) {
            System.out.println("Błąd podczas zapisu do pliku wynikowego: " + e.getMessage());
        }

        scanner.close();
    }
}
