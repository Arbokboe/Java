package view;

import com.googlecode.lanterna.TextColor;
import domain.Hero;
import domain.enemy.Enemy;
import domain.item.Item;

import java.util.ArrayList;
import java.util.List;

public class GameEvent {

    private int maxEventsSize;
    List<TextPart> events;

    public GameEvent(int maxEventsSize) {
        this.maxEventsSize = maxEventsSize;
        events = new ArrayList<>();
    }

    public void addEvent(String action, Object entity) {
        events.addFirst(createTextParts(entity, action));
        resizeIfNecessary();
    }

    public void addColoredMessage(String message, TextColor textColor) {
        List<ColoredText> coloredTexts = new ArrayList<>();
        coloredTexts.add(new ColoredText(message, textColor, TextColor.ANSI.DEFAULT));
        events.addFirst(new TextPart(coloredTexts));
        resizeIfNecessary();
    }

    public void addDamageEvent(Object entity, String action) {
        events.addFirst(createTextParts(entity, action));
        resizeIfNecessary();
    }

    private TextPart createTextParts(Object entity, String action) {
        List<ColoredText> coloredTexts = new ArrayList<>();
        coloredTexts.add(new ColoredText(action, TextColor.ANSI.WHITE, TextColor.ANSI.DEFAULT));
        coloredTexts.addAll(convertToColoredText(entity));
        return new TextPart(coloredTexts);
    }

    private List<ColoredText> convertToColoredText(Object entity) {
        List<ColoredText> coloredTexts = new ArrayList<>();
        TextColor color = null;
        if (entity instanceof Hero) {
            coloredTexts.add(new ColoredText(((Hero) entity).getName(), TextColor.ANSI.BLUE, TextColor.ANSI.DEFAULT));
        } else if (entity instanceof Enemy) {
            color = switch (((Enemy) entity).getType()) {
                case "Zombie" -> TextColor.ANSI.GREEN;
                case "Vampire" -> TextColor.ANSI.RED;
                case "Ogre" -> TextColor.ANSI.YELLOW;
                case "Serpent Mage", "Ghost" -> TextColor.ANSI.WHITE;
                default -> null;
            };
            if (color != null) {
                coloredTexts.add(new ColoredText(((Enemy) entity).getType(), color, TextColor.ANSI.DEFAULT));
            }
        } else {
            color = switch (((Item) entity).getType()){
                case "blue elixir" -> TextColor.ANSI.BLUE;
                case "green elixir", "scroll agility", "sword" -> TextColor.ANSI.GREEN;
                case "red elixir", "scroll strength", "axe" -> TextColor.ANSI.RED;
                case "yellow elixir", "coins" -> TextColor.ANSI.YELLOW;
                case "scroll death" -> TextColor.ANSI.WHITE;
                case "meat", "chapman" -> TextColor.ANSI.CYAN;
                default -> null;
            };
            if (color != null){
            coloredTexts.add(new ColoredText(((Item)entity).getType(), color, TextColor.ANSI.DEFAULT));
            }
        }
        return coloredTexts;
    }


    private void resizeIfNecessary() {
        if (events.size() > maxEventsSize) {
            events.removeLast();
        }
    }

    public List<TextPart> getEvents() {
        return events;
    }

    public static class TextPart {
        List<ColoredText> coloredTexts;

        public TextPart(List<ColoredText> coloredTexts) {
            this.coloredTexts = coloredTexts;
        }

        public List<ColoredText> getColoredTexts() {
            return coloredTexts;
        }
    }

    public static class ColoredText {
        String text;
        TextColor color;
        TextColor backgroundColor;

        public ColoredText(String text, TextColor textColor, TextColor backgroundColor) {
            this.text = text;
            this.color = textColor;
            this.backgroundColor = backgroundColor;
        }

        public static ColoredText createColoredText(String text){
            TextColor textColor;
            if (text == null){
                return new ColoredText("Пусто", TextColor.ANSI.WHITE, TextColor.ANSI.DEFAULT);
            }
            textColor = switch (text){
                case "elixir", "Эликсиры", "blue", "blue elixir" -> TextColor.ANSI.BLUE;
                case "scroll", "yellow", "Свитки", "yellow elixir" -> TextColor.ANSI.YELLOW;
                case "meat", "chapman" -> TextColor.ANSI.CYAN;
                case "green", "Еда", "sword", "agility", "green elixir", "scroll agility" -> TextColor.ANSI.GREEN;
                case "Оружие", "axe", "red", "strength", "red elixir", "scroll strength" -> TextColor.ANSI.RED;
                default -> TextColor.ANSI.WHITE;
            };
            return new ColoredText(text, textColor, TextColor.ANSI.WHITE);
        }

        public String getText() {
            return text;
        }

        public void setText(String text){
            this.text = text;
        }

        public TextColor getColor() {
            return color;
        }

        public TextColor getBackgroundColor(){
            return backgroundColor;
        }

        public void setColor(TextColor textColor){
            color = textColor;
        }

        public void setBackgroundColor(TextColor backgroundColor){
            this.backgroundColor = backgroundColor;
        }
    }
}