package view;

import com.googlecode.lanterna.TextColor;

public class MyTheme {
    private TextColor foreground;
    private TextColor background;

    public MyTheme(TextColor foreground, TextColor background) {
        this.foreground = foreground;
        this.background = background;
    }

    public TextColor getForeground(){
        return foreground;
    }

    public TextColor getBackground(){
        return background;
    }
}