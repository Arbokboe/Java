package domain.item;

import com.fasterxml.jackson.annotation.*;
import domain.Coordinate;
import domain.Point;
import domain.item.weapon.*;
import domain.item.coin.*;
import domain.item.eat.*;
import domain.item.elixir.*;
import domain.item.scrol.*;

@JsonIgnoreProperties(ignoreUnknown = true)

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Sword.class, name = "sword"),
        @JsonSubTypes.Type(value = Axe.class, name = "axe"),
        @JsonSubTypes.Type(value = Coin.class, name = "coin"),
        @JsonSubTypes.Type(value = Chapman.class, name = "chapman"),
        @JsonSubTypes.Type(value = Meat.class, name = "meat"),
        @JsonSubTypes.Type(value = ElixirRed.class, name = "elixirRed"),
        @JsonSubTypes.Type(value = ElixirBlue.class, name = "elixirBlue"),
        @JsonSubTypes.Type(value = ElixirGreen.class, name = "elixirGreen"),
        @JsonSubTypes.Type(value = ElixirYellow.class, name = "elixirYellow"),
        @JsonSubTypes.Type(value = ScrollAgility.class, name = "scrolAgility"),
        @JsonSubTypes.Type(value = ScrollDeath.class, name = "scrolDeath"),
        @JsonSubTypes.Type(value = ScrollStrength.class, name = "scrolStrength")
})


public abstract class Item {
    private Point point;
    private String type;

    public static final int COIN = 1;
    public static final int CHAPMAN = 2;
    public static final int MEAT = 3;
    public static final int ELIXIRBLUE = 4;
    public static final int ELIXIRGREEN = 5;
    public static final int ELIXIRRED = 6;
    public static final int ELIXIRYELLOW = 7;
    public static final int SCROLDEATH = 8;
    public static final int SCROLAGILITY = 9;
    public static final int SCROLSTRENGTH = 10;
    public static final int AXE = 11;
    public static final int SWORD = 12;

    @JsonCreator
    protected Item(@JsonProperty("coordinate") Coordinate coordinate,
                   @JsonProperty("visibility") boolean visibility) {
        point = new Point(coordinate, visibility);
    }

    public void setVisibility(boolean visibility) {
        this.point.setVisibility(visibility);
    }

    @JsonIgnore
    public String getType() {
        return type;
    }

    public Point getPoint() {
        return point;
    }

}
