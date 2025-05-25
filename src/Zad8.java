// Przykład z własnym wyjątkiem w Javie
//
// try - używamy go do bloku kodu w ktorym moga wystapic wyjatki
// catch - pozwala "złapać" i obsłużyć ten błąd, aby program się nie zatrzymał, tak jakby przechwytuje go
// finally - finally wykonuje sie zawsze bez wzgledu na obecnosc bledu czy tez jego brak
//



public class Zad8 {
    public static void main(String[] args) {
        try {
            zjedzPizza("widelcem"); // Prosze zmienić na "rękami" i zobaczyć co sie stanie ;)
        } catch (ZleJedzeniePizzyException e) {
            System.out.println("Złapano wyjątek: " + e.getMessage());
        } finally {
            System.out.println("Obiad zakończony.");
        }
    }

    public static void zjedzPizza(String sposob) throws ZleJedzeniePizzyException {
        if (!sposob.equalsIgnoreCase("rękami")) {
            throw new ZleJedzeniePizzyException("Nie wolno jeść pizzy " + sposob + "! Jedz rękami!");
        } else {
            System.out.println("Brawo! Pizza została zjedzona prawidłowo ;)");
        }
    }
}

// Własny wyjątek reprezentujący kulinarne przewinienie
class ZleJedzeniePizzyException extends Exception {
    public ZleJedzeniePizzyException(String komunikat) {
        super(komunikat);
    }
}
