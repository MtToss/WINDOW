package buginvaderz.window;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class TaskBarController {
    @FXML
    private HBox hboxContainer = new HBox();


    public void initialize() {
    }

    public void addIcon(Pane iconPane) {
        hboxContainer.getChildren().add(iconPane);
    }
}
