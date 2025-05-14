package domain.item.elixir;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import domain.Coordinate;
import domain.item.Item;

public class ElixirGreen extends Item {

    public ElixirGreen() {
        super(null, false);
    }

    public ElixirGreen(Coordinate coordinate, boolean visibility) {
        super(coordinate, visibility);
    }

    public String getType() {
        return "green elixir";
    }

}
