import java.util.Random;

public class Snake extends Monster {

    public Snake() {
        super(4, "Snake", generateRandomDamage(), 12, 5);
    }

    private static int generateRandomDamage() {
        Random rand = new Random();
        return rand.nextInt(3) + 3;
    }

    public int randomDamage(int damage) {
        damage = (int) (Math.random() * 3) + 3;
        return damage;
    }
}
