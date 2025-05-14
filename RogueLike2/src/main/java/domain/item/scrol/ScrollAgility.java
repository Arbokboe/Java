package domain.item.scrol;

import domain.Coordinate;
import domain.item.Item;

public class ScrollAgility extends Item {

    public ScrollAgility() {
        super(null, false);
    }
    public ScrollAgility(Coordinate coordinate, boolean visibility) {
        super(coordinate, visibility);
    }

    public String getType() {
        return "scroll agility";
    }

}
