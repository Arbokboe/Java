package domain.item.weapon;

import domain.Coordinate;
import domain.item.Item;

public class Axe extends Item {

    public Axe() {
        super(null, false);
    }

    public Axe(Coordinate coordinate, boolean visibility) {
        super(coordinate, visibility);
    }

    public String getType(){
        return "axe";
    }

}
