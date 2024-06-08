package buginvaderz.window;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Window extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            buginvaderz.window.Application app = buginvaderz.window.Application.getInstance();
            BorderPane windowBorderPane = app.getMainWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("TaskBar.fxml"));
            Parent taskBar = loader.load();

            windowBorderPane.setBottom(taskBar);

            Pane root = new Pane();

            root.setBackground(Background.fill(Color.AQUAMARINE));
            IconTemplate icon1 = new IconTemplate("Jabii", "https://www.developer.com/wp-content/uploads/2021/09/Java-tutorials.jpg", 25, 25, "IDE.fxml", root);
            IconTemplate icon2 = new IconTemplate("Spuuchify", "https://static-00.iconduck.com/assets.00/spotify-icon-512x511-5p0rqbu1.png", 25, 100, "Spuuchify.fxml", root);


            root.getChildren().add(icon1.getIcon());
            root.getChildren().add(icon2.getIcon());
            windowBorderPane.setCenter(root);


            Scene scene = new Scene(windowBorderPane);

            primaryStage.setMaximized(true);
            primaryStage.setScene(scene);
            primaryStage.setFullScreen(true);
            primaryStage.setTitle("Bug Invaderz");
            primaryStage.show();

        } catch (Exception e) {
            System.out.println("Window Error: " + e.getMessage()); e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
