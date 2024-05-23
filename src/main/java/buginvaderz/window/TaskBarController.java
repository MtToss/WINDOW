package buginvaderz.window;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;


public class TaskBarController {
    @FXML
    private HBox hboxContainer = new HBox();

    Image image;
    Pane containerImage = new Pane();

    public void initialize() {


        image = new Image("https://ih1.redbubble.net/image.920753007.8979/flat,750x,075,f-pad,750x1000,f8f8f8.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        containerImage.getChildren().addAll(imageView);
        hboxContainer.getChildren().addAll(containerImage);

    }
}

