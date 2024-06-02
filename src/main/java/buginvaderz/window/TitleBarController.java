package buginvaderz.window;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class TitleBarController {

    @FXML
    private BorderPane titBar;

    @FXML
    private Button minimizeButton;

    @FXML
    Pane ide = new Pane(); // sa ide fxml

    @FXML
    Label label = new Label(); // sa ide fxml

    private boolean isMaximized = true;

    @FXML
    public void handleMinimizeButton() {
        titBar.setVisible(false);
    }

    @FXML
    public void handleCloseButton() {
        titBar.setVisible(false);
    }

    @FXML
    public void handleMaximizeButton() {
        if (isMaximized) {
            ide.setPrefSize(700, 700);
            label.setText("Maximized");
            isMaximized = false;
        } else {
            ide.setPrefSize(300, 300);
            label.setText("Minimized");
            isMaximized = true;
        }
        System.out.println(isMaximized);
    }

    public void setMainPane(BorderPane pane) {
        this.titBar = pane;
    }
}
