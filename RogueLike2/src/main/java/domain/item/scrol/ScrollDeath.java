package domain.item.scrol;

import domain.Coordinate;
import domain.item.Item;

public class ScrollDeath extends Item {

    public ScrollDeath() {
        super(null, false);
    }

    public ScrollDeath(Coordinate coordinate, boolean visibility) {
        super(coordinate, visibility);
    }

    public String getType() {
        return "scroll death";
    }

}
