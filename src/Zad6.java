import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Zad6 {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Podaj adresy URL jako argumenty programu.");
            return;
        }

        zapiszStronyWWW(args);
    }

    private static void zapiszStronyWWW(String[] strony) {
        Path katalogDocelowy = Path.of("src/files/pobrane");


        for (String strona : strony) {
            try {
                // Tworzymy URI i konwertujemy na URL, by uniknąć deprecated konstruktora
                URI uri = new URI(strona.startsWith("http") ? strona : "https://" + strona);
                URL url = uri.toURL();

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                // generowanie unikalnej nazwy na podstawie nazwy stronki
                String nazwaPliku = uri.getHost() != null
                        ? uri.getHost().replaceAll("[^a-zA-Z0-9]", "_") + ".html"
                        : "strona.html";

                Path sciezkaPliku = katalogDocelowy.resolve(nazwaPliku);

                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                     BufferedWriter writer = new BufferedWriter(new FileWriter(sciezkaPliku.toFile()))) {

                    String linia;
                    while ((linia = reader.readLine()) != null) {
                        writer.write(linia);
                        writer.newLine();
                    }

                    System.out.println("Zapisano: " + sciezkaPliku);
                } catch (IOException e) {
                    System.out.println("Błąd podczas zapisu: " + e.getMessage());
                }

            } catch (URISyntaxException e) {
                System.out.println("Nieprawidłowy adres URI: " + strona);
            } catch (MalformedURLException e) {
                System.out.println("Nieprawidłowy adres URL: " + strona);
            } catch (IOException e) {
                System.out.println("Błąd podczas pobierania strony: " + strona);
            }
        }

        System.out.println("Pobieranie stron/-y zakończone.");
    }
}
