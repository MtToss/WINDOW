package buginvaderz.window;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

public class IconTemplate {

    String iconName;
    String imagePath;
    String fxmlLoader;

    int x, y;

    Pane pane;


    IconTemplate(String iconName, String imagePath, int x, int y, String fxmlLoader, Pane pane) {
        this.iconName = iconName;
        this.imagePath = imagePath;
        this.fxmlLoader = fxmlLoader;
        this.x = x;
        this.y = y;
        this.pane = pane;

    }

    public void openIcon() {
        try {
            BorderPane borderPane = new BorderPane();

            // Load TitleBar.fxml
            FXMLLoader titleBarLoader = new FXMLLoader(getClass().getResource("TitleBar.fxml"));
            Parent titleBarRoot = titleBarLoader.load(); // Load the FXML and get the root node
            TitleBarController titleBarController = titleBarLoader.getController();
            
            // Load another FXML specified by getFXML()
            FXMLLoader bodyLoader = new FXMLLoader(getClass().getResource(getFXML()));
            Parent bodyRoot = bodyLoader.load(); // Load the FXML and get the root node
            
            // Set the loaded nodes to the BorderPane
            borderPane.setTop(titleBarRoot);
            borderPane.setCenter(bodyRoot);
            
            // Perform any necessary operations with the controllers
            titleBarController.setMainPane(borderPane);

            pane.getChildren().add(borderPane);
            borderPane.setLayoutX(350);
            borderPane.setLayoutY(50);

            borderPane.setPrefSize(600.,400);

        }
        catch (IOException e) {
            e.printStackTrace();

        }
    }



    public Pane getIcon() {
        Pane iconPane = new Pane();

        Image image = new Image(imagePath);
        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(50);
        imageView.setFitHeight(50);

        imageView.setX(getX());
        imageView.setY(getY());

        Label nameLabel = new Label(iconName);

        nameLabel.setLayoutX(getX());
        nameLabel.setLayoutY(getY() + 50);

        iconPane.getChildren().addAll(imageView, nameLabel);

        iconPane.setOnMouseClicked(event -> {
            try {
                System.out.println("OPENING");
                openIcon();
                System.out.println("SPECIFIC ICON JUST BEEN OPENED");
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        return iconPane;
    }

    //method to add the components to the pane

    public String getFXML() {
        return fxmlLoader;

    }

    public int getX() {
        return x;

    }

    public int getY() {
        return y;
    }

}
