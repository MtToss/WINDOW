    package buginvaderz.window;

    import javafx.fxml.FXMLLoader;
    import javafx.scene.Parent;
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

        public Application(String fxmlLoader, Pane pane, BorderPane borderPane, Pane rootPane) {
            this.fxmlLoader = fxmlLoader;
            this.pane = pane;
            this.borderPane = borderPane;

            try {
                FXMLLoader titleBarLoader = new FXMLLoader(getClass().getResource("TitleBar.fxml"));
                Parent titleBarRoot = titleBarLoader.load();
                titleBarRoot.isResizable();
                Pane titleBarPane = new Pane(titleBarRoot);

                new Drag(borderPane, rootPane);

                FXMLLoader bodyLoader = new FXMLLoader(getClass().getResource(getFXML()));
                Parent bodyRoot = bodyLoader.load();
                System.out.println(bodyRoot);
                bodyRoot.isResizable();
                Pane bodyPane = new Pane(bodyRoot);

                borderPane.setTop(titleBarPane);
                borderPane.setCenter(bodyPane);


                pane.getChildren().add(borderPane);

                borderPane.setLayoutX(0);
                borderPane.setLayoutY(0);

                borderPane.setBackground(Background.fill(Color.VIOLET));
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
