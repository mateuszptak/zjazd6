import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;
import java.util.Scanner;

public class Zad3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj ścieżki do plików, oddzielając je spacją:");
        String liniaWejscia = scanner.nextLine();
        String[] sciezki = liniaWejscia.split("\\s+");

        // Ścieżka do katalogu i pliku wynikowego
        Path katalogDocelowy = Paths.get("src/files/zapisy");
        Path plikWynikowy = katalogDocelowy.resolve("wynik_nio.txt");

        try {
            // Tworzymy katalog jeśli nie istnieje
            if (!Files.exists(katalogDocelowy)) {
                Files.createDirectories(katalogDocelowy);
            }

            // Tworzymy (lub nadpisujemy) pusty plik wynikowy
            Files.writeString(plikWynikowy, "", StandardCharsets.UTF_8);

            for (String sciezka : sciezki) {
                Path sciezkaPliku = Paths.get(sciezka);
                String naglowek = "----- Zawartość pliku: " + sciezka + " -----";
                System.out.println(naglowek);
                Files.writeString(plikWynikowy, naglowek + System.lineSeparator(), StandardCharsets.UTF_8, StandardOpenOption.APPEND);

                try {
                    List<String> linie = Files.readAllLines(sciezkaPliku, StandardCharsets.UTF_8);
                    for (String linia : linie) {
                        System.out.println(linia);
                        Files.writeString(plikWynikowy, linia + System.lineSeparator(), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
                    }
                } catch (NoSuchFileException e) {
                    String blad = "Nie znaleziono pliku: " + sciezka;
                    System.out.println(blad);
                    Files.writeString(plikWynikowy, blad + System.lineSeparator(), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
                } catch (IOException e) {
                    String blad = "Błąd podczas odczytu pliku: " + sciezka;
                    System.out.println(blad);
                    Files.writeString(plikWynikowy, blad + System.lineSeparator(), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
                }

                System.out.println();
                Files.writeString(plikWynikowy, System.lineSeparator(), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
            }

            System.out.println("Zawartość została zapisana do pliku: " + plikWynikowy.toString());

        } catch (IOException e) {
            System.out.println("Błąd podczas tworzenia katalogu lub zapisu do pliku wynikowego: " + e.getMessage());
        }

        scanner.close();
    }
}
