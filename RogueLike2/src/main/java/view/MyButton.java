package view;

import com.googlecode.lanterna.TextColor;

public class MyButton {
    private GameEvent.ColoredText coloredText;
    private Runnable action;
    private TextColor actionColor;

    public MyButton(GameEvent.ColoredText coloredText, TextColor actionColor, Runnable action) {
        this.coloredText = coloredText;
        this.actionColor = actionColor;
        this.action = action;
    }

    public void run() {
        action.run();
    }

    public GameEvent.ColoredText getColoredText() {
        return coloredText;
    }

    public TextColor getActionColor(){
        return actionColor;
    }
}
