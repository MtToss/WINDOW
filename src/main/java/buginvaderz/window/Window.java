package buginvaderz.window;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Window extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            buginvaderz.window.Application app = buginvaderz.window.Application.getInstance(); //subtype ni chatgpt kasi potangina mas
            BorderPane windowBorderPane = app.getMainWindow(); //subtyping ko

            Parent taskBar = FXMLLoader.load(getClass().getResource("TaskBar.fxml"));

            Pane root = new Pane();

            // To be revised:
            // Make the path dynamic.
            IconTemplate icon1 = new IconTemplate("Jabii", "StartWindowIcon.png", 25, 25, "IDE.fxml", root);
            IconTemplate icon2 = new IconTemplate("Spuuchify", "https://static-00.iconduck.com/assets.00/spotify-icon-512x511-5p0rqbu1.png", 25, 100, "IDE.fxml", root);
            root.getChildren().add(icon1.getIcon());
            root.getChildren().add(icon2.getIcon());

            windowBorderPane.setBottom(taskBar);
            windowBorderPane.setCenter(root);

            Scene scene = new Scene(windowBorderPane);

            primaryStage.setMaximized(true);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Bug Invaderz");
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}