package buginvaderz.window;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class TitleBarController {

    @FXML
    private BorderPane titBar = new BorderPane();

    @FXML
    private Button minimizeButton;

    private Pane ide;

    @FXML
    Label label = new Label();

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
    public void isMax() {
        if (isMaximized) {
            ide.setPrefSize(200, 200);
            titBar.setPrefSize(200, 25);
            isMaximized = false;
        }
        else {
            ide.setPrefSize(800, 800);
            titBar.setPrefSize(500, 25);
            isMaximized = true;
        }
    }
    public void setIdePane(Pane ide) {
        this.ide = ide;
    }

    public void setMainPane(BorderPane pane) {
        this.titBar = pane;
    }
}