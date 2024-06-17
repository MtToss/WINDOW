package buginvaderz.window;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Window extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("try.fxml"));

            Scene scene = new Scene(root);
            primaryStage.setWidth(700);
            primaryStage.setHeight(700);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Bug Invaderz");
            System.out.println("test");
            primaryStage.show();

        } catch (Exception e) {
            System.out.println("Window Error: " + e.getMessage()); e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
