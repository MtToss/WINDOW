package buginvaderz.window;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Window extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        try {
            BorderPane borderPane = new BorderPane();

            Parent taskBar = FXMLLoader.load(getClass().getResource("TaskBar.fxml"));

            Pane root = new Pane();

            IconTemplate icon1 = new IconTemplate("Java", "https://static-00.iconduck.com/assets.00/java-icon-256x256-k4ufhihr.png", 25, 25, "IDE.fxml", root);
            IconTemplate icon2 = new IconTemplate("Python", "https://s3.dualstack.us-east-2.amazonaws.com/pythondotorg-assets/media/community/logos/python-logo-only.png", 25, 100, "IDE.fxml", root);

            root.getChildren().add(icon1.getIcon());
            root.getChildren().add(icon2.getIcon());

            borderPane.setBottom(taskBar);
            borderPane.setCenter(root);

            Scene scene = new Scene(borderPane);

            primaryStage.setMaximized(true);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Bug Invaderz");
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        launch(args);
    }
}
