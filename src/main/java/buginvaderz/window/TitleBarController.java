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

    @FXML
    private AnchorPane ide = new AnchorPane();

    @FXML
    Label label = new Label();

    private boolean isMaximized = true;
    private boolean isPanelMaximized = true;

    @FXML
    public void initialize() {

        if (isPanelMaximized) {
            ide.setPrefSize(800, 800);
            isPanelMaximized = false;
        }
        else {
            ide.setPrefSize(200, 200);
            isPanelMaximized = true;
        }
        System.out.println(ide);


        if (isMaximized) {
            titBar.setPrefSize(500, 25);
        }
        else {
            titBar.setPrefSize(200, 25);
        }

    }


    @FXML
    public void handleMinimizeButton() {
        titBar.setVisible(false);
    }

    @FXML
    public void handleCloseButton() {
        titBar.setVisible(false);
    }

    @FXML
    public boolean isMax() {
        if (isMaximized) {
            isMaximized = false;
        }
        else {
            isMaximized = true;
        }
        initialize();
        return isMaximized;
    }

    public void setMainPane(BorderPane pane) {
        this.titBar = pane;
    }
}