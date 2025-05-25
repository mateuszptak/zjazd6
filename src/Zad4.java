import java.io.*;
import java.nio.file.*;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zad4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj ścieżki do plików do spakowania, oddzielając je spacją:");
        String liniaWejscia = scanner.nextLine();
        String[] sciezki = liniaWejscia.split("\\s+");

        // Ścieżka do katalogu docelowego i pliku ZIP
        Path katalogDocelowy = Paths.get("src/files/zapakowane");
        Path plikZip = katalogDocelowy.resolve("archiwum_io.zip");

        // Tworzenie katalogu jeśli nie istnieje
        try {
            if (!Files.exists(katalogDocelowy)) {
                Files.createDirectories(katalogDocelowy);
            }

            // Tworzymy strumień ZIP
            try (ZipOutputStream zipOut = new ZipOutputStream(Files.newOutputStream(plikZip))) {

                for (String sciezka : sciezki) {
                    Path plik = Paths.get(sciezka);
                    if (Files.exists(plik) && Files.isRegularFile(plik)) {
                        try (InputStream in = Files.newInputStream(plik)) {
                            ZipEntry entry = new ZipEntry(plik.getFileName().toString());
                            zipOut.putNextEntry(entry);

                            byte[] buf = new byte[1024];
                            int len;
                            while ((len = in.read(buf)) > 0) {
                                zipOut.write(buf, 0, len);
                            }

                            zipOut.closeEntry();
                            System.out.println("Dodano do archiwum: " + plik);
                        }
                    } else {
                        System.out.println("Nieprawidłowa ścieżka pliku: " + sciezka);
                    }
                }

                System.out.println("Zakończono pakowanie. Plik ZIP: " + plikZip.toString());

            } catch (IOException e) {
                System.out.println("Błąd podczas tworzenia pliku ZIP: " + e.getMessage());
            }

        } catch (IOException e) {
            System.out.println("Błąd podczas tworzenia katalogu: " + e.getMessage());
        }

        scanner.close();
    }
}
