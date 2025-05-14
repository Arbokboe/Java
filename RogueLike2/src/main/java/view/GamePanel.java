package view;

import com.googlecode.lanterna.gui2.*;

public class GamePanel {

  private Panel panel;

  public GamePanel() {

  }

  public void initializePanel() {
    panel = new Panel(new GridLayout(2));
  }

  public void startGamePanel() {
    Label label = new Label("Hello, Player!");
    Button button = new Button("Start new game");
    panel.addComponent(label);
    panel.addComponent(button);

    Window window = new BasicWindow();
    window.setComponent(panel);

    //MultiWindowTextGUI gui = new MultiWindowTextGUI(screen, new DefaultWindowManager(), new EmptySpace(TextColor.Blue));
    //gui.addWindowAndWait(window);
  }
}
