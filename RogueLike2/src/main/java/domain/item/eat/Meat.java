package domain.item.eat;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import domain.Coordinate;
import domain.item.Item;

public class Meat extends Item {

    public Meat() {
        super(null, false);
    }

    public Meat(Coordinate coordinate, boolean visibility) {
        super(coordinate, visibility);
    }

    public String getType() {
        return "meat";
    }

}
