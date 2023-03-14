import java.util.Random;
public class HumansGoblins {


    class Land {
        private char symbol = '\u25A1';

        public String toString() {
            return String.valueOf(symbol);
        }
    }

    class Goblin {
        private char symbol = '\u2620';

        public String toString() {
            return String.valueOf(symbol);
        }
    }

    class Human {
        private char symbol = '\u2642';
        private int health = 100;
        private int damage = 10;

        public String toString() {
            return String.valueOf(symbol);
        }
    }
}
