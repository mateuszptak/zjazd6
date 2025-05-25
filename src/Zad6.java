import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Zad6 {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Podaj adresy URL jako argumenty programu.");
            return;
        }

        zapiszStroneWWW(args);
    }

    private static void zapiszStroneWWW(String[] strony) {
        for (var strona : strony) {
            try {
                // Tworzenie połączenia z podanym URL
                URL url = new URL(strona);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                // Pobieranie i zapisywanie zawartości strony
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                     BufferedWriter writer = new BufferedWriter(new FileWriter("nazwaPliku.html"))) {

                    String linia;
                    while ((linia = reader.readLine()) != null) {
                        writer.write(linia);
                        writer.newLine();
                    }

                    // System.out.println("Zapisano: " + nazwaPliku);
                } catch (IOException e) {
                    System.out.println("Błąd podczas zapisu: " + e.getMessage());
                }

            } catch (MalformedURLException e) {
                System.out.println("Nieprawidłowy adres URL: " + strona);
            } catch (IOException e) {
                System.out.println("Błąd podczas pobierania strony: " + strona);
            }
        }
        System.out.println("Próba udana");

    }
}
