package domain.item.scrol;

import domain.Coordinate;
import domain.item.Item;

public class ScrollStrength extends Item {

    public ScrollStrength() {
        super(null, false);
    }

    public ScrollStrength(Coordinate coordinate, boolean visibility) {
        super(coordinate, visibility);
    }

    public String getType() {
        return "scroll strength";
    }

}
