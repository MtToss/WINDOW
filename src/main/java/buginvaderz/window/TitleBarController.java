package buginvaderz.window;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class TitleBarController {

    public BorderPane titBar;
    public Button minimizeButton;

    @FXML
    public void minimizeButton() {

        System.out.println("HAI");
        titBar.setVisible(false);

    }

    @FXML
    public void closeButton() {
        titBar.getChildren().clear();
    }

    public void setMainPane(BorderPane pane) {
        this.titBar = pane;

    }




}
