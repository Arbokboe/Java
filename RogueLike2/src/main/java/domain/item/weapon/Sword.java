package domain.item.weapon;

import domain.Coordinate;
import domain.item.Item;

public class Sword extends Item {

    public Sword() {
        super(null, false);
    }

    public Sword(Coordinate coordinate, boolean visibility) {
        super(coordinate, visibility);
    }

    public String getType(){
        return "sword";
    }

}
