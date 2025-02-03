import java.util.*;

class Card {
    private String suit;  // Palo: tréboles, corazones, picas o diamantes.
    private String color; // Color: rojo o negro.
    private String value; // Valor: 2-10, A, J, Q o K.

    public Card(String suit, String color, String value) {
        this.suit = suit;
        this.color = color;
        this.value = value;
    }

    @Override
    public String toString() {
        return suit + "," + color + "," + value;
    }
}

class Deck {
    private List<Card> cards;  // Atributo 1: Lista de cartas (Collection Framework)
    private Set<String> usedCards;  // Atributo 2: Conjunto para rastrear cartas jugadas (Collection Framework)

    public Deck() {
        cards = new ArrayList<>();  // Usamos ArrayList porque permite eliminar elementos fácilmente
        usedCards = new HashSet<>(); // Usamos HashSet para rastrear cartas jugadas

        String[] suits = {"Tréboles", "Corazones", "Picas", "Diamantes"};
        String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "A", "J", "Q", "K"};

        for (String suit : suits) {
            String color = (suit.equals("Corazones") || suit.equals("Diamantes")) ? "Rojo" : "Negro";
            for (String value : values) {
                cards.add(new Card(suit, color, value));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
        System.out.println("Se mezcló el Deck.");
    }

    public void head() {
        if (cards.isEmpty()) {
            System.out.println("No quedan cartas en el Deck.");
            return;
        }
        Card card = cards.remove(0);
        usedCards.add(card.toString());
        System.out.println(card);
        System.out.println("Quedan " + cards.size());
    }

    public void pick() {
        if (cards.isEmpty()) {
            System.out.println("No quedan cartas en el Deck.");
            return;
        }
        int index = new Random().nextInt(cards.size());
        Card card = cards.remove(index);
        usedCards.add(card.toString());
        System.out.println(card);
        System.out.println("Quedan " + cards.size());
    }

    public void hand() {
        if (cards.size() < 5) {
            System.out.println("No hay suficientes cartas en el Deck.");
            return;
        }

        // Lista para almacenar las 5 cartas extraídas
        List<Card> handCards = new ArrayList<>();

        // Extraer 5 cartas y almacenarlas en la lista
        for (int i = 0; i < 5; i++) {
            Card card = cards.remove(0);
            handCards.add(card);
            usedCards.add(card.toString());
        }

        // Imprimir las 5 cartas, cada una en su propia línea
        for (Card card : handCards) {
            System.out.println(card);
        }

        // Imprimir el número de cartas restantes en el deck (solo una vez)
        System.out.println("Quedan " + cards.size());
    }
}

class PokerGame {
    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.shuffle();
        deck.head();
        deck.pick();
        deck.hand();
    }
}