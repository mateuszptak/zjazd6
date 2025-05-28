/*
 ---------  wlasny wyjatek warto pisac gdy:    -------------

 - chce opisac charakterystyczne bledy dla aplikacji ktore nie sa uwzglednione w standardowych wyjatkach jezyka programowania
 - gdy chce grupowac bledy w jedna kategorie co ulatwia ich obsluge oraz organizacje kody
 - gdy chce dodac dodatkowe informacje diagnostyczne np. kody bledow, lokalizacje problemu itd ktore pomagaja w szybszym zidentyfikowaniu go
 - poprawic testy aplikacji umozliwiajac latwiejsza symulacje pewnych sytuacji

                Przyklad kodu:

 public class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }
}



 ------- Kiedy warto rzucic lub przekazac dalej wyjatek? -------------

 - Rzucamy wyjatek gdy wystapil problem z kontynuowaniem dzialania metody/funkcji
 - Przekazuje gdy nie ma mozliwosci osbluzenia go na danym poziomie ale wyzszy poziom aplikacji moze podjac odpowiednie dzialania np. dać znać uzytkownikowi, zalogowac jakis blad etc
 - rzucam wyjatek w sytuacjiach ktore wskazuja na blad logiczny w plikacji, nieoczekiwane warunki, a nie w sytuacjach przewidywalnych takich jak np. bledne wejsciowe ktore powinny byc walidowane wczesniej

 przyklad?

 Jezeli funkcja przetwarzajaca dane otrzyma ich niepoprawny format moze rzucic wyjatkiem ktory bedzie przechwycony na wyzszym poziomie apki ktory zostanie wyswietlony uzytkownikowi


 ------- Kiedy obsluzyc wyjatek? -------

 - gdy chce zapobiec przerwaniu dzialania programu i zapewnic jego stabilnosc poprzez odpowiednia reakcje na blad
 - w celu logowania bledow i monitorowania aplikacji aby ulatwic pozniejsze debugowanie i analize tego problemu
 - w miejscu gdzie jest mi znany kontekst i moge poprawienia zareagowac na ten blad np przy odczycie pliku gdy plik nie istnieje to moge wyswietlic komunikat o tym i zakonczyc dana operacje bez wylaczania sie programu

 przyklad:

 try {
    processor.process(null);
} catch (InvalidInputException e) {
    System.out.println("Niepoprawne dane wejściowe: " + e.getMessage());
    // np. poprosić użytkownika o ponowne wprowadzenie danych
}

*/
