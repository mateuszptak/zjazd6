import java.io.*;
import java.nio.file.*;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Zad5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj ścieżki do plików ZIP do rozpakowania, oddzielając je spacją:");
        String liniaWejscia = scanner.nextLine();
        String[] sciezki = liniaWejscia.split("\\s+");

        Path katalogDocelowy = Paths.get("src/files/wypakowane");

        for (String sciezkaZip : sciezki) {
            Path plikZip = Paths.get(sciezkaZip);

            if (!Files.exists(plikZip) || !plikZip.toString().endsWith(".zip")) {
                System.out.println("Nieprawidłowy plik ZIP: " + sciezkaZip);
                continue;
            }

            String nazwaBezRozszerzenia = plikZip.getFileName().toString().replaceFirst("[.][zZ][iI][pP]$", "");
            Path katalogRozpakowania = katalogDocelowy.resolve(nazwaBezRozszerzenia);

            try {
                Files.createDirectories(katalogRozpakowania);

                try (ZipInputStream zipIn = new ZipInputStream(Files.newInputStream(plikZip))) {
                    ZipEntry entry;

                    while ((entry = zipIn.getNextEntry()) != null) {
                        Path plikDocelowy = katalogRozpakowania.resolve(entry.getName());

                        if (entry.isDirectory()) {
                            Files.createDirectories(plikDocelowy);
                        } else {
                            Files.createDirectories(plikDocelowy.getParent());

                            try (OutputStream out = Files.newOutputStream(plikDocelowy)) {
                                byte[] buf = new byte[1024];
                                int len;
                                while ((len = zipIn.read(buf)) > 0) {
                                    out.write(buf, 0, len);
                                }
                            }
                        }

                        zipIn.closeEntry();
                        System.out.println("Wypakowano: " + plikDocelowy);
                    }

                }

                System.out.println("Rozpakowano archiwum: " + plikZip.toString());

            } catch (IOException e) {
                System.out.println("Błąd przy rozpakowywaniu: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
