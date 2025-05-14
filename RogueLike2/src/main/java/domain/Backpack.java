package domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import domain.item.Item;

import java.util.ArrayList;
import java.util.List;


public class Backpack {

    private List<Item> elixirs = new ArrayList<>(8);
    private List<Item> scrolls = new ArrayList<>(8);
    private List<Item> weapons = new ArrayList<>(8);
    private List<Item> eats = new ArrayList<>(8);

    private int currentItem;
    private int iter = 0;
    private BackpackState state = BackpackState.SHOW_BACKPACK;

    public enum BackpackState {
        SHOW_DIALOG_MENU_FOR_ITEM, SHOW_BACKPACK
    }

    Backpack() {

    }

    public void nextIter() {
        if (iter < 3) {
            iter += 1;
        }
    }

    public void backIter() {
        if (iter > 0) {
            iter -= 1;
        }
    }

    public void clearIter() {
        iter = 0;
    }


    public void addElixir(Item item) {
        elixirs.add(item);
    }

    public void addScroll(Item item) {
        scrolls.add(item);
    }

    public void addWeapon(Item item) {
        weapons.add(item);
    }

    public void addEat(Item item) {
        eats.add(item);
    }

    //GET

    @JsonIgnore
    public String getBackpackType() {
        return switch (iter) {
            case 0 -> "Эликсиры";
            case 1 -> "Свитки";
            case 2 -> "Оружие";
            case 3 -> "Еда";
            default -> "none";
        };
    }

    @JsonIgnore
    public List<Item> getItems() {
        switch (iter) {
            case 0 -> {
                return elixirs;
            }
            case 1 -> {
                return scrolls;
            }
            case 2 -> {
                return weapons;
            }
            case 3 -> {
                return eats;
            }
            default -> {
                return null;
            }
        }
    }

    @JsonIgnore
    public int getIter() {
        return iter;
    }

    public List<Item> getElixir() {
        return elixirs;
    }

    public List<Item> getScroll() {
        return scrolls;
    }

    public List<Item> getWeapons() {
        return weapons;
    }

    public List<Item> getEat() {
        return eats;
    }

    @JsonIgnore
    public BackpackState getState() {
        return state;
    }

    @JsonIgnore
    public int getCurrentItem() {
        return currentItem;
    }

    //SET
    public void setWeapons(List<Item> weapons) {
        this.weapons = weapons;
    }

    public void setEats(List<Item> eats) {
        this.eats = eats;
    }

    public void setScrolls(List<Item> scrolls) {
        this.scrolls = scrolls;
    }

    public void setElixir(List<Item> elixirs) {
        this.elixirs = elixirs;
    }

    @JsonIgnore
    public void setState(BackpackState state) {
        this.state = state;
    }

    @JsonIgnore
    public void setCurrentItem(int itemIndex) {
        currentItem = itemIndex;
    }
}
