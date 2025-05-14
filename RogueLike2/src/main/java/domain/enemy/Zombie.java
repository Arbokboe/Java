package domain.enemy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import domain.Coordinate;

import java.util.Random;

public class Zombie extends Enemy {
    @JsonIgnore
    private final int BASE_DAMAGE = 10;

    public Zombie() {
        super(new Coordinate(0, 0), "zombie", 'Z', 0, 0, 0, 0, 0, false);
    }

    public Zombie(Coordinate coordinate, String type, char enemySymbol, int agility, int strength,
                  int hostility, int health, int currentRoom, boolean visibility) {
        super(coordinate, type, enemySymbol, agility, strength, hostility, health, currentRoom, visibility);
    }


    public int damageCalc() {
        Random rand = new Random();
        double randModifier = 0.8 * rand.nextDouble() * 0.4;
        double damage = (BASE_DAMAGE * 1.0 * randModifier) * (1 + (strength / 80.0));
        return Math.max(1, (int) damage);
    }
}
