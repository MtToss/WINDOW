package buginvaderz.window;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;

public class TitleBarController {
    public BorderPane titBar;
    BorderPane root;
    public Button minimizeButton;
    public BorderPane borderPane;

    @FXML
    public void minimizeButton() {

        System.out.println("HAI");
        titBar.setVisible(false);

    }

    @FXML
    public void closeButton() {

    }

    public void setMainPane(BorderPane pane) {
        this.titBar = pane;
    }


}
