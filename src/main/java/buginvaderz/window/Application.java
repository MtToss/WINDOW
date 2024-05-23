package buginvaderz.window;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class Application {
    private static Application instance;
    protected BorderPane mainWindow = new BorderPane();

    String iconName;
    String imagePath;
    String fxmlLoader;
    Pane pane;
    BorderPane borderPane;

    public Application() {

    }

    public Application( String fxmlLoader, Pane pane, BorderPane borderPane) {

        this.fxmlLoader = fxmlLoader;
        this.pane = pane;
        this.borderPane = borderPane;

        try {
            FXMLLoader titleBarLoader = new FXMLLoader(getClass().getResource("TitleBar.fxml"));
            Parent titleBarRoot = titleBarLoader.load();
            TitleBarController titleBarController = titleBarLoader.getController();

            FXMLLoader bodyLoader = new FXMLLoader(getClass().getResource(getFXML()));
            Parent bodyRoot = bodyLoader.load();

            borderPane.setTop(titleBarRoot);
            borderPane.setCenter(bodyRoot);

            titleBarController.setMainPane(borderPane);

            pane.getChildren().add(borderPane);
            borderPane.setLayoutX(350);
            borderPane.setLayoutY(50);

            borderPane.setPrefSize(600., 400);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Application getInstance() {
        if(instance == null) {
            instance = new Application();
        }

        return instance;
    }

    public BorderPane getMainWindow() {
        return mainWindow;
    }

    public String getFXML() {
        return fxmlLoader;
    }

}
