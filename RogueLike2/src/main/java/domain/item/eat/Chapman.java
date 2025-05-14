package domain.item.eat;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import domain.Coordinate;
import domain.item.Item;


public class Chapman extends Item {
    public Chapman() {
        super(null, false);
    }

    public Chapman(Coordinate coordinate, boolean visibility) {
        super(coordinate, visibility);
    }

    public String getType() {
        return "chapman";
    }
}



