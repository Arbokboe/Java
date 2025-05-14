package domain.item.elixir;

import domain.Coordinate;
import domain.item.Item;

public class ElixirRed extends Item {

    public ElixirRed() {
        super(null, false);
    }

    public ElixirRed(Coordinate coordinate, boolean visibility) {
        super(coordinate, visibility);
    }

    public String getType() {
        return "red elixir";
    }

}
