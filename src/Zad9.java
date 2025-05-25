// wlasne wyjatki uzywamy gdy chce reprezentowac unikalne bledy ktore nie sa ujete
// standardowymi wyjatkami lub kiedy chce dodac wiecej kontekstu do bledu niz ten standardowy

//          class BrakSrodkowException extends Exception {
//              public BrakSrodkowException(String komunikat) {
//                  super(komunikat);
//              }
//}

// Kiedy warto rzucać wyjątek - throw?
//Rzucamy wyjątek wtedy, gdy coś poszło nie tak i to nie ta metoda ma to naprawić, tylko ktoś wyżej (np. inna klasa,
// użytkownik).
// przyklad
//                  if (kwota > saldo) {
//                      throw new BrakSrodkowException("Nie masz wystarczającej ilości pieniędzy.");
//                  }


//Kiedy warto przekazać wyjątek dalej - throws?
//Gdy metoda może się wywalić, ale to nie jej problem, tylko ktoś wyżej powinien się tym zająć.
// Przykład:
//          public void wyplac(double kwota) throws BrakSrodkowException {
//          rzucamy wyjątek, ale nie obsługujemy go tutaj
//          }


// Kiedy warto obsłużyć wyjątek - try - catch?
// Używamy kiedy wiemy, co zrobić, jeśli coś się nie uda (np. wypisać komunikat, użyć wartości domyślnej),
// nie chcemy, żeby program się wywalił, chcemy coś posprzątać np. zamknąć plik, połączenie
//
//        Przykład:
//          try {
//                   konto.wyplac(500);
//          } catch (BrakSrodkowException e) {
//                   System.out.println("Błąd: " + e.getMessage());
//                   }