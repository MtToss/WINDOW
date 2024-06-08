    package buginvaderz.window;

    import javafx.fxml.FXMLLoader;
    import javafx.scene.Parent;
    import javafx.scene.layout.AnchorPane;
    import javafx.scene.layout.Background;
    import javafx.scene.layout.BorderPane;
    import javafx.scene.layout.Pane;
    import javafx.scene.paint.Color;

    import java.io.IOException;

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
                System.out.println(getFXML());
                AnchorPane bodyRoot = bodyLoader.load();
                //System.out.println(bodyRoot); //here
                bodyRoot.isResizable();
                Pane bodyPane = new Pane(bodyRoot);
                System.out.println(bodyPane);
                System.out.println(bodyPane.getId());

                FXMLLoader titleBarLoader = new FXMLLoader(getClass().getResource("TitleBar.fxml"));
                Parent titleBarRoot = titleBarLoader.load();
                TitleBarController controller = titleBarLoader.getController();
                controller.setRoot(rootPane);
                controller.setMainPane(bodyPane);
                controller.setMainApplicationPane(borderPane);
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
