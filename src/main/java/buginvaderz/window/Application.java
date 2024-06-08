    package buginvaderz.window;

    import java.io.IOException;

    import javafx.fxml.FXMLLoader;
    import javafx.scene.Parent;
    import javafx.scene.layout.AnchorPane;
    import javafx.scene.layout.BorderPane;
    import javafx.scene.layout.Pane;

    public class Application {
        private static Application instance;
        protected BorderPane mainWindow = new BorderPane();

        String fxmlLoader;
        Pane pane;
        BorderPane borderPane;

        public Application() {

        }

        public Application(String fxmlLoader, BorderPane borderPane, Pane rootPane) {
            this.fxmlLoader = fxmlLoader;
            this.borderPane = borderPane;
            this.pane = rootPane;

            try {
                FXMLLoader bodyLoader = new FXMLLoader(getClass().getResource(getFXML()));
                AnchorPane bodyRoot = bodyLoader.load();
                bodyRoot.isResizable();
                Pane bodyPane = new Pane(bodyRoot);

                FXMLLoader titleBarLoader = new FXMLLoader(getClass().getResource("TitleBar.fxml"));
                Parent titleBarRoot = titleBarLoader.load();
                TitleBarController controller = titleBarLoader.getController();
                controller.setRoot(rootPane);
                controller.setMainPane(bodyPane);
                controller.setMainApplicationPane(borderPane);

                // SpuuchifyController spuuchifyController = new FXMLLoader(getClass().getResource("Spuuchify.fxml")).getController();
                // spuuchifyController.setRoot(rootPane);
                // spuuchifyController.setMainPane(bodyPane);
                // spuuchifyController.setMainApplicationPane(borderPane);

                titleBarRoot.isResizable();
                Pane titleBarPane = new Pane(titleBarRoot);

                new Drag(borderPane, rootPane);

                borderPane.setTop(titleBarPane);
                borderPane.setCenter(bodyPane);

                rootPane.getChildren().add(borderPane);

                borderPane.setLayoutX(35);
                borderPane.setLayoutY(35);

            } catch (IOException e) {
                System.out.println("Application Error: " + e.getMessage()); e.printStackTrace();
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
