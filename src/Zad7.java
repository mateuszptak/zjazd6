import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Zad7 {
    public static void main(String[] args) {
        int szerokosc = 800;
        int wysokosc = 800;

        // Tworzenie obrazu
        BufferedImage obraz = new BufferedImage(szerokosc, wysokosc, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = obraz.createGraphics();

        // Wypełnienie tła kolorem pomarańczowym
        g2d.setColor(Color.orange);
        g2d.fillRect(0, 0, szerokosc, wysokosc);

        // Rysowanie czarnego owalu
        int owalX = 100, owalY = 100, owalSzer = 150, owalWys = 100;
        g2d.setColor(Color.black);
        g2d.fillOval(owalX, owalY, owalSzer, owalWys);

        // dodanie tekstu wewnątrz czarnego owalu
        String tekstWewnetrzny = "Mateusz Ptak\n171303";
        Font font = new Font("Arial", Font.BOLD, 16);
        g2d.setFont(font);
        g2d.setColor(Color.WHITE);

        // Dzielimy tekst na linie i rysujemy go wyśrodkowanego w owalu
        String[] linie = tekstWewnetrzny.split("\n");
        FontMetrics fm = g2d.getFontMetrics();
        int tekstWysokosc = fm.getHeight();
        int startY = owalY + (owalWys - (tekstWysokosc * linie.length)) / 2 + fm.getAscent();

        for (String linia : linie) {
            int tekstSzerokosc = fm.stringWidth(linia);
            int startX = owalX + (owalSzer - tekstSzerokosc) / 2;
            g2d.drawString(linia, startX, startY);
            startY += tekstWysokosc;
        }

        // Dodatkowa linia
        g2d.setColor(Color.GREEN);
        g2d.drawLine(200, 200, 400, 400);

        try {
            File folder = new File("src/files/wygenerowane");
            if (!folder.exists()) {
                folder.mkdirs(); // twozry folder jesli nie istnieje
            }

            String losowaNazwa = "obraz_" + (int)(Math.random() * 10000) + ".png";
            File plikObrazu = new File(folder, losowaNazwa);

            ImageIO.write(obraz, "png", plikObrazu);
            System.out.println("Obraz został wygenerowany w: " + plikObrazu.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
