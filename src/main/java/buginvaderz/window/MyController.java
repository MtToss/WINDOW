package buginvaderz.window;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class MyController {
    @FXML
    private AnchorPane imagePane;

    @FXML
    private ImageView imageView;

    @FXML
    public void initialize() {
        imagePane.widthProperty().addListener((observable, oldValue, newValue) -> {
            imageView.setFitWidth(newValue.doubleValue());
        });

        imagePane.heightProperty().addListener((observable, oldValue, newValue) -> {
            imageView.setFitHeight(newValue.doubleValue());
        });
    }
}

