package domain.item.elixir;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import domain.Coordinate;
import domain.item.Item;

public class ElixirBlue extends Item {

    public ElixirBlue() {
        super(null, false);
    }

    public ElixirBlue(Coordinate coordinate, boolean visibility) {
        super(coordinate, visibility);
    }

    public String getType() {
        return "blue elixir";
    }

}
