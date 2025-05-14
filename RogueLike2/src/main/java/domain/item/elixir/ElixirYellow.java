package domain.item.elixir;

import domain.Coordinate;
import domain.item.Item;

public class ElixirYellow extends Item {

    public ElixirYellow() {
        super(null, false);
    }

    public ElixirYellow(Coordinate coordinate, boolean visibility) {
        super(coordinate, visibility);
    }

    public String getType() {
        return "yellow elixir";
    }

}
