package buginvaderz.window;


import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class IconTemplate extends Pane {
    private BorderPane borderPane = new BorderPane();
    private Pane iconPane = new Pane();
    private String iconName;
    private String imagePath;
    private String fxmlLoader;
    private int x, y;
    private Pane pane;

    public IconTemplate(String iconName, String imagePath, int x, int y, String fxmlLoader, Pane pane) {
        this.iconName = iconName;
        this.imagePath = imagePath;
        this.fxmlLoader = fxmlLoader;
        this.x = x;
        this.y = y;
        this.pane = pane;

        Label nameLabel = new Label(iconName);
        nameLabel.setLayoutX(getX());
        nameLabel.setLayoutY(getY() + 50);
        iconPane.getChildren().addAll(nameLabel);

        mouseClicked();
    }

    public Pane getIcon() {

        Image image;
        image = new Image(imagePath);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setX(getX());
        imageView.setY(getY());
        iconPane.getChildren().addAll(imageView);

        return iconPane;
    }

    public void mouseClicked() {
        iconPane.setOnMouseClicked(event -> {
            try {
                System.out.println("OPENING");
                new Application(fxmlLoader, borderPane, pane);
                System.out.println("SPECIFIC ICON JUST BEEN OPENED");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }

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
